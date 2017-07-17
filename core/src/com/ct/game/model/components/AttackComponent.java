package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Cameron on 7/16/2017.
 */
public class AttackComponent implements Component {
    private int attackDamage;
    public AttackComponent(int attackDamage) {
        this.attackDamage = attackDamage;
    }
}



enum Move {
    PUNCH, ONE_PUNCH;
}
