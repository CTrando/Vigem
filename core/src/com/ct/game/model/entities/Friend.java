package com.ct.game.model.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/28/2017.
 */
public class Friend extends Entity {

    public void init() {
        add(new TransformComponent(1, 15, 0));
        add(new RenderComponent(new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")))));
        add(new PhysicsComponent());
        add(new StateComponent());
        add(new DirectionComponent());

        add(new CreateBodyComponent(BodyDef.BodyType.StaticBody,
                                    Mappers.tm.get(this).getPos(),
                                    5,
                                    5));
    }
}
