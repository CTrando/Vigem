package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.components.*;
import com.ct.game.model.listeners.RayCast;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/16/2017.
 */
public class AttackSystem extends IteratingSystem {
    private World world;
    private Engine engine;
    private Vector2 collisionPoint;
    private RayCast rayCast;

    public AttackSystem(final Engine engine, World world) {
        super(Family.all(AttackComponent.class, PhysicsComponent.class, DirectionComponent.class).get());
        this.world = world;
        this.engine = engine;
        this.collisionPoint = new Vector2();
        this.rayCast = new RayCast(engine);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AttackComponent aTc = Mappers.aTm.get(entity);
        PhysicsComponent pHc = Mappers.pHm.get(entity);
        DirectionComponent dc = Mappers.dm.get(entity);

        Vector2 pos = pHc.getBody().getPosition();
        Vector2 scaledDirection = dc.getDirection().getCoordinateDirection().cpy().scl(.5f);
        Vector2 pos2 = pHc.getBody().getPosition().cpy().add(scaledDirection);

        rayCast.update(scaledDirection);
        world.rayCast(rayCast, pos, pos2);
    }
}
