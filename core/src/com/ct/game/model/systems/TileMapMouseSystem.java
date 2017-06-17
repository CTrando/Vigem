package com.ct.game.model.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.*;
import com.ct.game.controller.InputHandler;
import com.ct.game.model.entities.Tile;
import com.ct.game.model.utils.TileMap;
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
            System.out.println(worldCoords.toString());
            Tile tile = tileMap.getTileAt(MathUtils.floor(worldCoords.x), MathUtils.floor(worldCoords.y));
            if(tile != null){
                tileMap.removeTile(tile);
            }
        }
    }
}
