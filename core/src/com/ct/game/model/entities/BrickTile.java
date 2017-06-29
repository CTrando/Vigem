package com.ct.game.model.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ct.game.model.components.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 6/16/2017.
 */
public class BrickTile extends Tile {

    @Override
    public void init(int row, int col) {
        super.init(row, col);
        add(new RenderComponent(TileMap.brickSprite));
        add(new PhysicsComponent());

        add(new CreateBodyComponent(
                BodyDef.BodyType.StaticBody,
                col,
                row,
                Tile.SIZE_PIXEL / GameScreen.PPM,
                Tile.SIZE_PIXEL / GameScreen.PPM
        ));
        setWalkable(false);
        setEntity(true);
    }
}
