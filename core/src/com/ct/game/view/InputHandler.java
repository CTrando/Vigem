package com.ct.game.view;

import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;

/**
 * Created by Cameron on 6/7/2017.
 */
public class InputHandler implements InputProcessor {
    private HashMap<Integer, Key> keyArray = new HashMap<Integer, Key>();

    @Override
    public boolean keyDown(int keycode) {
        if(keyArray.get(keycode) != null){
            keyArray.get(keycode).setPressed(true);
        } else keyArray.put(keycode, new Key(keycode));
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        keyArray.get(keycode).setPressed(false);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean isKeyPressed(int keyCode){
        if(keyArray.get(keyCode)!= null) {
            return keyArray.get(keyCode).isPressed();
        } else return false;
    }

    public void setKeyValue(int keyCode, boolean value){
        if(keyArray.get(keyCode) != null){
            keyArray.get(keyCode).setPressed(value);
        } else keyArray.put(keyCode, new Key(keyCode, value));
    }
}
