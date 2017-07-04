package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input;
import com.ct.game.controller.InputHandler;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;


/**
 * Created by Cameron on 6/18/2017.
 */
public class PlayerInputDirectionSystem extends IteratingSystem {
    private InputHandler inputHandler;

    public PlayerInputDirectionSystem(InputHandler inputHandler){
        super(Family.all(PlayerControlledComponent.class, DirectionComponent.class).get());
        this.inputHandler = inputHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (!inputHandler.areKeysPressed(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.DOWN, Input.Keys.UP)) {
            return;
        }
        DirectionComponent dc = Mappers.dm.get(entity);
        //System.out.println(dc.getDirection());
        if (inputHandler.isKeyPressed(Input.Keys.LEFT)) {
            dc.setDirection(DirectionComponent.Direction.left);
        }
        if (inputHandler.isKeyPressed(Input.Keys.RIGHT)) {
            dc.setDirection(DirectionComponent.Direction.right);
        }
        if (inputHandler.isKeyPressed(Input.Keys.DOWN)) {
            dc.setDirection(DirectionComponent.Direction.down);
        }
        if (inputHandler.isKeyPressed(Input.Keys.UP)) {
            dc.setDirection(DirectionComponent.Direction.up);
        }
    }
}
