package com.ct.game.controller;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.Vector2;
import com.ct.game.model.entities.Player;
import com.ct.game.model.systems.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.utils.Mappers;
import com.ct.game.view.ViewHandler;

/**
 * Created by Cameron on 6/5/2017.
 */
public class GameController {
    private Engine engine;
    private InputHandler inputHandler;
    private TileMap tileMap;

    public void init(InputHandler inputHandler, ViewHandler viewHandler){
        this.inputHandler = inputHandler;
        this.engine = new Engine();
        this.tileMap = new TileMap();
        tileMap.init();

        Player player = new Player();
        player.init();

        engine.addEntity(player);

        engine.addSystem(new CameraFocusSystem(viewHandler));
        engine.addSystem(new TileMapMouseSystem(inputHandler, tileMap, viewHandler.getCamera()));
        engine.addSystem(new PlayerInputMoveSystem(inputHandler));
        engine.addSystem(new TransformSyncSystem());
        engine.addSystem(new MoveSystem());
        engine.addSystem(new AnimationSystem());
    }

    public void update(float dt){
        engine.update(dt);
        tileMap.update();
        addNewTileEntities();
    }

    private void addNewTileEntities(){
        for(Entity newEntity: tileMap.getNewEntities()){
            engine.addEntity(newEntity);
        }
    }

    public void addSystem(EntitySystem system){
        engine.addSystem(system);
    }

    public void removeSystem(EntitySystem system){
        engine.removeSystem(system);
    }

    public TileMap getTileMap() {
        return tileMap;
    }
}
