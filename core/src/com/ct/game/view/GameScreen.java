package com.ct.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.Vigem;
import com.ct.game.controller.*;
import com.ct.game.model.systems.*;

/**
 * Created by Cameron on 6/5/2017.
 */
public class GameScreen implements Screen {
    public static final float PPM = 32;

    private Vigem game;
    private SpriteBatch batch;
    private ViewHandler viewHandler;
    private FPSLogger fpsLogger;
    private InputHandler inputHandler;
    private GameController gameController;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    public GameScreen(final Vigem game){
        this.game = game;
        this.batch = game.getBatch();
        this.viewHandler = new ViewHandler();
        this.fpsLogger = new FPSLogger();
        this.inputHandler = new com.ct.game.controller.InputHandler();
        this.gameController = new GameController();
        this.world = new World(new Vector2(0,0), true);
        this.debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {
        Assets.getInstance().load();
        Box2D.init();
        viewHandler.init();
        gameController.init(inputHandler, viewHandler);
        gameController.addSystem(new RenderSystem(batch));

        //gameController.addSystem(new PlayerInputTransformSystem(inputHandler));
        gameController.addSystem(new BodyInitSystem(world));
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, viewHandler.getCamera().combined);
        viewHandler.update(batch);
        batch.begin();

        fpsLogger.log();
        gameController.getTileMap().render(batch);
        world.step(dt, 6,2);
        gameController.update(dt);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewHandler.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
