package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/18/2017.
 */
public class StateSystem extends IteratingSystem {

    public StateSystem(){
        super(Family.all(StateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent sc = Mappers.sm.get(entity);

        PhysicsComponent pHc = Mappers.pHm.get(entity);
        MoveComponent mc = Mappers.mcm.get(entity);

        if(pHc != null){
            Body body = pHc.getBody();

            if(mc != null) {
                if (Math.abs(body.getLinearVelocity().len() - mc.getSpeedMag() / 2) > .1) {
                    sc.setState(StateComponent.State.dashing);
                } else {
                    sc.setState(StateComponent.State.running);
                }
            } else {
                if(body.getLinearVelocity().len() > .1){
                    sc.setState(StateComponent.State.slowing);
                } else {
                    sc.setState(StateComponent.State.idle);
                }
            }
        }
        System.out.println(sc.getState());
    }
}
