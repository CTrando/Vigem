package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 6/5/2017.
 */
public class RenderSystem extends IteratingSystem {
    private SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, RenderComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent tc = Mappers.tm.get(entity);
        RenderComponent rc = Mappers.rm.get(entity);

        TextureRegion textureRegion = rc.getTextureRegion();
        float width = textureRegion.getRegionWidth() / GameScreen.PPM;
        float height = textureRegion.getRegionHeight() / GameScreen.PPM;
        float drawX = tc.getPos().x - width / 2;
        float drawY = tc.getPos().y - height / 2;
        float rotation = tc.getRotation();

        batch.draw(textureRegion,
                   drawX,
                   drawY,
                   width/2,
                   height/2,
                   width,
                   height,
                   1,
                   1,
                   rotation* MathUtils.radiansToDegrees);
    }
}
