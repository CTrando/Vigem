package com.ct.game.model.systems;

import box2dLight.*;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/29/2017.
 */
public class CreateLightSystem extends IteratingSystem {

    private RayHandler rayHandler;

    public CreateLightSystem(RayHandler rayHandler) {
        super(Family.all(CreateLightComponent.class).get(), -1);
        this.rayHandler = rayHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CreateLightComponent cLc = Mappers.cLm.get(entity);
        Light light = new PointLight(rayHandler,
                                     20,
                                     cLc.getColor(),
                                     cLc.getDistance(),
                                     cLc.getX(),
                                     cLc.getY());
        light.setSoft(false);
        if(Mappers.pHm.get(entity) != null) {
            PhysicsComponent pHc = Mappers.pHm.get(entity);
            if(pHc.getBody() != null) {
                light.attachToBody(pHc.getBody());
            }
        }
        entity.remove(CreateLightComponent.class);
        entity.add(new LightComponent(light));
    }
}
