package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Cameron on 6/13/2017.
 */
public class AssetsTest extends GameTest {
    @Test
    public void load() throws Exception {
        init();
        Assets assets = Assets.getInstance();
        assets.load();

       /* Sprite grass = assets.get("grass.png", Sprite.class);
        assertNotNull(grass);*/

        Texture texture = new Texture(Gdx.files.internal("grass.png"));
        assertNotNull(texture);
    }

}