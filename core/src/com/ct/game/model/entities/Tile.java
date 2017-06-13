package com.ct.game.model.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * Created by Cameron on 6/12/2017.
 */
public abstract class Tile extends Entity {

    public static float SIZE = 32;
    private int row;
    private int col;
    private boolean isEntity;
    private Sprite sprite;
    private boolean isWalkable;

    public void init(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void render(SpriteBatch batch){
        if(sprite != null){
            batch.draw(sprite, row, col, 1, 1);
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }
}
