package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.ct.game.model.components.*;
import com.ct.game.controller.InputHandler;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/9/2017.
 */
public class PlayerInputMoveSystem extends IteratingSystem {
    private InputHandler inputHandler;

    private final Vector2 RIGHT = Vector2.X;
    private final Vector2 LEFT = Vector2.X.cpy().scl(-1);
    private final Vector2 DOWN = Vector2.Y.cpy().scl(-1);
    private final Vector2 UP = Vector2.Y;


    public PlayerInputMoveSystem(InputHandler inputHandler) {
        super(Family.all(PlayerControlledComponent.class).get());
        this.inputHandler = inputHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //realistically on the first frame the player moves, he will permanently have a move component
        MoveComponent mc;
        if(Mappers.mcm.has(entity)) {
            mc = Mappers.mcm.get(entity);
        } else {
            mc = new MoveComponent(5);
        }

        if (!inputHandler.areKeysPressed(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.DOWN, Input.Keys.UP)) {
            if(mc != null) {
                mc.setEnabled(false);
            }
            return;
        }

        mc.reset();
        mc.setSpeedMag(5);

        //used for animation purposes
        mc.setPrimarySpeed(5);
        mc.setSecondarySpeed(10);

        if (inputHandler.isKeyPressed(Input.Keys.S)){
            mc.setSpeedMag(10);
        }
        if (inputHandler.isKeyPressed(Input.Keys.LEFT)) {
            mc.addVelocity(LEFT);
        }
        if (inputHandler.isKeyPressed(Input.Keys.RIGHT)) {
            mc.addVelocity(RIGHT);
        }
        if (inputHandler.isKeyPressed(Input.Keys.DOWN)) {
            mc.addVelocity(DOWN);
        }
        if (inputHandler.isKeyPressed(Input.Keys.UP)) {
            mc.addVelocity(UP);
        }

        mc.normalizeForce();
        entity.add(mc);
    }
}
