package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * Created by Cameron on 6/5/2017.
 */
public class RenderComponent implements Component {
    private TextureRegion textureRegion;
    public RenderComponent(TextureRegion texture){
        this.textureRegion = texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }
}
