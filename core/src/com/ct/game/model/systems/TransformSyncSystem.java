package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/9/2017.
 */
public class TransformSyncSystem extends IteratingSystem {

    public TransformSyncSystem(){
        super(Family.all(TransformComponent.class, PhysicsComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent tc = Mappers.tm.get(entity);
        PhysicsComponent pHc = Mappers.pHm.get(entity);

        tc.setPos(pHc.getBody().getPosition());
        pHc.getBody().setTransform(tc.getPos(), tc.getRotation());
    }
}
