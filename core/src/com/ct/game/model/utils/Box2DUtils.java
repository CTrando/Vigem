package com.ct.game.model.utils;

import box2dLight.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Cameron on 7/1/2017.
 */
public class Box2DUtils {
    private static Box2DFactory box2DFactory;

    //TODO find out how this class even works with the mix of statics

    public static void init(World world, RayHandler rayHandler) {
        box2DFactory = new Box2DFactory();
        box2DFactory.init(world, rayHandler);
    }

    public static Body createBody(BodyDef.BodyType type, float x, float y) {
        return box2DFactory.createBody(type, x, y);
    }

    public static Fixture createFixture(Body body, float width, float height) {
        return box2DFactory.createFixture(body, width, height);
    }

    public static PointLight createPointLight(Color color,float distance, float x, float y) {
        return box2DFactory.createPointLight(color, distance, x, y);
    }


    private static class Box2DFactory {
        private World world;
        private RayHandler rayHandler;

        Box2DFactory() {

        }

        void init(World world, RayHandler rayHandler) {
            this.world = world;
            this.rayHandler = rayHandler;
        }

        Body createBody(BodyDef.BodyType type, float x, float y) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = type;
            bodyDef.position.set(x, y);

            Body body = world.createBody(bodyDef);
            return body;
        }

        Fixture createFixture(Body body, float width, float height) {
            FixtureDef fixtureDef = new FixtureDef();

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(width/2,
                           height/2);
            fixtureDef.shape = shape;

            return body.createFixture(fixtureDef);
        }

        PointLight createPointLight(Color color, float distance, float x, float y) {
            return new PointLight(rayHandler, 20, color, distance, x, y);
        }
    }
}
