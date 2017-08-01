package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.components.*;
import com.ct.game.model.listeners.AttackRayCast;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/16/2017.
 */
public class AttackSystem extends IteratingSystem {
    private static final float EPSILON = .1f;

    //the indices for specific information in the Vector2 array
    private static final int START_POS = 0;
    private static final int END_POS = 1;
    private static final int SCALED_DIRECTION = 2;

    private AttackRayCast rayCast;

    public AttackSystem(final Engine engine, World world) {
        super(Family.all(AttackComponent.class, PhysicsComponent.class, DirectionComponent.class).get());
        this.rayCast = new AttackRayCast(engine, world);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AttackComponent aTc = Mappers.aTm.get(entity);

        if (!aTc.isAttackComplete()) {
            aTc.setAttackComplete(true);

            Vector2[] endPoints;
            if (aTc.isTargetKnown()) {
                endPoints = handleTargetKnown(entity);
            } else {
                endPoints = handleTargetUnknown(entity);
            }
            Vector2 scaledDirection = endPoints[SCALED_DIRECTION];

            rayCast.rayCast(endPoints[START_POS], endPoints[END_POS]);
            if (rayCast.hasCollided()) {
                Entity collidedEntity = rayCast.getCollidedEntity();
                handleKnockBack(collidedEntity, scaledDirection.nor().cpy(), 5);
                handleDamage(collidedEntity, aTc.getAttackDamage());
            }
        }

        if (aTc.getCurrentTime() > aTc.getRefreshTime()) {
            entity.remove(AttackComponent.class);
        }

        aTc.addTime(Gdx.graphics.getDeltaTime());
    }

    private Vector2[] handleTargetKnown(Entity entity) {
        AttackComponent aTc = Mappers.aTm.get(entity);
        PhysicsComponent pHc = Mappers.pHm.get(entity);

        Vector2 targetPos = aTc.getTargetPos().cpy();
        Vector2 startPos = Mappers.tm.get(entity).getPos().cpy();
        Vector2 normDirection = targetPos.cpy().sub(startPos).nor();

        //Vector2 vel = pHc.getBody().getLinearVelocity();
        Vector2 scaledDirection = normDirection.cpy().scl(aTc.getRange());

        Vector2 endPos = startPos.cpy().add(scaledDirection);

        Vector2[] retVecs = new Vector2[3];
        retVecs[START_POS] = startPos;
        retVecs[END_POS] = endPos;
        retVecs[SCALED_DIRECTION] = scaledDirection;
        return retVecs;
    }

    private Vector2[] handleTargetUnknown(Entity entity) {
        AttackComponent aTc = Mappers.aTm.get(entity);
        PhysicsComponent pHc = Mappers.pHm.get(entity);
        DirectionComponent dc = Mappers.dm.get(entity);

        Vector2 startPos = pHc.getBody().getPosition();
        //Vector2 vel = pHc.getBody().getLinearVelocity();
        Vector2 scaledDirection = dc.getDirection()
                                    .getCoordinateDirection()
                                    .cpy()
                                    .scl(aTc.getRange());
        Vector2 endPos = pHc.getBody().getPosition().cpy().add(scaledDirection);

        Vector2[] retVecs = new Vector2[3];
        retVecs[START_POS] = startPos;
        retVecs[END_POS] = endPos;
        retVecs[SCALED_DIRECTION] = scaledDirection;
        return retVecs;
    }

    private void handleKnockBack(Entity collidedEntity, Vector2 direction, int knockback) {
        PhysicsComponent collidedEntityPHC = Mappers.pHm.get(collidedEntity);
        collidedEntityPHC.getBody().setLinearVelocity(direction.scl(knockback));
    }

    private void handleDamage(Entity collidedEntity, int damage) {
        HealthComponent hc = Mappers.hm.get(collidedEntity);
        hc.takeDamage(damage);
        System.out.println(hc.getHp());
    }
}
