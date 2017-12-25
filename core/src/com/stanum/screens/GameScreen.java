package com.stanum.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.stanum.AdManager;
import com.stanum.gameworld.GameRenderer;
import com.stanum.gameworld.GameWorld;
import com.stanum.helper.InputHelper;

/**
 * Created by varunrana on 14/12/17.
 */

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    public AdManager ads;

    public GameScreen(AdManager ads) {
        float screenWidth = Gdx.graphics.getHeight();
        float screenHeight = Gdx.graphics.getWidth();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(ads, midPointY);
        renderer = new GameRenderer(world, (int)gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHelper(ads, world));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime +=delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
