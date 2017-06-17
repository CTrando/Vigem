package com.ct.game.model.entities;

import com.ct.game.model.components.RenderComponent;
import com.ct.game.model.utils.TileMap;

/**
 * Created by Cameron on 6/16/2017.
 */
public class BrickTile extends Tile {

    @Override
    public void init(int row, int col) {
        super.init(row, col);
        add(new RenderComponent(TileMap.brickSprite));
        setWalkable(false);
    }
}
