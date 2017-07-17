package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/16/2017.
 */
public class AttackSystem extends IteratingSystem {
    private World world;
    private RayCastCallback rayCastCallback;
    private Vector2 collisionPoint;

    public AttackSystem(World world) {
        super(Family.all(AttackComponent.class, PhysicsComponent.class, DirectionComponent.class).get());
        this.world = world;
        this.collisionPoint = new Vector2();
        this.rayCastCallback = new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                collisionPoint.set(point);
                System.out.println("HELLO");
                return 0;
            }};
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AttackComponent aTc = Mappers.aTm.get(entity);
        PhysicsComponent pHc = Mappers.pHm.get(entity);
        DirectionComponent dc = Mappers.dm.get(entity);

        Vector2 pos = pHc.getBody().getPosition();
        Vector2 pos2 = pHc.getBody().getPosition().cpy().add(5,0);

        world.rayCast(rayCastCallback, pos, pos2);
    }
}
