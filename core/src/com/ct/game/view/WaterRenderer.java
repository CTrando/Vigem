package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.ct.game.model.components.PhysicsComponent;
import com.ct.game.model.entities.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.utils.Mappers;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

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

        HashMap<Body, Vector2[]> coordinates = new HashMap<Body, Vector2[]>();
        for (Tile waterTile : tileMap.getTilesOfType(TileMap.TileType.WATER)) {
            PhysicsComponent pHc = Mappers.pHm.get(waterTile);
            Body body = pHc.getBody();
            if (coordinates.get(body) == null) {
                coordinates.put(body, new Vector2[3]);
                coordinates.get(body)[0] = new Vector2();
                coordinates.get(body)[1] = new Vector2();
                coordinates.get(body)[2] = new Vector2();
            }
            if (pHc.getVertices()[0] < coordinates.get(body)[0].x) {
                coordinates.get(body)[0].x = pHc.getVertices()[0];
            }
            if (pHc.getVertices()[1] < coordinates.get(body)[0].y) {
                coordinates.get(body)[0].y = pHc.getVertices()[1];
            }

            if(pHc.getVertices()[0] == -.5f && pHc.getVertices()[1] == -.5f) {
                coordinates.get(body)[2] = Mappers.tm.get(waterTile).getPos();
            }
            //store width and height into the second vector2
            //WIDTH
            float width = getDimension(pHc.getVertices(), 0);
            if (width > coordinates.get(body)[1].x) {
                coordinates.get(body)[1].x = width;
            }

            //HEIGHT
            float height = getDimension(pHc.getVertices(), 1);
            if (height > coordinates.get(body)[1].y) {
                coordinates.get(body)[1].y = height;
            }

            //TODO create an algorithm that will cover all the water tiles and render one big rectangle

            /*Vector2 pos = Mappers.tm.get(waterTile).getPos();
            batch.draw(TileMap.waterSprite,
                       pos.x - Tile.SIZE/2,
                       pos.y - Tile.SIZE/2,
                       Tile.SIZE,
                       Tile.SIZE
                       );*/

        }

        for (Vector2[] dimensions : coordinates.values()) {
            float x = dimensions[0].x + dimensions[2].x;
            float y = dimensions[0].y + dimensions[2].y;

            float width = 2*dimensions[1].x;
            float height = 2*dimensions[1].y;

            batch.draw(TileMap.waterSprite,
                       x - Tile.SIZE / 2,
                       y - Tile.SIZE / 2,
                       width,
                       height);
        }


        waterFBO.end();
        shaderManager.unBindShader(batch);
        batch.end();

        batch.begin();
        batch.draw(waterTextures, 0, 0);
        batch.end();
    }

    /**
     * @param vertices
     * @param offset   0 for width, 1 for height
     * @return
     */
    private float getDimension(float[] vertices, int offset) {
        float dim = Math.abs(-.5f - vertices[offset]) + 1;
        return dim == 0 ? 2 : dim;
    }
}
