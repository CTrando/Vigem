package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 6/5/2017.
 */
public class RenderComponent implements Component {
    private TextureRegion textureRegion;
    private float xOffset;
    private float yOffset;

    public RenderComponent() {
        this(null, 0, 0);
    }

    public RenderComponent(TextureRegion texture){
        this(texture, -texture.getRegionWidth()/2/ GameScreen.PPM, -texture.getRegionHeight()/2/GameScreen.PPM);
    }

    public RenderComponent(TextureRegion texture, float xOffset, float yOffset) {
        this.textureRegion = texture;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        this.xOffset = -textureRegion.getRegionWidth()/2/GameScreen.PPM;
        this.yOffset = -textureRegion.getRegionHeight()/2/GameScreen.PPM;
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }
}
