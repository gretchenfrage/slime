package com.phoenixkahlo.slime.core;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.*;

/**
 * Created by Phoenix on 5/5/2017.
 */
public class WorldState extends BasicGameState {

    private int id;
    private World physicsWorld;
    private SortedSet<Entity> updateTree;
    private SortedSet<Entity> renderTree;
    private Stack<Entity> addAccumulator;
    private Stack<Entity> removeAccumulator;
    private CameraTransform camera;

    public WorldState(int id) {
        this.id = id;
        physicsWorld = new World(new Vec2(0, -9.8f));
        updateTree = new TreeSet<>(Comparator.comparing(entity -> entity.getUpdateStage().ordinal()));
        renderTree = new TreeSet<>(Comparator.comparing(entity -> entity.getRenderStage().ordinal()));
        addAccumulator = new Stack<>();
        removeAccumulator = new Stack<>();
        camera = new CameraTransform();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        for (Entity entity : renderTree) {
            entity.render(this, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        physicsWorld.step(delta, 6, 2);
        for (Entity entity : updateTree) {
            entity.update(container, this, delta);
        }
        while (addAccumulator.size() > 0) {
            Entity toAdd = addAccumulator.pop();
            renderTree.add(toAdd);
            updateTree.add(toAdd);
            toAdd.onAdd(this);
        }
        while (removeAccumulator.size() > 0) {
            Entity toRemove = removeAccumulator.pop();
            renderTree.remove(toRemove);
            updateTree.remove(toRemove);
            toRemove.onRemove(this);
        }
    }

    public void add(Entity entity) {
        addAccumulator.push(entity);
    }

    public void remove(Entity entity) {
        removeAccumulator.push(entity);
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

}
