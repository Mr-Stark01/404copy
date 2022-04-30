package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "404";
		config.width = 480;
		config.height = 320;
		config.foregroundFPS=60;
		config.fullscreen = true;

		new LwjglApplication(new MyGdxGame(), config);
	}
}
