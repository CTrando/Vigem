package com.ct.game.controller;

import box2dLight.*;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ct.game.model.entities.*;
import com.ct.game.model.listeners.BounceListener;
import com.ct.game.model.systems.*;
import com.ct.game.model.utils.*;
import com.ct.game.view.ViewportManager;

/**
 * Created by Cameron on 6/5/2017.
 */
public class GameController {
    private Engine engine;
    private ViewportManager viewportManager;
    private InputHandler inputHandler;
    private TileMap tileMap;
    private World world;

    private RayHandler rayHandler;

    public void init(ViewportManager viewportManager){
        this.inputHandler = new InputHandler();
        this.viewportManager = viewportManager;
        this.engine = new Engine();
        this.tileMap = new TileMap();

        this.world = new World(new Vector2(0, 0), true);
        this.rayHandler = new RayHandler(world, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.rayHandler.setAmbientLight(.8f);

        Box2DUtils.init(world, rayHandler);

        tileMap.init();
        tileMap.initExpansion(viewportManager);

        Player player = new Player();
        player.init();

        Friend friend = new Friend();
        friend.init();
        Friend friend1 = new Friend();
        friend1.init();

        engine.addEntity(player);
        engine.addEntity(friend);
        //engine.addEntity(friend1);

        engine.addSystem(new CameraFocusSystem(viewportManager));
        engine.addSystem(new TileMapMouseSystem(inputHandler, engine, tileMap, viewportManager.getCamera()));
        engine.addSystem(new PlayerInputMoveSystem(inputHandler));
        engine.addSystem(new PlayerInputDirectionSystem(inputHandler));
        engine.addSystem(new PlayerInputAttackSystem(inputHandler));
        engine.addSystem(new TransformSyncSystem());
        engine.addSystem(new MoveSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new StateAnimationSystem());
        engine.addSystem(new DayNightSystem(rayHandler));
        engine.addSystem(new HealthSystem());

        engine.addSystem(new AttackSystem(engine, world));

        engine.addSystem(new LightBodyAttachSystem());
        engine.addSystem(new FriendAttackSystem(engine));
        engine.addSystem(new FriendMovementSystem(engine));

        world.setContactListener(new BounceListener());
    }

    public void update(float dt){
        engine.update(dt);
        rayHandler.update();
        tileMap.update(viewportManager);
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

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public World getWorld() {
        return world;
    }

    public void dispose() {
        world.dispose();
        rayHandler.dispose();
    }
}
