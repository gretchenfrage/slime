package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.assets.ImageAsset;
import com.phoenixkahlo.slime.core.AbstractEntity;
import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.core.WorldState;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Phoenix on 5/6/2017.
 */
public class PhysicsBrickEntity extends AbstractEntity {

    private Body body;
    private Image image = ImageAsset.MINECRAFT_BRICK.image();

    public PhysicsBrickEntity() {
        super(RenderStage.MAIN, UpdateStage.MAIN);
    }

    @Override
    public void onAdd(WorldState state) {
        BodyDef def = new BodyDef();
        def.type = BodyType.DYNAMIC;
        def.position = new Vec2(200, 200);
        body = state.getPhysicsWorld().createBody(def);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape fixtureShape = new PolygonShape();
        fixtureShape.setAsBox(100, 100);
        fixtureDef.shape = fixtureShape;
    }

    @Override
    public void onRemove(WorldState state) {
        state.getPhysicsWorld().destroyBody(body);
    }

    @Override
    public void update(GameContainer container, WorldState state, int delta) {

    }

    @Override
    public void render(WorldState state, Graphics g) {
        g.drawImage(
                image,
                body.getPosition().x,
                body.getPosition().y
        );
    }

}
