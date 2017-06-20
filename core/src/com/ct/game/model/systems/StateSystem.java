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

    public StateSystem() {
        super(Family.all(StateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent sc = Mappers.sm.get(entity);

        PhysicsComponent pHc = Mappers.pHm.get(entity);
        MoveComponent mc = Mappers.mcm.get(entity);
        DirectionComponent dc = Mappers.dm.get(entity);

        if (pHc != null) {
            Body body = pHc.getBody();

            if (mc != null) {
                // TODO fix bug here where it doesn't realize the direction of the speed, so it will turn to another
                // animation if the magnitude is close to speed even if it is going wrong way
                // Also slowing down by hitting opposite key does not work, so fix that through velocity manipulation
                // here
                if (Math.abs(body.getLinearVelocity().len() - mc.getSpeedMag() / 2) > .1) {
                    sc.setState(StateComponent.State.dashing);
                } else {
                    sc.setState(StateComponent.State.running);
                    if (dc != null) {
                        sc.setStateDirection(dc.getDirection());
                    }
                }
            } else {
                if (body.getLinearVelocity().len() > .1) {
                    sc.setState(StateComponent.State.slowing);
                    if (dc != null) {
                        sc.setStateDirection(dc.getDirection());
                    }
                } else {
                    sc.setState(StateComponent.State.idle);
                    if (dc != null) {
                        sc.setStateDirection(dc.getDirection());
                    }
                }
            }
        }
        System.out.println(sc.getState());
    }
}
