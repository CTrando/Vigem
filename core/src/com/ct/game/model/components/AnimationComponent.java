package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by Cameron on 6/16/2017.
 */
public class AnimationComponent implements Component {
    private Animation currentAnimation;
    private float currentTime;

    public AnimationComponent(Animation animation, float currentTime){
        currentAnimation = animation;
        this.currentTime = currentTime;
    }

    public AnimationComponent(float currentTime) {
        this(null, currentTime);
    }

    public Animation getAnimation() {
        return currentAnimation;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void addTime(float addTime) {
        this.currentTime += addTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public void setAnimation(Animation animation) {
        if(animation != currentAnimation) {
            currentAnimation = animation;
            setCurrentTime(0);
        }
    }
}
