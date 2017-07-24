package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

public class FriendMovementSystem extends IteratingSystem {
    private Engine engine;
    public FriendMovementSystem(Engine engine) {
        super(Family.all(FriendAIComponent.class).get());
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent dc = Mappers.dm.get(entity);

        ImmutableArray<Entity> logicEntities = engine.getEntitiesFor(
                Family.one(PlayerControlledComponent.class,
                           FriendAIComponent.class).get());
        TransformComponent entityTc = Mappers.tm.get(entity);

        for(Entity logicEntity: logicEntities) {
            if (entity == logicEntity) continue;

            TransformComponent tc = Mappers.tm.get(logicEntity);

            if (tc.getPos().dst(entityTc.getPos()) < 50) {
                Vector2 direction = tc.getPos().cpy().sub(entityTc.getPos()).nor();
                dc.setDirection(DirectionComponent.Direction.getClosestDirection(direction, 45));

                Vector2 velocityDirection = tc.getPos().cpy().sub(entityTc.getPos().cpy());
                velocityDirection.add(new Vector2().setToRandomDirection());
                MoveComponent mc = new MoveComponent(velocityDirection, 10);
                entity.add(mc);
            } else {
                entity.remove(MoveComponent.class);
            }
        }
    }
}
