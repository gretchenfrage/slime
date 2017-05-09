package com.phoenixkahlo.slime.entities;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Created by kahlo on 5/9/2017.
 */
public class ParticleEntity extends SingleBodyEntity {

    @Override
    protected void configureBodyDef(BodyDef bodyDef) {
        bodyDef.type = BodyType.DYNAMIC;
    }

    @Override
    protected void accumulateFixtureDefConfigurators(Collection<Consumer<FixtureDef>> accumulator) {
        accumulator.add(fixtureDef -> {
            fixtureDef.filter.categoryBits = FilterType.PARTICLE.categoryBits;
            fixtureDef.filter.maskBits = FilterType.PARTICLE.maskBits;
            CircleShape shape = new CircleShape();
            shape.setRadius(0.01f);
            fixtureDef.shape = shape;
            fixtureDef.density = 1;
        });
    }

}
