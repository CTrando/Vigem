package com.ct.game.controller;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.model.entities.Player;
import com.ct.game.model.systems.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.*;

/**
 * Created by Cameron on 6/5/2017.
 */
public class GameController {
    private Engine engine;

    public void init(){
        engine = new Engine();;
        Player player = new Player();
        player.init();
        engine.addEntity(player);

        Player secondPlayer = new Player();
        secondPlayer.init();
        Mappers.pm.get(secondPlayer).setPos(new Vector2(5,5));
        engine.addEntity(secondPlayer);

        engine.addSystem(new PositionSyncSystem());
        engine.addSystem(new MoveSystem());
    }

    public void update(float dt){
        engine.update(dt);
    }

    public void addSystem(EntitySystem system){
        engine.addSystem(system);
    }

    public void removeSystem(EntitySystem system){
        engine.removeSystem(system);
    }
}
