package com.ct.game.model.listeners;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.components.PhysicsComponent;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/20/2017.
 */
public class RayCast implements RayCastCallback {
    private Vector2 collisionPoint;
    private Vector2 hitDirection;
    private Engine engine;

    public RayCast(Engine engine) {
        this.engine = engine;
        this.collisionPoint = new Vector2();
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
        collisionPoint.set(point);

        //get the entities from the engine and loop through them to see which entity the fixture is from
        ImmutableArray<Entity> physicsEntities = engine.getEntitiesFor(
                Family.all(PhysicsComponent.class).get());
        for(Entity physicsEntity: physicsEntities) {
            PhysicsComponent pHc = Mappers.pHm.get(physicsEntity);
            if(pHc.getFixture() == fixture){
                System.out.println("Entity hit " + physicsEntity.toString());
            }
        }

        fixture.getBody().applyForceToCenter(hitDirection.cpy().scl(100), true);
        return 0;
    }

    public Vector2 getCollisionPoint() {
        return collisionPoint;
    }

    public void setCollisionPoint(Vector2 collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    public void update(Vector2 scaledDirection) {
        this.hitDirection = scaledDirection;
    }
}
