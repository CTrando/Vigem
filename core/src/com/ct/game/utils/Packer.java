package com.ct.game.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import java.awt.*;

/**
 * Created by Cameron on 6/15/2017.
 */
public class Packer {
    public static void main(String[] args) {
        TexturePacker.process("rawassets", "android/assets", "tiles");
    }
}
