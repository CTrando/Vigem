package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.*;


/**
 * Created by Cameron on 6/5/2017.
 */
public class TransformComponent implements Component {
    //pos refers to the exact position in world units, not in pixels
    private Vector2 pos;
    //in radians
    private float rotation;

    public TransformComponent(float x, float y, float rotation) {
        this.pos = new Vector2(x, y);
        this.rotation = rotation;
    }

    public TransformComponent(){
        this(0,0, 0);
    }

    public TransformComponent(Vector2 pos){
        this(pos.x, pos.y, 0);
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }
}
