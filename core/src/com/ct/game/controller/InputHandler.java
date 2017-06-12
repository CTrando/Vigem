package com.ct.game.controller;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.ct.game.view.GameScreen;

import java.util.HashMap;

/**
 * Created by Cameron on 6/7/2017.
 */
public class InputHandler implements InputProcessor {
    private HashMap<Integer, Key> keyArray = new HashMap<Integer, Key>();
    private Vector2 mousePos = new Vector2(0,0);
    private Vector2 mouseClickPos = null;

    @Override
    public boolean keyDown(int keycode) {
        if(keyArray.get(keycode) != null){
            keyArray.get(keycode).setPressed(true);
        } else {
            keyArray.put(keycode, new Key(keycode));
            keyArray.get(keycode).setPressed(true);
        }
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
        mouseClickPos = new Vector2(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mouseClickPos = null;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePos.set(screenX/ GameScreen.PPM, (Gdx.graphics.getHeight()-screenY)/GameScreen.PPM);
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

    public boolean noKeyPressed() {
        for(Key key: keyArray.values()){
            if(key.isPressed()) {
                return false;
            }
        }
        return true;
    }

    public boolean areKeysPressed(int... keyCodes){
        for(int keyCode: keyCodes){
            if(keyArray.get(keyCode) != null && keyArray.get(keyCode).isPressed()){
                return true;
            }
        }
        return false;
    }

    public Vector2 getMousePos() {
        return mousePos;
    }

    public Vector2 getMouseClickPos() {
        return mouseClickPos;
    }
}
