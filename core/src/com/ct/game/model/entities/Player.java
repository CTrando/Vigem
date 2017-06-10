package com.ct.game.model.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.World;
import com.ct.game.model.components.*;
import com.ct.game.model.systems.KinematicInitSystem;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 6/5/2017.
 */
public class Player extends Entity {

    public void init(){
        add(new PositionComponent(1,10));
        add(new RenderComponent(new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")))));
        add(new CameraFocusComponent());
        add(new PlayerControlledComponent());
        add(new PhysicsComponent());
        add(new KinematicInitComponent());
    }
}
