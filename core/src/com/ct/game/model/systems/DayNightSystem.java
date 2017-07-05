package com.ct.game.model.systems;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.EntitySystem;

/**
 * Created by Cameron on 7/4/2017.
 */
public class DayNightSystem extends EntitySystem {
    public static float DAY_LENGTH = 100f;

    //TODO fix day night cycle
    private RayHandler rayHandler;
    private float elapsedTime;

    public DayNightSystem(RayHandler rayHandler) {
        this.rayHandler = rayHandler;
        this.elapsedTime = 0;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        elapsedTime+=dt;
        setLighting(elapsedTime);

        elapsedTime%=DAY_LENGTH;
    }

    private void setLighting(float elapsedTime) {
        rayHandler.setAmbientLight(elapsedTime/DAY_LENGTH);
    }
}
