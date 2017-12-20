package com.stanum;

import com.badlogic.gdx.Game;
import com.stanum.helper.AssetLoader;
import com.stanum.screens.GameScreen;

public class staNUMGame extends Game {
	
	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose(){
		super.dispose();
		AssetLoader.dispose();
	}
}
