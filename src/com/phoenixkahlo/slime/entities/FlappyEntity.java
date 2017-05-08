package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.assets.ImageAsset;
import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.core.WorldState;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Phoenix on 5/6/2017.
 */
public class FlappyEntity extends AbstractEntity {

    private Vec2 pos = new Vec2(100, 100);
    private Image image;

    public FlappyEntity() {
        super(RenderStage.MAIN, UpdateStage.MAIN);
        image = ImageAsset.FLAPPY.image();
    }

    @Override
    public void render(WorldState state, Graphics g) {
        g.drawImage(image, pos.x, pos.y);
    }

    @Override
    public void update(GameContainer container, WorldState state, int delta) {
        pos.x += delta * 0.1;
    }

}
