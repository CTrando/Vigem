package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.HashMap;

/**
 * Created by Cameron on 6/13/2017.
 */
public class Assets {
    private static Assets assets;
    private AssetManager manager;
    private HashMap<String, Sprite> sprites;

    private Assets(){
        init();
    }

    private void init() {
        manager = new AssetManager();
        sprites = new HashMap<String, Sprite>();
    }

    public static Assets getInstance() {
        if(assets == null){
            assets = new Assets();
        }
        return assets;
    }

    public void load(){
        String[] assets = Gdx.files.internal("assets").readString().split(", ");
        for(String asset: assets){
            if(asset.endsWith(".atlas")){
                manager.load(asset, TextureAtlas.class);
                manager.finishLoading();
                loadSpritesFrom(manager.get(asset, TextureAtlas.class));
            }
        }
    }

    public Sprite getSprite(String name){
        return sprites.get(name);
    }

    private void loadSpritesFrom(TextureAtlas atlas){
        for(TextureAtlas.AtlasRegion atlasRegion: atlas.getRegions()) {
            Sprite sprite = atlas.createSprite(atlasRegion.name);
            sprites.put(atlasRegion.name, sprite);
        }
    }

    public <T> T get(String string, Class<T> type){
        return manager.get(string, type);
    }


}
