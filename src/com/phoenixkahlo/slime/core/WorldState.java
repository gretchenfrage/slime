package com.phoenixkahlo.slime.core;

import com.phoenixkahlo.slime.entities.Entity;
import com.phoenixkahlo.slime.entities.PhysicsStepper;
import com.sun.javafx.geom.Vec2d;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.jbox2d.common.Vec2;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by Phoenix on 5/5/2017.
 */
public class WorldState extends BasicGameState {

    private int gameStateID;

    private int time = 0;

    private World physicsWorld;

    private SortedMap<UpdateStage, List<Entity>> updateTree;
    private SortedMap<RenderStage, List<Entity>> renderTree;
    private Stack<Entity> addAccumulator;
    private Stack<Entity> removeAccumulator;

    private PhysicsStepper physicsStepper;

    private CameraTransform camera;

    private SortedMap<Integer, List<Runnable>> scheduledEvents;

    private List<Consumer<Input>> inputListeners;
    private Stack<Consumer<Input>> inputListenerAddAccumulator;
    private Stack<Consumer<Input>> inputListenerRemoveAccumulator;

    public WorldState(int id, Vec2 gravity) {
        this.gameStateID = id;

        physicsWorld = new World(gravity);

        updateTree = new TreeMap<>(Comparator.comparing(Enum::ordinal));
        for (UpdateStage stage : UpdateStage.values())
            updateTree.put(stage, new ArrayList<>());
        renderTree = new TreeMap<>(Comparator.comparing(Enum::ordinal));
        for (RenderStage stage : RenderStage.values())
            renderTree.put(stage, new ArrayList<>());
        addAccumulator = new Stack<>();
        removeAccumulator = new Stack<>();

        physicsStepper = new PhysicsStepper(0.01f);
        add(physicsStepper);

        camera = new CameraTransform();

        scheduledEvents = new TreeMap<>();

        inputListeners = new ArrayList<>();
        inputListenerAddAccumulator = new Stack<>();
        inputListenerRemoveAccumulator = new Stack<>();
    }

    @Override
    public int getID() {
        return gameStateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        camera.transform(container, g);

        renderTree.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .forEach(entity -> entity.render(this, g));
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // update time variable
        time += delta;
        // run input listeners
        while (inputListenerAddAccumulator.size() > 0)
            inputListeners.add(inputListenerAddAccumulator.pop());
        while (inputListenerRemoveAccumulator.size() > 0)
            inputListeners.remove(inputListenerRemoveAccumulator.pop());
        for (Consumer<Input> listener : inputListeners)
            listener.accept(container.getInput());
        // collect add and remove accumulators
        while (addAccumulator.size() > 0) {
            Entity toAdd = addAccumulator.pop();
            if (toAdd.getRenderStage() != null)
                renderTree.get(toAdd.getRenderStage()).add(toAdd);
            if (toAdd.getUpdateStage() != null)
                updateTree.get(toAdd.getUpdateStage()).add(toAdd);
        }
        while (removeAccumulator.size() > 0) {
            Entity toRemove = removeAccumulator.pop();
            if (toRemove.getRenderStage() != null)
                renderTree.remove(toRemove.getRenderStage());
            if (toRemove.getUpdateStage() != null)
                updateTree.remove(toRemove.getUpdateStage());
        }
        // update the entities
        updateTree.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .forEach(entity -> entity.update(container, this, delta));
        // run scheduled events
        {
            List<Integer> timeAccumulator = new ArrayList<>();
            Iterator<Integer> timeIterator = scheduledEvents.keySet().iterator();
            int currentTime;
            while (timeIterator.hasNext() && (currentTime = timeIterator.next()) <= time)
                timeAccumulator.add(currentTime);
            timeAccumulator.stream()
                    .flatMap(time -> scheduledEvents.get(time).stream())
                    .forEach(Runnable::run);
            for (int time : timeAccumulator)
                scheduledEvents.remove(time);
        }
    }

    public void add(Entity entity) {
        addAccumulator.push(entity);
        entity.onAdd(this);
    }

    public void remove(Entity entity) {
        removeAccumulator.push(entity);
        entity.onRemove(this);
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    public int getTime() {
        return time;
    }

    public void schedule(int time, Runnable event) {
        if (!scheduledEvents.containsKey(time))
            scheduledEvents.put(time, new ArrayList<>());
        scheduledEvents.get(time).add(event);
    }

    public CameraTransform getCamera() {
        return camera;
    }

    public void listenForInput(Consumer<Input> listener) {
        inputListenerAddAccumulator.push(listener);
    }

    public void removeInputListener(Consumer<Input> listener) {
        inputListenerRemoveAccumulator.push(listener);
    }

    public PhysicsStepper getPhysicsStepper() {
        return physicsStepper;
    }

}
