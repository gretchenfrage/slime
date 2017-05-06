package com.phoenixkahlo.slime.core;

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.Collection;

/**
 * Created by Phoenix on 5/6/2017.
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
