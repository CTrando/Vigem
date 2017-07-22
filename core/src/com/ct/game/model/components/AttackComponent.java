package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Cameron on 7/16/2017.
 */
public class AttackComponent implements Component {
    private int attackDamage;
    private float refreshTime;
    private float currentTime;

    public AttackComponent(float refreshTime, int attackDamage) {
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
}



enum Move {
    PUNCH, ONE_PUNCH;
}
