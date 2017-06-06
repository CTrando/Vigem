package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.*;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 6/5/2017.
 */
public class RenderSystem extends IteratingSystem {
    private SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(PositionComponent.class, RenderComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pc = Mappers.pm.get(entity);
        RenderComponent rc = Mappers.rm.get(entity);

        Sprite sprite = rc.getSprite();
        float x = pc.getPos().x;
        float y = pc.getPos().y;
        float width = sprite.getWidth()/GameScreen.PPM;
        float height = sprite.getHeight()/GameScreen.PPM;

        batch.draw(sprite, x, y, width, height);
    }
}
