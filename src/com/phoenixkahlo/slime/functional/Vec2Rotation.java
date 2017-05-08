package com.phoenixkahlo.slime.functional;

import org.jbox2d.common.Vec2;

import java.util.function.BinaryOperator;
import java.util.function.Function;

import static java.lang.Math.atan2;
import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * Created by kahlo on 5/6/2017.
 */
public class Vec2Rotation implements Function<Vec2, Vec2> {

    private float theta;

    public Vec2Rotation(float theta) {
        this.theta = theta;
    }

    @Override
    public Vec2 apply(Vec2 vec) {
        float magnitude = vec.length();
        float direction = (float) atan2(vec.y, vec.x);
        direction += theta;
        return new Vec2(
                (float) cos(direction) * magnitude,
                (float) sin(direction) * magnitude
        );
    }

}
