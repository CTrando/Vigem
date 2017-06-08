package com.ct.game.view;

/**
 * Created by Cameron on 6/7/2017.
 */
public class Key {
    private int keyCode;
    private boolean pressed;

    public Key(int keyCode, boolean pressed){
        this.keyCode = keyCode;
        this.pressed = pressed;
    }

    public Key(int keyCode){
        this(keyCode, false);
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isPressed() {
        return pressed;
    }
}
