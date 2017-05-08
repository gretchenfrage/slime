package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.core.WorldState;
import com.phoenixkahlo.slime.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of entity that should be extendible for any entity.
 */
public abstract class AbstractEntity implements Entity {

    private RenderStage renderStage;
    private UpdateStage updateStage;

    protected AbstractEntity(RenderStage renderStage, UpdateStage updateStage) {
        this.renderStage = renderStage;
        this.updateStage = updateStage;
    }

    @Override
    public void onAdd(WorldState state) {}

    @Override
    public void update(GameContainer container, WorldState state, int delta) {}

    @Override
    public void render(WorldState state, Graphics g) {}

    @Override
    public void onRemove(WorldState state) {}

    @Override
    public RenderStage getRenderStage() {
        return renderStage;
    }

    @Override
    public UpdateStage getUpdateStage() {
        return updateStage;
    }

}
