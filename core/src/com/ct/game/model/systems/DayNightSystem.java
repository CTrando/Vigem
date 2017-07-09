package com.ct.game.model.systems;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.MathUtils;

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
        setLighting(getLight(elapsedTime));
        elapsedTime%=DAY_LENGTH*2*MathUtils.PI;
        //System.out.println(elapsedTime);
    }

    private float getLight(float elapsedTime) {
        return .5f* MathUtils.cos(elapsedTime/DAY_LENGTH) + .5f;
    }

    private void setLighting(float lightLevel) {
        rayHandler.setAmbientLight(lightLevel);
    }
}
