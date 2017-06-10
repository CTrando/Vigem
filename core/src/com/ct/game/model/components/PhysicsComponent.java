package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Cameron on 6/9/2017.
 */
public class PhysicsComponent implements Component{
    private Body body;
    private Fixture fixture;

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
