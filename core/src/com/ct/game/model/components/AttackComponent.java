package com.ct.game.model.components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.Vector2;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/16/2017.
 */
public class AttackComponent implements Component {
    private int attackDamage;
    private float range;
    private float refreshTime;
    private float currentTime;
    private boolean attackComplete;
    private Entity target;

    public AttackComponent(Entity target, float range, float refreshTime, int attackDamage) {
        this.target = target;
        this.range = range;
        this.attackDamage = attackDamage;
        this.refreshTime = refreshTime;
    }

    public AttackComponent(float range, float refreshTime, int attackDamage) {
        this(null, range, refreshTime, attackDamage);
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

    public Entity getTarget() {
        return target;
    }

    public Vector2 getTargetPos() {
        return Mappers.tm.get(target).getPos();
    }

    public boolean isTargetKnown() {
        return target != null;
    }
}



enum Move {
    PUNCH, ONE_PUNCH;
}
