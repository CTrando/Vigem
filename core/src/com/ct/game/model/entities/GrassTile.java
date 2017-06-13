package com.ct.game.model.entities;

import com.ct.game.model.utils.TileMap;

/**
 * Created by Cameron on 6/12/2017.
 */
public class GrassTile extends Tile {

    @Override
    public void init(int row, int col){
        super.init(row, col);
        this.setWalkable(true);
        this.setSprite(TileMap.sprite);
    }
}
