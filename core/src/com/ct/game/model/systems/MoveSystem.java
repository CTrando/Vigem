package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/9/2017.
 */
public class MoveSystem extends IteratingSystem {

    public MoveSystem(){
        super(Family.all(MoveComponent.class, PositionComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MoveComponent mc = Mappers.mcm.get(entity);
        PositionComponent pc = Mappers.pm.get(entity);

        pc.getPos().add(mc.getVelocity());
    }
}
