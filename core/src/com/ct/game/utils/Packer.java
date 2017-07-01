package com.ct.game.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import java.awt.*;

/**
 * Created by Cameron on 6/15/2017.
 */
public class Packer {
    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.edgePadding = false;
        settings.stripWhitespaceX = true;
        settings.stripWhitespaceY = true;
        settings.paddingX = 0;
        settings.paddingY = 0;
        TexturePacker.process(settings, "rawassets", "core/assets", "tiles");
        AssetLister.main(args);
    }
}
