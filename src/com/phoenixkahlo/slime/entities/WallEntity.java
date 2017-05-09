package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.core.WorldState;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * A physics stick.
 */
public class WallEntity extends SingleBodyEntity {

    private Vec2 p1;
    private Vec2 p2;

    public WallEntity(Vec2 p1, Vec2 p2) {
        super(RenderStage.MAIN, UpdateStage.MAIN);
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    protected void configureBodyDef(BodyDef bodyDef) {
        bodyDef.type = BodyType.STATIC;

    }

    @Override
    protected void accumulateFixtureDefConfigurators(Collection<Consumer<FixtureDef>> accumulator) {
        accumulator.add(fixtureDef -> {
            EdgeShape shape = new EdgeShape();
            shape.set(p1, p2);
            fixtureDef.shape = shape;
        });
    }

    @Override
    public void update(GameContainer container, WorldState state, int delta) {
        super.update(container, state, delta);
    }
}