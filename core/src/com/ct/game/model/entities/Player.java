package com.ct.game.model.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ct.game.model.components.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.utils.Mappers;
import com.ct.game.view.Assets;

/**
 * Created by Cameron on 6/5/2017.
 */
public class Player extends GameObject {

    public void init() {
        add(new TransformComponent(1, 10, 0));
        add(new RenderComponent(new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")))));
        add(new CameraFocusComponent());
        add(new PlayerControlledComponent());
        add(new AnimationComponent(Assets.getInstance().getAnimation("player-idle-right", "tiles.atlas"), 0));
        add(new StateComponent());
        add(new DirectionComponent());

        //TODO maybe switch to builder pattern
        add(new PhysicsComponent(BodyDef.BodyType.DynamicBody,
                                    Mappers.tm.get(this).getPos(),
                                    .9f,
                                    .9f));

        Mappers.pHm.get(this).setUserData(this);

        add(new BounceComponent());
    }
}
