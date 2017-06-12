package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.*;
import com.ct.game.controller.InputHandler;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/11/2017.
 */
public class PlayerInputTransformSystem extends IteratingSystem {
    private InputHandler inputHandler;
    public PlayerInputTransformSystem(InputHandler inputHandler) {
        super(Family.all(PlayerControlledComponent.class, TransformComponent.class).get());
        this.inputHandler = inputHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent tc = Mappers.tm.get(entity);
        Vector2 dist = inputHandler.getMousePos().cpy().sub(tc.getPos());
        if (dist.len() >= 1) {
            System.out.println(-1*(Math.abs(dist.angleRad()) +MathUtils.PI));
            tc.setRotation(-1*(Math.abs(dist.angleRad()) +MathUtils.PI)/*%MathUtils.PI/2*/);
        }
    }
}
