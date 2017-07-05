package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.*;
import com.ct.game.model.entities.*;
import com.ct.game.model.utils.TileMap;
import org.lwjgl.opengl.GL11;

/**
 * Created by Cameron on 6/30/2017.
 */
public class WaterRenderer {
    private FrameBuffer waterFBO;
    private TextureRegion waterTextures;
    private TileMap tileMap;
    private ShaderManager shaderManager;

    public void init(TileMap tileMap, ShaderManager shaderManager) {
        this.waterFBO = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth() / (int) GameScreen.PPM, Gdx
                .graphics
                .getHeight() / (int) GameScreen.PPM,
                                        false);
        this.waterTextures = new TextureRegion(waterFBO.getColorBufferTexture(), 0, 0, waterFBO.getWidth(),
                                               waterFBO.getHeight());
        this.waterTextures.flip(false, true);
        this.tileMap = tileMap;
        this.shaderManager = shaderManager;
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        shaderManager.bindShader(batch);
        waterFBO.begin();

        for (Tile[] tileRow : tileMap.getTiles()) {
            for (Tile tile : tileRow) {
                if (tile instanceof WaterTile) {
                    //TODO create an algorithm that will cover all the water tiles and render one big rectangle
                    batch.draw(TileMap.waterSprite,
                               tile.getCol() - Tile.SIZE/2,
                               tile.getRow()-Tile.SIZE/2,
                               Tile.SIZE,
                               Tile.SIZE);
                }
            }
        }

        waterFBO.end();
        shaderManager.unBindShader(batch);
        batch.end();

        batch.begin();
        batch.draw(waterTextures, 0, 0);
        batch.end();
    }
}
