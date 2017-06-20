package com.ct.game.model.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.ct.game.model.entities.*;
import com.ct.game.view.Assets;

/**
 * Created by Cameron on 6/12/2017.
 */
public class TileMap {
    public static int WIDTH = 100;
    public static int HEIGHT = 100;

    public static TextureRegion grassSprite = Assets.getInstance().getTextureRegion("grass", "tiles.atlas");
    public static TextureRegion brickSprite = Assets.getInstance().getTextureRegion("brick", "tiles.atlas");

    private Tile[][] tileMap;
    private Array<Entity> entities;
    private Array<Entity> newEntities;

    public void init() {
        this.tileMap = new Tile[HEIGHT][WIDTH];
        this.entities = new Array<Entity>();
        this.newEntities = new Array<Entity>();
        //make a tilemap builder later

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Tile tile = new GrassTile();
                tile.init(row, col);
                tileMap[row][col] = tile;
            }
        }
    }

    public void update() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Tile tile = getTileAt(row, col);
                if (tile != null && tile.isEntity() && !entities.contains(tile, true)) {
                    entities.add(tile);
                    newEntities.add(tile);
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Tile tile = getTileAt(row, col);
                if (tile != null) {
                    tile.render(batch);
                }
            }
        }
    }

    public void removeTile(Tile tile) {
        removeTile(tile.getRow(), tile.getCol());
    }

    public void removeTile(int row, int col) {
        tileMap[row][col] = null;
    }

    public Array<Entity> getNewEntities() {
        Array<Entity> ret = new Array<Entity>(newEntities);
        newEntities.clear();
        return ret;
    }

    public Tile getTileAt(int row, int col) {
        try {
            return tileMap[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void set(int row, int col, Tile tile) {
        tileMap[row][col] = tile;
    }

    public Tile getTileAt(Vector2 pos) {
        return getTileAt(MathUtils.round(pos.x), MathUtils.round(pos.y));
    }
}
