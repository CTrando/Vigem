package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.ct.game.model.components.*;
import com.ct.game.utils.*;
import com.ct.game.view.InputHandler;

/**
 * Created by Cameron on 6/9/2017.
 */
public class PlayerInputSystem extends IteratingSystem {
    private InputHandler inputHandler;

    public PlayerInputSystem(InputHandler inputHandler) {
        super(Family.all(PlayerControlledComponent.class).get());
        this.inputHandler = inputHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (inputHandler.noKeyPressed()) {
            entity.remove(MoveComponent.class);
            return;
        }

        MoveComponent mc = new MoveComponent(1);

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
