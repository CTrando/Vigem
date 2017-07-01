package com.ct.game.model.components;

import box2dLight.Light;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Cameron on 6/29/2017.
 */
public class CreateLightComponent implements Component {

    private Color color;
    private float distance;
    private float x;
    private float y;

    public CreateLightComponent(Color color, float distance, float x, float y) {
        this.color = color;
        this.distance = distance;
        this.x = x;
        this.y = y;
    }

    public CreateLightComponent(Color color,float distance, Vector2 pos) {
        this(color, distance, pos.x, pos.y);
    }

    public Color getColor() {
        return color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDistance() {
        return distance;
    }
}
