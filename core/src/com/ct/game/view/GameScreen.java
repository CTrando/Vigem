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
    private ViewportManager viewportManager;
    private FPSLogger fpsLogger;
    private ShaderManager shaderManager;
    private GameController gameController;

    private Box2DDebugRenderer debugRenderer;

    public GameScreen(final Vigem game){
        this.game = game;
        this.batch = game.getBatch();
        this.viewportManager = new ViewportManager();
        this.fpsLogger = new FPSLogger();
        this.gameController = new GameController();
        this.debugRenderer = new Box2DDebugRenderer();
        this.shaderManager = new ShaderManager();
    }

    @Override
    public void show() {
        Assets.getInstance().load();
        Box2D.init();
        shaderManager.init();
        viewportManager.init();
        gameController.init(viewportManager);
        gameController.addSystem(new RenderSystem(batch));

        Gdx.input.setInputProcessor(gameController.getInputHandler());
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewportManager.update(batch);
        batch.begin();
        shaderManager.bindShader(batch);

        fpsLogger.log();
        gameController.getTileMap().render(batch);
        gameController.getWorld().step(dt, 6,2);
        gameController.update(dt);

        shaderManager.unBindShader(batch);
        batch.end();
        debugRenderer.render(gameController.getWorld(), viewportManager.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        viewportManager.resize(width, height);
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
