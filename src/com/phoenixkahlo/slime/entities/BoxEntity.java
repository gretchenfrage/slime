package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * A physics box.
 */
public class BoxEntity extends SingleBodyEntity {

    private float width;
    private float height;

    public BoxEntity(float width, float height) {
        super(RenderStage.MAIN, UpdateStage.MAIN);
        this.width = width;
        this.height = height;
    }

    @Override
    protected void configureBodyDef(BodyDef bodyDef) {
        bodyDef.type = BodyType.DYNAMIC;
    }

    @Override
    protected void accumulateFixtureDefConfigurators(Collection<Consumer<FixtureDef>> accumulator) {
        accumulator.add(fixtureDef -> {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(width / 2, height / 2);
            fixtureDef.shape = shape;
            fixtureDef.density = 1;
        });
    }

}
