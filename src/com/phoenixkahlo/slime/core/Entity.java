package com.phoenixkahlo.slime.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Phoenix on 5/5/2017.
 */
public interface Entity {

    void onAdd(WorldState state);

    void update(GameContainer container, WorldState state, int delta);

    void render(WorldState state, Graphics g);

    void onRemove(WorldState state);

    RenderStage getRenderStage();

    UpdateStage getUpdateStage();

}
