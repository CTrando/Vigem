package com.ct.game.model.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.ct.game.model.components.TransformComponent;
import com.ct.game.model.entities.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.Assets;

import java.util.HashMap;

/**
 * Created by Cameron on 6/12/2017.
 */
public class TileMap {
    public static int WIDTH = 100;
    public static int HEIGHT = 100;

    public static TextureRegion grassSprite = Assets.getInstance().getTextureRegion("grass", "tiles.atlas");
    public static TextureRegion brickSprite = Assets.getInstance().getTextureRegion("brick", "tiles.atlas");
    public static TextureRegion waterSprite = Assets.getInstance().getTextureRegion("water", "tiles.atlas");

    private Tile[][] tileMap;
    private HashMap<TileType, Array<Tile>> tileTypes;
    private Array<Entity> entities;
    private Array<Entity> newEntities;

    public void init() {
        this.tileMap = new Tile[HEIGHT][WIDTH];
        this.tileTypes = new HashMap<TileType, Array<Tile>>(TileType.values().length+1);
        this.entities = new Array<Entity>();
        this.newEntities = new Array<Entity>();
        //make a tilemap builder later

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Tile tile = new GrassTile();
                tile.init(row, col);
                set(row, col, tile, TileType.GRASS);
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

    public void set(int row, int col, Tile tile, TileType type) {
        tileMap[row][col] = tile;
        if(tileTypes.get(type) == null) {
            tileTypes.put(type, new Array<Tile>());
        }
        Array<Tile> tiles = tileTypes.get(type);
        if(tiles.contains(tile, true)) return;

        if(tiles.size == 0){
            tiles.add(tile);
        } else {
            for (int i = 0; i < tiles.size; i++) {
                TransformComponent tTm = Mappers.tm.get(tiles.get(i));
                TransformComponent tAm = Mappers.tm.get(tile);

                if (tAm.getPos().x < tTm.getPos().x || tAm.getPos().y < tTm.getPos().y) {
                    tiles.insert(i, tile);
                    break;
                }

                if(i == tiles.size-1){
                    tiles.add(tile);
                    break;
                }
            }
        }
    }

    public Tile getTileAt(Vector2 pos) {
        return getTileAt(MathUtils.round(pos.x), MathUtils.round(pos.y));
    }

    public Tile[][] getTiles() {
        return tileMap;
    }

    public Array<Tile> getTilesOfType(TileType type){
        if(tileTypes.get(type) == null){
            tileTypes.put(type, new Array<Tile>());
        }
        return tileTypes.get(type);
    }

    public enum TileType{
        GRASS, WATER, BRICK, BOUNCE;
    }
}
