package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.*;
import com.ct.game.controller.InputHandler;
import com.ct.game.model.components.*;
import com.ct.game.model.entities.*;
import com.ct.game.model.utils.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.*;

/**
 * Created by Cameron on 6/16/2017.
 */
public class TileMapMouseSystem extends EntitySystem {
    private InputHandler inputHandler;
    private Engine engine;
    private TileMap tileMap;
    private Camera camera;

    public TileMapMouseSystem(InputHandler inputHandler, Engine engine, TileMap tileMap, Camera camera) {
        this.inputHandler = inputHandler;
        this.engine = engine;
        this.tileMap = tileMap;
        this.camera = camera;
    }

    @Override
    public void update(float dt) {
        if (inputHandler.getMouseClickPosPixel() != null) {
            Vector3 worldCoords = camera.unproject(new Vector3(inputHandler.getMouseClickPosPixel(), 0));
            try {
                //convert row and column to odd numbers for quad tree
                float row = MathUtils.floor(worldCoords.y) + .5f;
                float col = MathUtils.floor(worldCoords.x) + .5f;

                Tile tile = tileMap.getTileAt(col, row);
                if(tile instanceof GrassTile) {
                    Tree tree = new Tree();
                    tree.init(worldCoords.x, worldCoords.y);
                    engine.addEntity(tree);
                    //BrickTile waterTile = new BrickTile()yup;
                  //  waterTile.init(row, col);

                //    tileMap.set(col, row, waterTile, TileMap.TileType.BRICK);
                }
                //tile.dispose();
                //combinePhysicsBodies(waterTile);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void combinePhysicsBodies(Tile placedTile) {
        TransformComponent tC = Mappers.tm.get(placedTile);
        int tileRow = (int) tC.getPos().y;
        int tileCol = (int) tC.getPos().x;

      /*  PhysicsComponent pHc = Mappers.pHm.getData(placedTile);
        if(pHc != null) {
            pHc.getBody().destroyFixture(pHc.getFixture());
        }
*/
        for (int row = -1; row <= +1 && tileRow + row >= 0 && tileRow + row < TileMap.HEIGHT; row++) {
            Tile tile = tileMap.getTileAt(tileRow + row, tileCol);
            if (Mappers.pHm.get(tile) == null || tile == placedTile) {
                continue;
            }
            System.out.println("FOUND SOMETHING");
            mergeBodies(placedTile, tile, 0, -1 * row);

        }

        //can't use width here
        for (int col = -1; col <= 1 && tileCol + col >= 0 && tileCol + col < TileMap.WIDTH; col++) {
            Tile tile = tileMap.getTileAt(tileRow, tileCol + col);
            if (Mappers.pHm.get(tile) == null || tile == placedTile) {
                continue;
            }
            //mergeTileBodies(placedTile, tile);
            mergeBodies(placedTile, tile, 1, -1 * col);
        }
    }

    /**
     * @param newTile
     * @param prevTile
     * @param orientation will be 1 or -1, telling whether going East or West or North or South
     */
    private void mergeBodies(Tile newTile, Tile prevTile, int direction, int orientation) {
        PhysicsComponent pHcNewTile = Mappers.pHm.get(newTile);
        PhysicsComponent pHcPrevTile = Mappers.pHm.get(prevTile);

        if (pHcNewTile.getBody() == pHcPrevTile.getBody()) {
            return;
        }
        if (pHcNewTile.getBody().getFixtureList().size > pHcPrevTile.getBody().getFixtureList().size) {
            return;
        }

        pHcNewTile.destroyBody();
        pHcNewTile.setBody(pHcPrevTile.getBody());

        if (direction == 1) {
            float width = getDimension(pHcPrevTile.getVertices(), 0);
            pHcNewTile.setVertices(add(pHcPrevTile.getVertices(), orientation * width, 0));
        } else {
            float height = getDimension(pHcPrevTile.getVertices(), 1);
            pHcNewTile.setVertices(add(pHcPrevTile.getVertices(), orientation * height, 1));
        }
        pHcNewTile.setFixture(Box2DUtils.createFixture(pHcNewTile.getBody(), pHcNewTile.getVertices()));
    }

    private void mergeTileBodies(Tile newTile, Tile prevTile) {
        PhysicsComponent pHcNewTile = Mappers.pHm.get(newTile);
        PhysicsComponent pHcPrevTile = Mappers.pHm.get(prevTile);
        newTile.remove(PhysicsComponent.class);
        newTile.add(pHcPrevTile);
        float[] oldVertices = pHcPrevTile.getVertices();

        float[] newTileCorners = getCorners(pHcNewTile.getVertices());
        float[] prevTileCorners = getCorners(pHcPrevTile.getVertices());

        //should work when placing tile to the right of old tile
        if (newTileCorners[0] * -1 == prevTileCorners[3]) {
            oldVertices[oldVertices.length / 2] = prevTileCorners[2] + newTileCorners[2] + -1 * newTileCorners[0];
        }

        pHcNewTile.destroyCurrentFixture();
        pHcPrevTile.destroyCurrentFixture();
        pHcPrevTile.setFixture(Box2DUtils.createFixture(pHcPrevTile.getBody(), oldVertices));
        pHcPrevTile.setVertices(oldVertices);
    }

    private float[] getCorners(float[] vertices) {
        float[] corners = new float[4];

        //bottom left
        corners[0] = vertices[0];
        corners[1] = vertices[1];
        //top right
        corners[2] = vertices[vertices.length / 2];
        corners[3] = vertices[vertices.length / 2 + 1];
        return corners;
    }

    private float[] add(float[] vertices, float add, int offset) {
        float[] copy = new float[vertices.length];
        System.arraycopy(vertices, 0, copy, 0, vertices.length);
        for (int i = offset; i < copy.length; i += 2) {
            copy[i] += add;
        }
        return copy;
    }

    /**
     * @param vertices
     * @param offset   0 for width, 1 for height
     * @return
     */
    private float getDimension(float[] vertices, int offset) {
        return Math.abs(vertices[offset] - vertices[vertices.length / 2 + offset]);
    }
}
