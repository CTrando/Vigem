package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.*;
import com.ct.game.model.entities.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.utils.Mappers;
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


        for (Tile waterTile : tileMap.getTilesOfType(TileMap.TileType.WATER)) {
            //TODO create an algorithm that will cover all the water tiles and render one big rectangle
            Vector2 bottomLeftPos = Mappers.tm.get(tileMap.getTilesOfType(TileMap.TileType.WATER).first()).getPos();
            Vector2 topRightPos = Mappers.tm.get(tileMap.getTilesOfType(TileMap.TileType.WATER).peek()).getPos();

            batch.draw(TileMap.waterSprite,
                       bottomLeftPos.x - Tile.SIZE / 2,
                       bottomLeftPos.y - Tile.SIZE / 2,
                       topRightPos.x - bottomLeftPos.x + 1,
                       topRightPos.y - bottomLeftPos.y + 1);

        }


        waterFBO.end();
        shaderManager.unBindShader(batch);
        batch.end();

        batch.begin();
        batch.draw(waterTextures, 0, 0);
        batch.end();
    }
}
