package com.ct.game.model.listeners;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/20/2017.
 */
public class AttackRayCast implements RayCastCallback {
    private Vector2 collisionPoint;
    private Entity collidedEntity;
    private Engine engine;
    private World world;
    private boolean hasCollided;

    public AttackRayCast(Engine engine, World world) {
        this.engine = engine;
        this.world = world;
        this.collisionPoint = new Vector2();
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
        hasCollided = true;
        collisionPoint.set(point);
        collidedEntity = identifyEntity(fixture);
        return 0;
    }

    public void rayCast(Vector2 startPos, Vector2 endPos){
        collisionPoint.setZero();
        collidedEntity = null;
        hasCollided = false;
        world.rayCast(this, startPos, endPos);
    }

    public boolean hasCollided() {
        return hasCollided;
    }

    public Vector2 getCollisionPoint() {
        return collisionPoint;
    }

    public void setCollisionPoint(Vector2 collisionPoint) {
        this.collisionPoint = collisionPoint;
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

    public Entity getCollidedEntity() {
        return collidedEntity;
    }
}
