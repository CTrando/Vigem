package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.ct.game.model.components.*;
import com.ct.game.model.entities.Wolf;
import com.ct.game.model.utils.TileMap;
import com.ct.game.utils.Mappers;

public class WolfSpawnSystem extends IteratingSystem {

    public WolfSpawnSystem() {
        super(Family.all(PlayerControlledComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //TODO randomize in a circle
        if (getEngine().getEntitiesFor(Family.all(WolfAIComponent.class).get()).size() < 3) {
            TransformComponent tc = Mappers.tm.get(entity);
            Vector2 pos = tc.getPos();

            Wolf wolf = new Wolf();
            wolf.add(new TransformComponent(pos.x + 10, pos.y, 0f));
            wolf.init();
            getEngine().addEntity(wolf);
        }
    }
}
