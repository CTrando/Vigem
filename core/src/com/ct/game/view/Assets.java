package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Cameron on 6/13/2017.
 */
public class Assets {
    private static Assets assets;
    private AssetManager manager;
    private HashMap<String, Animation> animations;

    private Assets() {
        init();
    }

    private void init() {
        manager = new AssetManager();
        animations = new HashMap<String, Animation>();
    }

    public static Assets getInstance() {
        if (assets == null) {
            assets = new Assets();
        }
        return assets;
    }

    public void load() {
        String[] assets = Gdx.files.internal("assets").readString().split(", ");
        for (String asset : assets) {
            if (asset.endsWith(".atlas")) {
                manager.load(asset, TextureAtlas.class);
            }
        }
        manager.finishLoading();

       /* Array<TextureAtlas> atlases = new Array<TextureAtlas>();
        for (TextureAtlas atlas : manager.getAll(TextureAtlas.class, atlases)) {
            System.out.println(atlas.getRegions());
        }*/

        initAnimations();
    }

    /**
     * @param textureName - the name of the texture
     * @param atlasName   - the name of the atlas with the .atlas extension
     * @return The specified texture region with the textureName in the specified atlas, throws null pointer
     * exception if it
     * cannot be found
     */
    public TextureRegion getTextureRegion(String textureName, String atlasName) throws NullPointerException {
        TextureRegion region = manager.get(atlasName, TextureAtlas.class).findRegion(textureName);
        if (region == null) {
            throw new NullPointerException("Region cannot be found in specified atlas");
        } else {
            return region;
        }
    }

    private void initAnimations() {
        //TODO fix this method such that it gets every texture region with the same name and packages them together
        // into an animation
        Array<TextureAtlas> atlases = new Array<TextureAtlas>();
        for (TextureAtlas atlas : manager.getAll(TextureAtlas.class, atlases)) {
            Array<TextureAtlas.AtlasRegion> regions = atlas.getRegions();
            HashMap<String, Integer> numRegions = new HashMap<String, Integer>();

            for (TextureAtlas.AtlasRegion region : regions) {
                if (numRegions.get(region.name) == null) {
                    numRegions.put(region.name, 1);
                } else {
                    numRegions.put(region.name, numRegions.get(region.name) + 1);
                }
            }

            // getting the String from the keys in order to see which ones have more than one frame
            // afterwards, grab the regions from the texture atlas with findRegions(String string) and put them into
            // animation
            for (String key : numRegions.keySet()) {
                if (numRegions.get(key) > 1) {
                    // 1/numRegions.get(key) in order to spread it out over one second
                    Animation<TextureRegion> animation = new Animation<TextureRegion>(1f/numRegions.get(key), atlas
                            .findRegions(key),
                                                                                      Animation.PlayMode.LOOP);
                    animations.put(key, animation);
                }
            }

            /*int startIndex = 0;
            for (int index = 0; index < regions.size - 1; index++) {
                if (!(regions.get(index).name.equals(regions.get(index + 1).name))) {
                    if (index - startIndex > 1) {
                        Array<TextureRegion> animationArray = new Array<TextureRegion>();
                        //uses index +1 in order to count the frame that triggered the conditional
                        for(int currentFrame = startIndex, lastFrame = index + 1; currentFrame < lastFrame;
                            currentFrame++){
                            animationArray.add(regions.get(currentFrame));
                        }
                        Animation<TextureRegion> animation = new Animation<TextureRegion>(.33f, animationArray);
                        animations.put(regions.get(index).name, animation);
                    }
                    //uses +1 so starts on the next frame after the last frame
                    startIndex = index + 1;
                }
            }*/
        }
    }

    public Animation getAnimation(String animationName) {
        return animations.get(animationName);
    }

    public <T> T get(String string, Class<T> type) {
        return manager.get(string, type);
    }


}
