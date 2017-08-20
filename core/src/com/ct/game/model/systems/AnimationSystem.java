package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/16/2017.
 */
public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(Family.all(AnimationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(!Mappers.rm.has(entity)) entity.add(new RenderComponent());
        RenderComponent rc = Mappers.rm.get(entity);
        AnimationComponent ac = Mappers.am.get(entity);

        Animation animation = ac.getAnimation();
        ac.addTime(Gdx.graphics.getDeltaTime());

        if (animation != null) {
            if (ac.getCurrentTime() > 2 * animation.getAnimationDuration()) {
                ac.setCurrentTime(0);
            }

            rc.setTextureRegion(((TextureRegion) animation.getKeyFrame(ac.getCurrentTime(), true)));
        }
    }
}
