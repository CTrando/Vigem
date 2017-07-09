package com.ct.game.model.listeners;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ct.game.utils.Mappers;

/**
 * Created by Cameron on 7/7/2017.
 */
public class BounceListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Entity entityA = (Entity) contact.getFixtureA().getBody().getUserData();
        Entity entityB = (Entity) contact.getFixtureB().getBody().getUserData();

        if(entityA == null|| entityB ==null) return;

        if(Mappers.bm.get(entityA) != null && Mappers.bm.get(entityB) != null) {
            contact.getFixtureA().getBody().applyForceToCenter(new Vector2(100, 100), true);
            contact.getFixtureB().getBody().applyForceToCenter(new Vector2(100, 100), true);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
