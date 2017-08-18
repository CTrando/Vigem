package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Cameron on 6/13/2017.
 */
public class Assets {
    private static Assets assets;
    private AssetManager manager;
    private HashMap<String, Animation<TextureRegion>> animations;

    private Assets() {
        init();
    }

    private void init() {
        manager = new AssetManager();
        animations = new HashMap<String, Animation<TextureRegion>>();
    }

    public static Assets getInstance() {
        if (assets == null) {
            assets = new Assets();
        }
        return assets;
    }

    public void load() {
        String[] assets = Gdx.files.internal("asset_list.txt").readString().split("\r\n");
        for (String asset : assets) {
            if (asset.endsWith(".atlas")) {
                manager.load(asset, TextureAtlas.class);
            }
        }
        manager.finishLoading();
    }

    /**
     * @param textureName - the name of the texture
     * @param atlasName   - the name of the atlas with the .atlas extension
     * @return The specified texture region with the textureName in the specified atlas, throws null pointer
     * exception if it
     * cannot be found
     */
    public TextureRegion getTextureRegion(String textureName, String atlasName) {
        TextureRegion region = manager.get(atlasName, TextureAtlas.class).findRegion(textureName);
        if (region == null) {
            throw new NullPointerException("Region cannot be found in specified atlas");
        } else {
            return region;
        }
    }

    /**
     * Loading animations asynchronously by loading them on request and storing them into a hashmap
     *
     * @param animationName - the name of the animation; the part of the file before the underscore ex: dragon_1
     *                      dragon is the name
     * @param atlasName     - the location of the textureatlas
     * @return the animation requested
     * @throws throws IllegalArgumentException if the atlas cannot be found or if the animation is one frame or less
     */
    public Animation getAnimation(String animationName, String atlasName) {
        if (animations.get(animationName) == null) {
            if (!manager.isLoaded(atlasName, TextureAtlas.class)) {
                throw new IllegalArgumentException("TextureAtlas not loaded");
            }
            Array<TextureAtlas.AtlasRegion> regions = manager.get(atlasName, TextureAtlas.class).findRegions(animationName);
            if(regions.size == 1){
                throw new IllegalArgumentException("Animation cannot be one frame or less");
            } else if(regions.size == 0){
                return null;
            }
            Animation<TextureRegion> animation = new Animation<TextureRegion>(1f/(regions.size), regions);
            animations.put(animationName, animation);
        }
        return animations.get(animationName);
    }

    public <T> T get(String string, Class<T> type) {
        return manager.get(string, type);
    }
}
