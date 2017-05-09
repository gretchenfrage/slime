package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.core.WorldState;
import com.phoenixkahlo.slime.particles.ParticleStepper;
import org.newdawn.slick.GameContainer;

/**
 * Created by kahlo on 5/9/2017.
 */
public class PhysicsStepper extends AbstractEntity {

    private float timeDebt = 0;
    private float timeStep;
    private ParticleStepper particleDriver;

    public PhysicsStepper(float timeStep) {
        super(null, UpdateStage.PHYSICS);
        this.timeStep = timeStep;
        particleDriver = new ParticleStepper();
    }

    @Override
    public void update(GameContainer container, WorldState state, int delta) {
        timeDebt += delta / 1000f;
        while (timeDebt >= timeStep) {
            state.getPhysicsWorld().step(timeStep, 6, 2);
            particleDriver.step();
            timeDebt -= timeStep;
        }
    }

    public ParticleStepper getParticleDriver() {
        return particleDriver;
    }

}
