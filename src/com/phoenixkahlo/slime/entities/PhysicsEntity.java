package com.phoenixkahlo.slime.entities;

import com.phoenixkahlo.slime.core.RenderStage;
import com.phoenixkahlo.slime.core.UpdateStage;
import com.phoenixkahlo.slime.functional.PCollectors;
import com.phoenixkahlo.slime.functional.Vec2Rotation;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * An abstract entity class that provides helper methods for creating and rendering bodies.
 */
public abstract class PhysicsEntity extends AbstractEntity {

    public PhysicsEntity() {
        super(RenderStage.MAIN, UpdateStage.MAIN);
    }

    public PhysicsEntity(RenderStage renderStage, UpdateStage updateStage) {
        super(renderStage, updateStage);
    }

    protected Body createBody(World world, Consumer<BodyDef> bodyDefConfigurator,
                              Collection<Consumer<FixtureDef>> fixtureDefConfigurators) {
        BodyDef bodyDef = new BodyDef();
        bodyDefConfigurator.accept(bodyDef);
        Body body = world.createBody(bodyDef);
        for (Consumer<FixtureDef> configurator : fixtureDefConfigurators) {
            FixtureDef fixtureDef = new FixtureDef();
            configurator.accept(fixtureDef);
            body.createFixture(fixtureDef);
        }
        return body;
    }

    protected Body createBody(World world, Consumer<BodyDef> bodyDefConfigurator,
                              Consumer<FixtureDef>... fixtureDefConfigurators) {
        return createBody(world, bodyDefConfigurator, Arrays.asList(fixtureDefConfigurators));
    }

    protected void renderFixtures(Body body, Graphics g) {
        Fixture fixture = body.getFixtureList();
        do {
            switch (fixture.getShape().getType()) {
                case POLYGON: {
                    PolygonShape box2DShape = (PolygonShape) fixture.getShape();
                    float[] verts = Arrays.stream(box2DShape.m_vertices)
                            .limit(box2DShape.m_count)
                            .map(new Vec2Rotation(body.getAngle()))
                            .map(vec -> vec.add(body.getPosition()))
                            .flatMap(vec -> Stream.of(vec.x, vec.y))
                            .collect(PCollectors.toFloatArray());
                    Shape slick2DShape = new Polygon(verts);
                    g.setColor(Color.blue);
                    g.draw(slick2DShape);
                    break;
                }
                case EDGE: {
                    EdgeShape box2DShape = (EdgeShape) fixture.getShape();
                    Vec2Rotation rotation = new Vec2Rotation(body.getAngle());
                    Shape slick2DShape = new Line(
                            rotation.apply(box2DShape.m_vertex1).x + body.getPosition().x,
                            rotation.apply(box2DShape.m_vertex1).y + body.getPosition().y,
                            rotation.apply(box2DShape.m_vertex2).x + body.getPosition().x,
                            rotation.apply(box2DShape.m_vertex2).y + body.getPosition().y
                    );
                    g.setColor(Color.red);
                    g.draw(slick2DShape);
                    break;
                }
                case CIRCLE: {
                    CircleShape box2DShape = (CircleShape) fixture.getShape();
                    float r = box2DShape.getRadius();
                    g.setColor(Color.green);
                    g.drawOval(
                            body.getPosition().x - r,
                            body.getPosition().y - r,
                            r * 2,
                            r * 2
                    );
                    break;
                }
                default: {
                    System.err.println("Don't know how to render fixture: " + fixture);
                }
            }
        } while ((fixture = fixture.getNext()) != null);
    }
}
