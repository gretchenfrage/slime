package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.WorldState;
import com.phoenixkahlo.slime.particles.Particle;
import com.phoenixkahlo.slime.particles.ParticleType;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Created by kahlo on 5/9/2017.
 */
public class ParticleEntity extends SingleBodyEntity implements Particle {

    private ParticleType type;

    public ParticleEntity(ParticleType type) {
        this.type = type;
    }

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

    @Override
    public void onAdd(WorldState state) {
        super.onAdd(state);
        state.getPhysicsStepper().getParticleDriver().add(this);
    }

    @Override
    public void onRemove(WorldState state) {
        super.onRemove(state);
        state.getPhysicsStepper().getParticleDriver().remove(this);
    }

    @Override
    public Vec2 getPosition() {
        return getBody().getPosition();
    }

    @Override
    public void applyForce(Vec2 force) {
        getBody().applyForceToCenter(force);
    }

    @Override
    public ParticleType getType() {
        return type;
    }
}
