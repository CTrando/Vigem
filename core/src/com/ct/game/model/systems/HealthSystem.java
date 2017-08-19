package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ct.game.model.components.HealthComponent;
import com.ct.game.model.entities.GameObject;
import com.ct.game.utils.Mappers;

public class HealthSystem extends IteratingSystem {
    public HealthSystem() {
        super(Family.all(HealthComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent hc = Mappers.hm.get(entity);
        if(hc.getHp() <= 0) {
            ((GameObject)entity).dispose();
            getEngine().removeEntity(entity);
        }
    }
}
