package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Cameron on 6/9/2017.
 */
public class MoveComponent implements Component{
    private Vector2 movementForce;
    private float speedMag;
    private float primarySpeed;
    private float secondarySpeed;
    private boolean enabled;

    //TODO switch over to acceleration system
    public MoveComponent(Vector2 movementForce, float speedMag){
        this.movementForce = movementForce;
        this.speedMag = speedMag;
        this.enabled = true;
    }

    public MoveComponent(float speedMag){
        this(new Vector2(), speedMag);
    }

    public float getPrimarySpeed() {
        return primarySpeed;
    }

    public MoveComponent setPrimarySpeed(float primarySpeed) {
        this.primarySpeed = primarySpeed;
        return this;
    }

    public float getSecondarySpeed() {
        return secondarySpeed;
    }

    public MoveComponent setSecondarySpeed(float secondarySpeed) {
        this.secondarySpeed = secondarySpeed;
        return this;
    }

    public float getSpeedMag() {
        return speedMag;
    }

    public void setSpeedMag(float speedMag) {
        this.speedMag = speedMag;
    }

    public Vector2 getForce() {
        return movementForce;
    }

    public void setMovementForce(Vector2 movementForce) {
        this.movementForce = movementForce;
    }

    public void addVelocity(Vector2 dVelocity){
        this.movementForce.add(dVelocity);
    }

    public void normalizeForce(){
        this.movementForce.setLength(speedMag);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void reset() {
        this.movementForce.setZero();
        this.speedMag = 0;
        this.enabled = true;
    }
}
