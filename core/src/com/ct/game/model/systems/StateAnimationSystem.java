package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ct.game.model.components.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.utils.Mappers;
import com.ct.game.view.Assets;

/**
 * Created by Cameron on 6/18/2017.
 */
public class StateAnimationSystem extends IteratingSystem {

    public StateAnimationSystem() {
        super(Family.all(StateComponent.class, AnimationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent sc = Mappers.sm.get(entity);
        AnimationComponent ac = Mappers.am.get(entity);
        ac.setAnimation(Assets.getInstance().getAnimation("player" + sc.getState().toString(), "tiles.atlas"));
    }
}
