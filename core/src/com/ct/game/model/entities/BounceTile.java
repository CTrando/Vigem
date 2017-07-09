package com.ct.game.model.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.GameScreen;

/**
 * Created by Cameron on 7/7/2017.
 */
public class BounceTile extends Tile {

    @Override
    public void init(int row, int col) {
        super.init(row, col);

        add(new PhysicsComponent(
                BodyDef.BodyType.StaticBody,
                col,
                row,
                Tile.SIZE_PIXEL / GameScreen.PPM,
                Tile.SIZE_PIXEL / GameScreen.PPM
        ));

        add(new BounceComponent());

        setEntity(true);
        Mappers.pHm.get(this).setUserData(this);
    }
}
