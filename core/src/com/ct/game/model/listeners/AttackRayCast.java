package com.ct.game.model.listeners;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/20/2017.
 */
public class AttackRayCast implements RayCastCallback {
    private Vector2 collisionPoint;
    private Vector2 hitDirection;
    private float damage;
    private Engine engine;

    public AttackRayCast(Engine engine) {
        this.engine = engine;
        this.collisionPoint = new Vector2();
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
        collisionPoint.set(point);
        Entity entity = identifyEntity(fixture);
        if(entity == null) return 0;
        //get the entities from the engine and loop through them to see which entity the fixture is from
        fixture.getBody().applyForceToCenter(hitDirection.cpy().scl(10), true);
        if(Mappers.hm.has(entity)) {
            HealthComponent hc = Mappers.hm.get(entity);
            hc.takeDamage(damage);
            System.out.print(hc.getHp());
        }
        return 0;
    }

    public Vector2 getCollisionPoint() {
        return collisionPoint;
    }

    public void setCollisionPoint(Vector2 collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    public void update(Vector2 scaledDirection, float damage) {
        this.hitDirection = scaledDirection;
        this.damage = damage;
    }

    /**
     * @param fixture - the fixture belonging to some entity
     * @return the entity with the fixture, or null if it is not registered in the engine
     */
    private Entity identifyEntity(Fixture fixture) {
        ImmutableArray<Entity> physicsEntities = engine.getEntitiesFor(
                Family.all(PhysicsComponent.class).get());
        for(Entity physicsEntity: physicsEntities) {
            PhysicsComponent pHc = Mappers.pHm.get(physicsEntity);
            if(pHc.getFixture() == fixture){
                return physicsEntity;
            }
        }
        return null;
    }
}
