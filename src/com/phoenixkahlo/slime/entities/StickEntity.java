package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.core.WorldState;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.Graphics;

/**
 * A physics stick.
 */
public class StickEntity extends PhysicsEntity {

    private Vec2 initialPosition;
    private float length;

    private Body body;

    public StickEntity(Vec2 initialPosition, float length) {
        super(RenderStage.MAIN, UpdateStage.MAIN);
        this.initialPosition = initialPosition;
        this.length = length;
    }

    @Override
    public void onAdd(WorldState state) {
        body = createBody(state.getPhysicsWorld(),
                bodyDef -> {
                    bodyDef.type = BodyType.DYNAMIC;
                    bodyDef.position = initialPosition;
                },
                fixtureDef -> {
                    EdgeShape shape = new EdgeShape();
                    shape.set(new Vec2(0, 0), new Vec2(length, 0));
                    fixtureDef.shape = shape;
                }
                );
    }

    @Override
    public void render(WorldState state, Graphics g) {
        renderFixtures(body, g);
    }

    @Override
    public void onRemove(WorldState state) {
        state.getPhysicsWorld().destroyBody(body);
    }

}
