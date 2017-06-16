package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Cameron on 6/9/2017.
 */
public class MoveComponent implements Component{
    private Vector2 velocity;
    private float speedMag;

    public MoveComponent(Vector2 velocity, float speedMag){
        this.velocity = velocity;
        this.speedMag = speedMag;
    }

    public MoveComponent(float speedMag){
        this(new Vector2(), speedMag);
    }

    public float getSpeedMag() {
        return speedMag;
    }

    public void setSpeedMag(float speedMag) {
        this.speedMag = speedMag;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void addVelocity(Vector2 dVelocity){
        this.velocity.add(dVelocity);
    }

    public void normalizeVelocity(){
        this.velocity.setLength(speedMag);
    }
}
