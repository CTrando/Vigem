package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.*;


/**
 * Created by Cameron on 6/5/2017.
 */
public class PositionComponent implements Component {
    //pos refers to the exact position in world units, not in pixels
    private Vector2 pos;

    public PositionComponent(float x, float y) {
        pos = new Vector2(x, y);
    }

    public PositionComponent(){
        this(0,0);
    }

    public PositionComponent(Vector2 pos){
        this.pos = pos;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }
}
