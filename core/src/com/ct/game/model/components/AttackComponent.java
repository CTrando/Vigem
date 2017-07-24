package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Cameron on 7/16/2017.
 */
public class AttackComponent implements Component {
    private int attackDamage;
    private float range;
    private float refreshTime;
    private float currentTime;
    private boolean attackComplete;

    public AttackComponent(float range, float refreshTime, int attackDamage) {
        this.range = range;
        this.attackDamage = attackDamage;
        this.refreshTime = refreshTime;
    }

    public float getRefreshTime() {
        return refreshTime;
    }

    public void addTime(float deltaTime) {
        currentTime+=deltaTime;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public float getRange() {
        return range;
    }

    public boolean isAttackComplete() {
        return attackComplete;
    }

    public void setAttackComplete(boolean attackComplete) {
        this.attackComplete = attackComplete;
    }

    public int getAttackDamage() {
        return attackDamage;
    }
}



enum Move {
    PUNCH, ONE_PUNCH;
}
