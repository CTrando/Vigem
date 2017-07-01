package com.ct.game.view;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Cameron on 6/5/2017.
 */
public class ViewportManager {

    private ScreenViewport viewPort;

    public void init() {
        viewPort = new ScreenViewport();
        viewPort.setUnitsPerPixel(1 / GameScreen.PPM);
        setCameraPos(new Vector2(0,0));
    }

    public void update(SpriteBatch batch) {
        batch.setProjectionMatrix(viewPort.getCamera().combined);
    }

    public void resize(int width, int height) {
        viewPort.update(width, height, true);
    }

    public void setCameraPos(Vector2 cameraPos) {
        viewPort.getCamera()
                .position
                .lerp(new Vector3(cameraPos, 0), .3f);
        viewPort.getCamera()
                .update();
    }

    public Camera getCamera() {
        return viewPort.getCamera();
    }
}
