package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.ct.game.model.components.*;
import com.ct.game.controller.InputHandler;

/**
 * Created by Cameron on 6/9/2017.
 */
public class PlayerInputMoveSystem extends IteratingSystem {
    private InputHandler inputHandler;

    public PlayerInputMoveSystem(InputHandler inputHandler) {
        super(Family.all(PlayerControlledComponent.class).get());
        this.inputHandler = inputHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (!inputHandler.areKeysPressed(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.DOWN, Input.Keys.UP)) {
            entity.remove(MoveComponent.class);
            return;
        }

        MoveComponent mc = new MoveComponent(5);

        if (inputHandler.isKeyPressed(Input.Keys.S)){
            mc.setSpeedMag(10);
        }
        if (inputHandler.isKeyPressed(Input.Keys.LEFT)) {
            mc.addVelocity(new Vector2(-1, 0));
        }
        if (inputHandler.isKeyPressed(Input.Keys.RIGHT)) {
            mc.addVelocity(new Vector2(1, 0));
        }
        if (inputHandler.isKeyPressed(Input.Keys.DOWN)) {
            mc.addVelocity(new Vector2(0, -1));
        }
        if (inputHandler.isKeyPressed(Input.Keys.UP)) {
            mc.addVelocity(new Vector2(0, 1));
        }

        mc.normalizeVelocity();
        entity.add(mc);
    }
}
