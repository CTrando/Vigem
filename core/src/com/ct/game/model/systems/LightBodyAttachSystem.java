package com.ct.game.model.systems;

import box2dLight.Light;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/3/2017.
 */
public class LightBodyAttachSystem extends EntitySystem {

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        engine.addEntityListener(
                Family.all(LightComponent.class, PhysicsComponent.class).get(),
                new EntityListener() {
                                     @Override
                                     public void entityAdded(Entity entity) {
                                         Light light = Mappers.lm.get(entity).getLight();
                                         Body body = Mappers.pHm.get(entity).getBody();
                                         light.attachToBody(body);
                                     }

                                     @Override
                                     public void entityRemoved(Entity entity) {
                                         Light light = Mappers.lm.get(entity).getLight();
                                         light.attachToBody(null);
                                     }
                                 });;
    }
}
