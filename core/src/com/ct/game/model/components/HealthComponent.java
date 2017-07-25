package com.ct.game.model.components;


import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
    private float hp;
    private boolean isDead;

    public HealthComponent(float hp) {
        this.hp = hp;
        this.isDead = false;
    }

    public void takeDamage(float hp) {
        this.hp -= hp;
        if(this.hp < 0){
            isDead = true;
            this.hp = 0;
        }
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
