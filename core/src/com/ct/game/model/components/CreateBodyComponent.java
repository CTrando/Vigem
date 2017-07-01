package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * Created by Cameron on 6/28/2017.
 */
public class CreateBodyComponent implements Component {
    //read only data
    private BodyDef.BodyType type;

    //in meters
    private Vector2 pos;

    //in meters
    private float width;
    private float height;

    public CreateBodyComponent(BodyDef.BodyType type, float x, float y, float width, float height) {
        this.type = type;
        if(type == null) {
            throw new IllegalArgumentException("You must define a body type!");
        }
        this.pos = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    public CreateBodyComponent(BodyDef.BodyType type,
                               Vector2 pos,
                               float width,
                               float height) {
        this.type = type;
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public BodyDef.BodyType getType() {
        return type;
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
