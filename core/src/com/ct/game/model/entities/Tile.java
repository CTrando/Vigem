package com.ct.game.model.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 6/12/2017.
 */
public abstract class Tile extends GameObject {

    public static float SIZE_PIXEL = 32;
    public static float SIZE = SIZE_PIXEL/GameScreen.PPM;

    private boolean isEntity;
    private boolean isWalkable;

    public void init(float row, float col) {
        add(new TransformComponent(col, row, 0));
    }

    public void render(SpriteBatch batch) {
        RenderComponent rc = Mappers.rm.get(this);

        if(rc != null){
           batch.draw(rc.getTextureRegion(), getCol() - SIZE/2, getRow()-SIZE/2, SIZE, SIZE);
        }
    }

    public float getRow() {
        return Mappers.tm.get(this).getPos().y;
    }

    public void setRow(int row) {
        Mappers.tm.get(this).getPos().y = row;
    }

    public float getCol() {
        return Mappers.tm.get(this).getPos().x;
    }

    public void setCol(int col) {
        Mappers.tm.get(this).getPos().x = col;
    }

    public boolean isEntity() {
        return isEntity;
    }

    public void setEntity(boolean entity) {
        this.isEntity = entity;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }
}
