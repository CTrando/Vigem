package com.ct.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
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

    private WaterRenderer waterRenderer;
    private Box2DDebugRenderer debugRenderer;

    public GameScreen(final Vigem game) {
        this.game = game;
        this.batch = game.getBatch();
        this.viewportManager = new ViewportManager();
        this.fpsLogger = new FPSLogger();
        this.gameController = new GameController();
        this.debugRenderer = new Box2DDebugRenderer();
        this.shaderManager = new ShaderManager();
        this.waterRenderer = new WaterRenderer();
    }

    @Override
    public void show() {
        Assets.getInstance().load();
        Box2D.init();
        shaderManager.init();
        viewportManager.init();
        gameController.init(viewportManager);
        gameController.addSystem(new RenderSystem(batch));
        waterRenderer.init(gameController.getTileMap(), shaderManager, viewportManager);

        Gdx.input.setInputProcessor(gameController.getInputHandler());
        fboRegion.flip(false, true);
    }

    FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, (Gdx.graphics.getWidth()), (Gdx.graphics
            .getHeight()),
                                      false);

    TextureRegion fboRegion = new TextureRegion(fbo.getColorBufferTexture(),
                                          0,
                                          0,
                                          Gdx.graphics.getWidth(),
                                          Gdx.graphics.getHeight());

    @Override
    public void render(float dt) {
        //fbo.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameController.getRayHandler().setCombinedMatrix((OrthographicCamera) viewportManager.getCamera());

        viewportManager.update(batch);
        batch.begin();
        //shaderManager.bindShader(batch);
        fpsLogger.log();
        gameController.getTileMap().render(batch);
        gameController.getWorld().step(dt, 6, 2);
        gameController.update(dt);
        gameController.getRayHandler().render();

        //shaderManager.unBindShader(batch);
        batch.end();
        waterRenderer.render(batch);
        //debugRenderer.render(gameController.getWorld(), viewportManager.getCamera().combined);
        /*fbo.end();

        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.draw(fboRegion, viewportManager.getCamera().position.x - fboRegion.getRegionWidth()/PPM/2,
                   viewportManager
                           .getCamera()
                           .position.y - fboRegion.getRegionHeight()/PPM/2,
                   fboRegion
                           .getRegionWidth() / PPM,
                   fboRegion
                .getRegionHeight() / PPM);
        batch.end();*/
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
