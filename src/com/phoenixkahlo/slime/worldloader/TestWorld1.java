package com.phoenixkahlo.slime.worldloader;

import com.phoenixkahlo.slime.core.WorldState;
import com.phoenixkahlo.slime.entities.BoxEntity;
import com.phoenixkahlo.slime.entities.WallEntity;
import org.jbox2d.common.Vec2;

/**
 * Created by Phoenix on 5/8/2017.
 */
public class TestWorld1 implements WorldLoader {

    @Override
    public void load(WorldState state) {
        state.getCamera().setScale(100);
        {
            BoxEntity entity = new BoxEntity(1, 1);
            state.add(entity);
            entity.getBody().setTransform(new Vec2(1, 1), 0);
        }
        {
            BoxEntity entity = new BoxEntity(1, 1);
            state.add(entity);
            entity.getBody().setTransform(new Vec2(5, 1.75f), 0);
            entity.getBody().applyLinearImpulse(new Vec2(-1, 0), entity.getBody().getPosition());
            entity.getBody().applyAngularImpulse(-0.5f);
        }
        {
            WallEntity entity = new WallEntity(new Vec2(0, 0.6f), new Vec2(0, -2));
            state.add(entity);
        }
    }

}
