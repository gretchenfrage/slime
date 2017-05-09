package com.phoenixkahlo.slime.worldloader;

import com.phoenixkahlo.slime.core.WorldState;
import com.phoenixkahlo.slime.entities.ParticleEntity;
import com.phoenixkahlo.slime.entities.WallEntity;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Input;
import org.newdawn.slick.particles.ParticleSystem;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Phoenix on 5/8/2017.
 */
public class FluidTest1 implements WorldLoader {

    @Override
    public void load(WorldState world) {
        world.getCamera().setScale(100);

        world.add(new WallEntity(new Vec2(-3, 1), new Vec2(-3, -3)));
        world.add(new WallEntity(new Vec2(-3, -3), new Vec2(3, -3)));
        world.add(new WallEntity(new Vec2(3, -3), new Vec2(3, 1)));

        world.listenForInput(input -> {
            if (input.isKeyDown(Input.KEY_M)) {
                Random random = ThreadLocalRandom.current();
                for (int i = 0; i < 1; i++) {
                    ParticleEntity entity = new ParticleEntity();
                    world.add(entity);
                    entity.getBody().setTransform(
                            new Vec2(
                                    random.nextFloat() * 2 - 1,
                                    random.nextFloat() * 2 - 1
                            ), 0);
                }
            }
        });
    }

}
