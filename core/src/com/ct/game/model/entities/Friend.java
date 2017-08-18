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
        add(new TransformComponent(5, 10, 0));
        //add(new RenderComponent(new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")))));
        add(new StateComponent());
        add(new DirectionComponent());
        add(new TransformSyncComponent());
        add(new HealthComponent(100));

        //will create body in world despite not being in the engine yet
        add(new PhysicsComponent(BodyDef.BodyType.DynamicBody,
                                    Mappers.tm.get(this).getPos(),
                                    1,
                                    1));

        add(new FriendAIComponent());
    }
}
