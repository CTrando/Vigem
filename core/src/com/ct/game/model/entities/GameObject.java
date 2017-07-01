package com.ct.game.model.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Cameron on 6/29/2017.
 */
public class GameObject extends Entity implements Disposable {

    public void dispose() {
        this.removeAll();
    }
}
