package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ct.game.model.components.*;
import com.ct.game.model.entities.Wolf;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/10/2017.
 */
public class WolfAttackSystem extends IteratingSystem {
    private Engine engine;

    public WolfAttackSystem(Engine engine) {
        super(Family.all(WolfAIComponent.class).get());
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ImmutableArray<Entity> logicEntities = engine.getEntitiesFor(
                Family.one(PlayerControlledComponent.class,
                           WolfAIComponent.class).get());
        TransformComponent entityTc = Mappers.tm.get(entity);

        for(Entity logicEntity: logicEntities) {
            if(entity == logicEntity || logicEntity instanceof Wolf) continue;
            TransformComponent tc = Mappers.tm.get(logicEntity);

            if(tc.getPos().dst(entityTc.getPos()) < 1.5) {
                if(Mappers.aTm.has(entity)) {
                    return;
                }

                AttackComponent aTc = new AttackComponent(logicEntity,.6f, 1f, 15);
                System.out.println("ATTACKING");
                entity.add(aTc);
            }
        }
    }
}
