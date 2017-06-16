package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * Created by Cameron on 6/13/2017.
 */
public class Assets {
    private static Assets assets;
    private AssetManager manager;

    private Assets(){
        init();
    }

    private void init() {
        manager = new AssetManager();
    }

    public static Assets getInstance() {
        if(assets == null){
            assets = new Assets();
        }
        return assets;
    }

    public void load(){
        String[] assets = Gdx.files.internal("assets").readString().split(", ");
        FileHandle atlas = Gdx.files.internal("tiles.atlas");

        for(String asset: assets){
            if(asset.endsWith(".atlas")){
                manager.load(asset, TextureAtlas.class);
                manager.finishLoading();
            }
        }
    }

    public <T> T get(String string, Class<T> type){
        return manager.get(string, type);
    }


}
