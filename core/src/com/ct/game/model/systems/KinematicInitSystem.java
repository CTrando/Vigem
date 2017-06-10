package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 6/9/2017.
 */
public class KinematicInitSystem extends IteratingSystem {
    private World world;

    public KinematicInitSystem(World world){
        super(Family.all(KinematicInitComponent.class, PhysicsComponent.class, PositionComponent.class, RenderComponent
                .class).get(), -1);
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent pHc = Mappers.pHm.get(entity);
        RenderComponent rc = Mappers.rm.get(entity);
        PositionComponent pc = Mappers.pm.get(entity);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(pc.getPos());

        Body body = world.createBody(bodyDef);
        pHc.setBody(body);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rc.getSprite().getWidth()/2/GameScreen.PPM, rc.getSprite().getHeight()/2/GameScreen.PPM);
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);
        pHc.setFixture(fixture);

        entity.remove(KinematicInitComponent.class);
    }
}
