package com.ct.game.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ct.game.model.components.*;
import com.ct.game.model.utils.Box2DUtils;
import com.ct.game.utils.Mappers;
import com.ct.game.view.Assets;

/**
 * Created by Cameron on 6/28/2017.
 */
public class Wolf extends GameObject {

    public void init() {
        add(new StateComponent());
        add(new DirectionComponent());
        add(new AnimationComponent(Assets.getInstance().getAnimation("wolf-idle-right", "tiles.atlas"), 0));
        add(new TransformSyncComponent());
        add(new HealthComponent(100));

        //will create body in world despite not being in the engine yet
        add(new PhysicsComponent(BodyDef.BodyType.DynamicBody,
                                    Mappers.tm.get(this).getPos(),
                                    1,
                                    1));

        add(new WolfAIComponent());
    }

    @Override
    public void dispose() {
        Box2DUtils.destroyBody(Mappers.pHm.get(this).getBody());
    }

    @Override
    public String toString() {
        return "wolf";
    }
}
