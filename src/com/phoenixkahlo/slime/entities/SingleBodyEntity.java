package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.core.WorldState;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * Extension of PhysicsEntity for entities that have only one body. Declares protected abstract methods
 * for the creation of that body, and handles the rest.
 */
public abstract class SingleBodyEntity extends PhysicsEntity {

    private Body body;

    public SingleBodyEntity() {}

    public SingleBodyEntity(RenderStage renderStage, UpdateStage updateStage) {
        super(renderStage, updateStage);
    }

    protected abstract void configureBodyDef(BodyDef bodyDef);

    protected abstract void accumulateFixtureDefConfigurators(Collection<Consumer<FixtureDef>> accumulator);

    @Override
    public void onAdd(WorldState state) {
        List<Consumer<FixtureDef>> fixtureDefAccumulator = new ArrayList<>();
        accumulateFixtureDefConfigurators(fixtureDefAccumulator);
        body = createBody(state.getPhysicsWorld(), this::configureBodyDef, fixtureDefAccumulator);
        super.onAdd(state);
    }

    @Override
    public void render(WorldState state, Graphics g) {
        renderFixtures(body, g);
    }

    @Override
    public void onRemove(WorldState state) {
        state.getPhysicsWorld().destroyBody(body);
    }

    public Body getBody() {
        return body;
    }

}
