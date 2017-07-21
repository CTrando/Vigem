package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.ct.game.controller.InputHandler;
import com.ct.game.model.components.*;

/**
 * Created by Cameron on 7/16/2017.
 */
public class PlayerInputAttackSystem extends IteratingSystem {
    private InputHandler inputHandler;

    public PlayerInputAttackSystem(InputHandler inputHandler) {
        super(Family.all(PlayerControlledComponent.class).get());
        this.inputHandler = inputHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(!inputHandler.areKeysPressed(Input.Keys.A)) {
            entity.remove(AttackComponent.class);
            return;
        }

        if (inputHandler.isKeyPressed(Input.Keys.A)) {
            entity.add(new AttackComponent(5));
        }
    }
}
