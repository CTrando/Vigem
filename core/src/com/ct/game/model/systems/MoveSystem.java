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
        super(Family.all(MoveComponent.class,PhysicsComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MoveComponent mc = Mappers.mcm.get(entity);
        PhysicsComponent pHc = Mappers.pHm.get(entity);
        /*if(mc == null) {
            pHc.getBody().setLinearVelocity(0,0);
            return;
        }*/
        pHc.getBody().setLinearDamping(2f);
        pHc.getBody().setLinearVelocity(mc.getVelocity());
    }
}
