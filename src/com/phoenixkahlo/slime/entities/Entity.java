package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.core.WorldState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * A entity within the world that can be added, removed, updated, and rendered.
 */
public interface Entity {

    void onAdd(WorldState state);

    void update(GameContainer container, WorldState state, int delta);

    void render(WorldState state, Graphics g);

    void onRemove(WorldState state);

    RenderStage getRenderStage();

    UpdateStage getUpdateStage();

}
