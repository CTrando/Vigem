package com.ct.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ct.game.Vigem;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	/*	config.foregroundFPS = 0;
		config.backgroundFPS = 0;
		config.vSyncEnabled = false;*/
		new LwjglApplication(new Vigem(), config);
	}
}
