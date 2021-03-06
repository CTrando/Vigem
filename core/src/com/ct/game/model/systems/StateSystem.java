package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/18/2017.
 */
public class StateSystem extends IteratingSystem {

    //margin between expected and actual value
    private static float EPSILON = .1f;

    public StateSystem() {
        super(Family.all(StateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent sc = Mappers.sm.get(entity);

        AttackComponent aTc = Mappers.aTm.get(entity);
        PhysicsComponent pHc = Mappers.pHm.get(entity);
        MoveComponent mc = Mappers.mcm.get(entity);
        DirectionComponent dc = Mappers.dm.get(entity);

        if (pHc != null) {
            Body body = pHc.getBody();

            if (mc != null && mc.isEnabled()) {
                // TODO fix bug here where it doesn't realize the direction of the speed, so it will turn to another
                // animation if the magnitude is close to speed even if it is going wrong way
                // Also slowing down by hitting opposite key does not work, so fix that through velocity manipulation
                // here
                //System.out.println(body.getLinearVelocity().len());
                if (body.getLinearVelocity().len() - mc.getPrimarySpeed() / 2 > EPSILON) {
                    sc.setState(StateComponent.State.running);
                } else {
                    sc.setState(StateComponent.State.walking);
                }
            } else {
                if (body.getLinearVelocity().len() > EPSILON) {
                    sc.setState(StateComponent.State.slowing);
                } else {
                    sc.setState(StateComponent.State.idle);
                }
            }
        }

        if (aTc != null) {
            sc.setState(StateComponent.State.attacking);
        }
        //System.out.println(entity.toString()+ "-" + sc.getState() + "-" + dc.getDirection());
    }
}
