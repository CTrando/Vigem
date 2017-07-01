package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/28/2017.
 */
public class CreateBodySystem extends IteratingSystem {

    private World world;
    public CreateBodySystem(World world) {
        super(Family.all(CreateBodyComponent.class, PhysicsComponent.class).get(), -2);
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent pHc = Mappers.pHm.get(entity);
        CreateBodyComponent bCc = Mappers.bCm.get(entity);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bCc.getType();
        bodyDef.position.set(bCc.getPos());

        Body body = world.createBody(bodyDef);
        pHc.setBody(body);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bCc.getWidth()/2,
                       bCc.getHeight()/2);
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);
        pHc.setFixture(fixture);

        entity.remove(CreateBodyComponent.class);
    }
}
