package com.ct.game.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.*;

public class Tree extends GameObject {

    public void init(float x, float y) {
        add(new TransformComponent(x, y, 0));
        TextureRegion tree = Assets.getInstance().getTextureRegion("tree", "tiles.atlas");
        add(new RenderComponent(tree, -tree.getRegionWidth()/2/ GameScreen.PPM, 0));
        add(new PhysicsComponent(BodyDef.BodyType.StaticBody,
                                 Mappers.tm.get(this).getPos(),
                                 .5f,
                                 .25f));
    }
}
