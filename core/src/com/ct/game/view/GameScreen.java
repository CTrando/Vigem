package com.ct.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ct.game.Vigem;
import com.ct.game.controller.GameController;
import com.ct.game.model.systems.RenderSystem;

/**
 * Created by Cameron on 6/5/2017.
 */
public class GameScreen implements Screen {
    public static final float PPM = 32;

    private Vigem game;
    private SpriteBatch batch;
    private ViewHandler viewHandler;
    private GameController gameController;

    public GameScreen(final Vigem game){
        this.game = game;
        this.batch = game.getBatch();
        this.viewHandler = new ViewHandler();
        this.gameController = new GameController();
    }

    @Override
    public void show() {
        viewHandler.init();
        gameController.init();
        gameController.addSystem(new RenderSystem(batch));
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewHandler.update(batch);
        batch.begin();
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
