package com.ct.game.model.components;

import box2dLight.Light;
import com.badlogic.ashley.core.Component;

/**
 * Created by Cameron on 6/29/2017.
 */
public class LightComponent implements Component {
    private Light light;

    public LightComponent(Light light) {
        this.light = light;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }
}
