package com.ct.game.model.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.ct.game.model.entities.*;
import com.ct.game.view.*;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * Created by Cameron on 6/12/2017.
 */
public class TileMap {
    public static float WIDTH = 4;
    public static int HEIGHT = 100;

    public static TextureRegion grassSprite = Assets.getInstance().getTextureRegion("grass", "tiles.atlas");
    public static TextureRegion brickSprite = Assets.getInstance().getTextureRegion("brick", "tiles.atlas");
    public static TextureRegion waterSprite = Assets.getInstance().getTextureRegion("water", "tiles.atlas");

    private QuadTree<Tile> quadMap;
    private HashMap<TileType, Array<Tile>> tileTypes;
    private Array<Entity> entities;
    private Array<Entity> newEntities;

    private TileMapExpansionThread expandThread;

    public void init() {
        this.quadMap = new QuadTree<Tile>(WIDTH);
        this.tileTypes = new HashMap<TileType, Array<Tile>>(TileType.values().length + 1);
        this.entities = new Array<Entity>();
        this.newEntities = new Array<Entity>();

        TreeNode<Tile> root = quadMap.getRoot();
        //expandQuadTree(root);
    }

    void expandQuadTree(TreeNode<Tile> root) {
        float width = root.width / 2;
        for (float col = root.x - width + .5f; col < root.x + width; col++) {
            for (float row = root.y - width + .5f; row < root.y + width; row++) {
                GrassTile grassTile = new GrassTile();
                grassTile.init(row, col);
                quadMap.insert(col, row, grassTile);
            }
        }
    }

    public void initExpansion(ViewportManager viewportManager) {
        expandThread = new TileMapExpansionThread(viewportManager, this);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(expandThread, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void update(ViewportManager viewportManager) {

    }

    public void render(SpriteBatch batch, ViewportManager viewport) {
        render(batch, quadMap.getRoot(), viewport.getCamera().position.cpy());
    }

    private void render(SpriteBatch batch, TreeNode node, Vector3 relativePosition) {
        if (node == null) {
            return;
        }

        float bottomLeftX = MathUtils.round(relativePosition.x - Gdx.graphics.getWidth() / GameScreen.PPM / 2);
        float bottomLeftY = MathUtils.round(relativePosition.y - Gdx.graphics.getWidth() / GameScreen.PPM / 2);
        Rectangle rect = GameScreen.getRenderBoundCoordsRelativeTo(bottomLeftX, bottomLeftY);

        if (node.width <= .5) {
            if(node.data == null){
                batch.draw(grassSprite, node.x - Tile.SIZE/2, node.y - Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
            } else {
                Tile tile = (Tile) node.data;
                tile.render(batch);
                return;
            }
        }

        if (rect.contains(node.x, node.y)) {
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
        float width = quadMap.getRoot().width;
        if (y > width/2 || y < -width/2 || x > width/2 || x < -width/2) {
            throw new IllegalArgumentException("Coordinates are not in world");
        }
        try {
            //return null;
            return quadMap.getData(y, x);
            //return tileMap[row][col];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void set(float x, float y, Tile tile, TileType type) {
        //tileMap[row][col] = tile;
        quadMap.insert(x, y, tile);
        /*if (tileTypes.getData(type) == null) {
            tileTypes.put(type, new Array<Tile>());
        }*/
       /* Array<Tile> tiles = tileTypes.getData(type);
        if (tiles.contains(tile, true)) {
            return;
        }

        if (tiles.size == 0) {
            tiles.add(tile);
        } else {
            for (int i = 0; i < tiles.size; i++) {
                TransformComponent tTm = Mappers.tm.getData(tiles.getData(i));
                TransformComponent tAm = Mappers.tm.getData(tile);

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

    public Array<Tile> getTilesOfType(TileType type) {
        if (tileTypes.get(type) == null) {
            tileTypes.put(type, new Array<Tile>());
        }
        return tileTypes.get(type);
    }

    public enum TileType {
        GRASS, WATER, BRICK, BOUNCE;
    }

    public QuadTree<Tile> getQuadMap() {
        return quadMap;
    }

    public void dispose() {
        expandThread.interrupt();
    }
}

class TileMapExpansionThread extends Thread {
    private ViewportManager viewportManager;
    private TileMap tileMap;
    private QuadTree<Tile> quadMap;

    public TileMapExpansionThread(ViewportManager viewportManager, TileMap tileMap) {
        this.viewportManager = viewportManager;
        this.tileMap = tileMap;
        this.quadMap = tileMap.getQuadMap();
    }

    @Override
    public void run() {
        TreeNode<Tile> root = quadMap.getRoot();
        TreeNode<Tile> newRoot;
        Vector2 position = new Vector2(viewportManager.getCamera().position.x, viewportManager.getCamera().position.y);
        Vector2 rootPos = new Vector2(quadMap.getRoot().x, quadMap.getRoot().y);

        if (rootPos.dst2(position) > quadMap.getRoot().width * quadMap.getRoot().width / 16) {
            if (position.x > root.x && position.y > root.y) {
                newRoot = new TreeNode<Tile>(root.x + root.width / 2, root.y + root.width / 2, 2 * root.width, null);
                newRoot.SW = root;
            } else if (position.x > root.x && position.y < root.y) {
                newRoot = new TreeNode<Tile>(root.x + root.width / 2, root.y - root.width / 2, 2 * root.width, null);
                newRoot.NW = root;
            } else if (position.x < root.x && position.y > root.y) {
                newRoot = new TreeNode<Tile>(root.x - root.width / 2, root.y + root.width / 2, 2 * root.width, null);
                newRoot.SE = root;
            } else {
                newRoot = new TreeNode<Tile>(root.x - root.width / 2, root.y - root.width / 2, 2 * root.width, null);
                newRoot.NE = root;
            }
            TileMap.WIDTH = newRoot.width;
            quadMap.setRoot(newRoot);
            tileMap.expandQuadTree(newRoot);
        }
    }
}
