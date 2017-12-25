package com.stanum;

import com.badlogic.gdx.Game;
import com.stanum.helper.AssetLoader;
import com.stanum.screens.GameScreen;

public class staNUMGame extends Game {

	final AdManager ads;

	public staNUMGame(AdManager adMob){
		this.ads=adMob;
	}
	
	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new GameScreen(ads));
	}

	@Override
	public void dispose(){
		super.dispose();
		AssetLoader.dispose();
	}
}
