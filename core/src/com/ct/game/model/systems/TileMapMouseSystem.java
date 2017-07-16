package com.ct.game.model.systems;

import com.badlogic.ashley.core.EntitySystem;
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
    private TileMap tileMap;
    private Camera camera;

    public TileMapMouseSystem(InputHandler inputHandler, TileMap tileMap, Camera camera){
        this.inputHandler = inputHandler;
        this.tileMap = tileMap;
        this.camera = camera;
    }

    @Override
    public void update(float dt){
        if(inputHandler.getMouseClickPosPixel() != null) {
            Vector3 worldCoords = camera.unproject(new Vector3(inputHandler.getMouseClickPosPixel(), 0));
            Tile tile = tileMap.getTileAt(MathUtils.round(worldCoords.y), MathUtils.round(worldCoords.x));
            if(tile != null && !(tile instanceof WaterTile)){
                if(Mappers.lm.get(tile) != null) return;
                WaterTile waterTile = new WaterTile();
                waterTile.init(tile.getRow(), tile.getCol());

                tileMap.removeTile(tile);
                tileMap.set(tile.getRow(), tile.getCol(), waterTile, TileMap.TileType.WATER);
                //tile.dispose();

                combinePhysicsBodies(waterTile);
            }
        }
    }

    private void combinePhysicsBodies(Tile placedTile) {
        TransformComponent tC = Mappers.tm.get(placedTile);
        int tileRow = (int)tC.getPos().y;
        int tileCol = (int)tC.getPos().x;

      /*  PhysicsComponent pHc = Mappers.pHm.get(placedTile);
        if(pHc != null) {
            pHc.getBody().destroyFixture(pHc.getFixture());
        }
*/
        for(int row = tileRow-1 ; row < tileRow+1 && row >= 0 && row < TileMap.HEIGHT; row++) {
            Tile tile = tileMap.getTileAt(row, tileCol);
            if(Mappers.pHm.get(tile) == null || tile == placedTile) continue;
            System.out.println("FOUND SOMETHING");
        }

        for(int col = tileCol-1 ; col < tileCol+1 && col >= 0 && col < TileMap.WIDTH; col++) {
            Tile tile = tileMap.getTileAt(tileRow, col);
            if(Mappers.pHm.get(tile) == null || tile == placedTile) continue;
            mergeTileBodies(placedTile, tile);
        }

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
        if(newTileCorners[0]*-1 == prevTileCorners[3]) {
            oldVertices[oldVertices.length/2] = prevTileCorners[2] + newTileCorners[2] + -1*newTileCorners[0];
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
        corners[2] = vertices[vertices.length/2];
        corners[3] = vertices[vertices.length/2+1];
        return corners;
    }
}
