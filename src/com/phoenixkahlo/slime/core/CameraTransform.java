package com.phoenixkahlo.slime.core;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import static java.lang.Math.toDegrees;

/**
 * Created by Phoenix on 5/6/2017.
 */
public class CameraTransform {

    private Vec2 position;
    private float angle;
    private float scale;

    public CameraTransform() {
        this.position = new Vec2(0, 0);
        this.angle = 0;
        this.scale = 1;
    }

    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = (float) toDegrees(angle);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void transform(GameContainer container, Graphics g) {
        g.translate(container.getWidth() / 2, container.getHeight() / 2);
        g.scale(scale, scale);
        g.scale(1, -1);
        g.translate(-position.x, -position.y);
        g.rotate(position.x, position.y, -angle);
    }

    public void untransform(GameContainer container, Graphics g) {

    }

}
