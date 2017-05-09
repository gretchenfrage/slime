package com.phoenixkahlo.slime.particles;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by kahlo on 5/9/2017.
 */
public class ParticleStepper {

    private SpatialCollection<Particle> particles;
    private Map<Short, BiConsumer<Particle, Particle>> interactionHandlers;

    public ParticleStepper() {
        particles = new SpatialCollection<>(Particle::getPosition);
        interactionHandlers = new HashMap<>();
    }

    public void addInteractionHandler(ParticleType type1, ParticleType type2, BiConsumer<Particle, Particle> handler) {
        short compound = ParticleType.compound(type1, type2);
        interactionHandlers.put(compound, handler);
    }

    public void add(Particle particle) {
        particles.add(particle);
    }

    public void remove(Particle particle) {
        particles.remove(particle);
    }

    public void step() {
        for (Particle particle : particles) {
            particles.neighbors(particle.getPosition(), 1f).forEach(neighbor -> {
                short compound = ParticleType.compound(particle.getType(), neighbor.getType());
                interactionHandlers.get(compound).accept(particle, neighbor);
            });
        }
    }


}
