package com.ct.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Cameron on 6/5/2017.
 */
public class ViewHandler {

    private ScreenViewport viewPort;

    public void init() {
        viewPort = new ScreenViewport();
        viewPort.setUnitsPerPixel(1/GameScreen.PPM);
    }

    public void update(SpriteBatch batch){
        batch.setProjectionMatrix(viewPort.getCamera().combined);
    }

    public void resize(int width, int height){
        viewPort.update(width, height, true);
    }
}
