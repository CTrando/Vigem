package com.ct.game.model.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ct.game.model.components.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.utils.Mappers;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 6/16/2017.
 */
public class BrickTile extends Tile {

    @Override
    public void init(int row, int col) {
        super.init(row, col);
        add(new RenderComponent(TileMap.brickSprite));

        add(new PhysicsComponent(
                BodyDef.BodyType.StaticBody,
                col,
                row,
                Tile.SIZE_PIXEL / GameScreen.PPM,
                Tile.SIZE_PIXEL / GameScreen.PPM
        ));

        add(new LightComponent(
                Color.FIREBRICK,
                2,
                Mappers.tm.get(this).getPos().x,
                Mappers.tm.get(this).getPos().y));
        setWalkable(false);
        setEntity(true);
    }
}
