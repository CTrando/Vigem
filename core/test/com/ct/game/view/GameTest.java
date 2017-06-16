package com.ct.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.ct.game.Vigem;
import org.junit.*;
import org.mockito.Mockito;

/**
 * Created by Cameron on 6/13/2017.
 */
public class GameTest {
    private static Application game;
    @BeforeClass
    public static void init() {
        game = new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {

            }

            @Override
            public void resize(int width, int height) {

            }

            @Override
            public void render() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void resume() {

            }

            @Override
            public void dispose() {

            }
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
    }

    @AfterClass
    public static void dispose(){
        game.exit();
    }
}
