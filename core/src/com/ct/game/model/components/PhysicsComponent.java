package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.utils.Box2DUtils;

/**
 * Created by Cameron on 6/9/2017.
 */
public class PhysicsComponent implements Component{
    private Body body;
    private Fixture fixture;

    public PhysicsComponent(BodyDef.BodyType type, float x, float y, float width, float height) {
        if(type == null) {
            throw new IllegalArgumentException("You must define a body type!");
        }
        body = Box2DUtils.createBody(type, x, y);
        fixture = Box2DUtils.createFixture(body, width, height);
    }

    public PhysicsComponent(BodyDef.BodyType type,
                         Vector2 pos,
                         float width,
                         float height) {
        this(type, pos.x, pos.y, width, height);
    }

    public void setUserData(Object data) {
        body.setUserData(data);
    }

    public Object getUserData() {
        return body.getUserData();
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }
}
