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
import com.ct.game.view.*;

import java.util.HashMap;

import static com.ct.game.view.GameScreen.PPM;

/**
 * Created by Cameron on 6/12/2017.
 */
public class TileMap {
    public static int WIDTH = 1024;
    public static int HEIGHT = 100;

    public static TextureRegion grassSprite = Assets.getInstance().getTextureRegion("grass", "tiles.atlas");
    public static TextureRegion brickSprite = Assets.getInstance().getTextureRegion("brick", "tiles.atlas");
    public static TextureRegion waterSprite = Assets.getInstance().getTextureRegion("water", "tiles.atlas");

    private Tile[][] tileMap;
    private QuadTree<Tile> quadMap;
    private HashMap<TileType, Array<Tile>> tileTypes;
    private Array<Entity> entities;
    private Array<Entity> newEntities;

    public void init() {
        this.quadMap = new QuadTree<Tile>(WIDTH);
        this.tileMap = new Tile[HEIGHT][WIDTH];
        this.tileTypes = new HashMap<TileType, Array<Tile>>(TileType.values().length + 1);
        this.entities = new Array<Entity>();
        this.newEntities = new Array<Entity>();

        TreeNode<Tile> root = quadMap.getRoot();
        float width = root.width / 2;

        for (float col = root.x - width + .5f; col < root.x + width; col++) {
            for (float row = root.y - width + .5f; row < root.y + width; row++) {
                GrassTile grassTile = new GrassTile();
                grassTile.init(row, col);
                quadMap.insert(col, row, grassTile);
            }
        }
    }

    public void update() {
        /*for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Tile tile = getTileAt(row, col);
                if (tile != null && tile.isEntity() && !entities.contains(tile, true)) {
                    entities.add(tile);
                    newEntities.add(tile);
                }
            }
        }*/
    }

    public void render(SpriteBatch batch, ViewportManager viewport) {
        /*TreeNode<Tile> root = quadMap.getRoot();
        float width = root.width/2;
        for (float col = root.x - width + .5f; col < root.x + width; col++) {
            for (float row = root.y - width + .5f; row < root.y + width; row++) {
                batch.draw(grassSprite, col - Tile.SIZE / 2, row - Tile.SIZE / 2, Tile.SIZE, Tile.SIZE);
            }
        }*/
        render(batch, quadMap.getRoot(), viewport.getCamera().position.cpy());
    }

    private void render(SpriteBatch batch, TreeNode node, Vector3 relativePosition) {
        if (node == null) {
            return;
        }

        float bottomLeftX = MathUtils.round(relativePosition.x - Gdx.graphics.getWidth() / GameScreen.PPM / 2);
        float bottomLeftY = MathUtils.round(relativePosition.y - Gdx.graphics.getWidth() / GameScreen.PPM / 2);

        Rectangle rect = GameScreen.getRenderBoundCoordsRelativeTo(bottomLeftX, bottomLeftY);

        if (node.width <= .5 && node.data != null) {
            Tile tile = (Tile) node.data;
            tile.render(batch);
            return;
        }


        if(rect.contains(node.x, node.y)){
            render(batch, node.NE, relativePosition);
            render(batch, node.NW, relativePosition);
            render(batch, node.SE, relativePosition);
            render(batch, node.SW, relativePosition);
        } else {
            if ((rect.x > node.x && rect.y > node.y) || (rect.x + rect.width > node.x && rect.y + rect.height > node.y)) {
                render(batch, node.NE, relativePosition);
            }
            if ((rect.x < node.x && rect.y > node.y) || (rect.x + rect.width < node.x && rect.y + rect.height >
                    node.y)) {
                render(batch, node.NW, relativePosition);
            }
            if ((rect.x > node.x && rect.y < node.y) || (rect.x + rect.width > node.x && rect.y + rect.height <
                    node.y)) {
                render(batch, node.SE, relativePosition);
            }
            if ((rect.x < node.x && rect.y < node.y) || (rect.x + rect.width < node.x && rect.y + rect.height <
                    node.y)) {
                render(batch, node.SW, relativePosition);
            }
        }
    }

    /*public void removeTile(Tile tile) {
        removeTile(tile.getRow(), tile.getCol());
    }

    public void removeTile(int row, int col) {
        tileMap[row][col] = null;
    }*/

    public Array<Entity> getNewEntities() {
        Array<Entity> ret = new Array<Entity>(newEntities);
        newEntities.clear();
        return ret;
    }

    public Tile getTileAt(float y, float x) throws IllegalArgumentException {
        if (y > WIDTH || y < 0 || x > WIDTH || x < 0) {
            throw new IllegalArgumentException("Coordinates are not in world");
        }
        try {
            //return null;
            return quadMap.get(y, x);
            //return tileMap[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void set(float x, float y, Tile tile, TileType type) {
        //tileMap[row][col] = tile;
        quadMap.insert(x, y, tile);
        /*if (tileTypes.get(type) == null) {
            tileTypes.put(type, new Array<Tile>());
        }*/
       /* Array<Tile> tiles = tileTypes.get(type);
        if (tiles.contains(tile, true)) {
            return;
        }

        if (tiles.size == 0) {
            tiles.add(tile);
        } else {
            for (int i = 0; i < tiles.size; i++) {
                TransformComponent tTm = Mappers.tm.get(tiles.get(i));
                TransformComponent tAm = Mappers.tm.get(tile);

                if (tAm.getPos().x < tTm.getPos().x || tAm.getPos().y < tTm.getPos().y) {
                    tiles.insert(i, tile);
                    break;
                }

                if (i == tiles.size - 1) {
                    tiles.add(tile);
                    break;
                }
            }
        }*/
    }

    public Tile getTileAt(Vector2 pos) {
        return getTileAt(MathUtils.round(pos.x), MathUtils.round(pos.y));
    }

    public Tile[][] getTiles() {
        return tileMap;
    }

    public Array<Tile> getTilesOfType(TileType type) {
        if (tileTypes.get(type) == null) {
            tileTypes.put(type, new Array<Tile>());
        }
        return tileTypes.get(type);
    }

    public enum TileType {
        GRASS, WATER, BRICK, BOUNCE;
    }
}
