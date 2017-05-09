package com.phoenixkahlo.slime.worldloader;

import com.phoenixkahlo.slime.core.WorldState;
import com.phoenixkahlo.slime.entities.ParticleEntity;
import com.phoenixkahlo.slime.entities.WallEntity;
import com.phoenixkahlo.slime.particles.ParticleType;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Input;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Phoenix on 5/8/2017.
 */
public class FluidTest1 implements WorldLoader {

    @Override
    public void load(WorldState world) {
        // 100 pixels per meter
        world.getCamera().setScale(100);

        // create the walls
        /*
        world.add(new WallEntity(new Vec2(-3, 1), new Vec2(-3, -3)));
        world.add(new WallEntity(new Vec2(-3, -3), new Vec2(3, -3)));
        world.add(new WallEntity(new Vec2(3, -3), new Vec2(3, 1)));
        */
        world.add(new WallEntity(new Vec2(-4, 1), new Vec2(0, -2)));
        world.add(new WallEntity(new Vec2(4, 1), new Vec2(0, -2)));

        // create particles on pressing of the m key
        world.listenForInput(input -> {
            if (input.isKeyDown(Input.KEY_M)) {
                Random random = ThreadLocalRandom.current();
                for (int i = 0; i < 1; i++) {
                    ParticleEntity entity = new ParticleEntity(ParticleType.SOAPY_WATER);
                    world.add(entity);
                    entity.getBody().setTransform(
                            new Vec2(
                                    random.nextFloat() * 2 - 1,
                                    random.nextFloat() * 2 - 1
                            ), 0);
                }
            }
        });

        Random random = new Random();
        world.getPhysicsStepper().getParticleDriver().addInteractionHandler(
                ParticleType.SOAPY_WATER, ParticleType.SOAPY_WATER,
                (particle1, particle2) -> {
                    // particle1 always pushes particle2
                    Vec2 pos1 = particle1.getPosition();
                    Vec2 pos2 = particle2.getPosition();
                    float distance = pos2.sub(pos1).lengthSquared();
                    float repulsion = 0.000001f / Math.max((float) Math.pow(distance, 10), 0.001f);
                    Vec2 force = pos2.sub(pos1);
                    force.normalize();
                    force.x += random.nextFloat() / 30f;
                    force.y += random.nextFloat() / 30f;
                    force.mulLocal(repulsion);
                    particle2.applyForce(force);
                }
        );
    }

}
