package com.phoenixkahlo.slime.particles;

import org.jbox2d.common.Vec2;

/**
 * Created by kahlo on 5/9/2017.
 */
public interface Particle {

    Vec2 getPosition();

    void applyForce(Vec2 force);

    ParticleType getType();

}
