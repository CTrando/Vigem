package com.ct.game.model.components;

import box2dLight.Light;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.ct.game.model.utils.Box2DUtils;

/**
 * Created by Cameron on 6/29/2017.
 */
public class LightComponent implements Component {
    private Light light;

    public LightComponent(Color color, float distance, float x, float y) {
        this.light = Box2DUtils.createPointLight(color, distance, x, y);
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }
}
