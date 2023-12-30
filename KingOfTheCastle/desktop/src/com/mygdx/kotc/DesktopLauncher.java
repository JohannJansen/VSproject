package com.mygdx.kotc;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.kotc.KingOfTheCastle;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	private static KingOfTheCastle koc = new KingOfTheCastle();

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("KOTC");
		config.setWindowedMode(koc.getScreenWidth(), koc.getScreenHeight());
		new Lwjgl3Application(new KingOfTheCastle(), config);
	}
}
