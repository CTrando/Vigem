package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/9/2017.
 */
public class PositionSyncSystem extends IteratingSystem {

    public PositionSyncSystem(){
        super(Family.all(PositionComponent.class, PhysicsComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pc = Mappers.pm.get(entity);
        PhysicsComponent pHc = Mappers.pHm.get(entity);

        pc.setPos(pHc.getBody().getPosition());
    }
}
