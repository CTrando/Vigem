package com.ct.game.model.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.*;
import com.ct.game.model.components.RenderComponent;
import com.ct.game.utils.Mappers;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 6/12/2017.
 */
public abstract class Tile extends Entity {

    public static float SIZE = 32;
    private int row;
    private int col;
    private boolean isEntity;
    private boolean isWalkable;

    public void init(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void render(SpriteBatch batch) {
        RenderComponent rc = Mappers.rm.get(this);
        if(rc != null){
            batch.draw(rc.getTextureRegion(), row, col, SIZE/ GameScreen.PPM, SIZE/GameScreen.PPM);
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
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
