package com.stanum.gameobjects;

import com.badlogic.gdx.Game;
import com.stanum.gameworld.GameWorld;

/**
 * Created by varunrana on 16/12/17.
 */

public class ScrollHandler {

    // ScrollHandler will create all five objects that we need.
    private Tile tile1, tile2, tile3;

    private GameWorld gameWorld;

    private boolean isAlive = true;

    // ScrollHandler will use the constants below to determine
    // how fast we need to scroll and also determine
    // the size of the gap between each pair of pipes.

    // Capital letters are used by convention when naming constants.
    public static  final  float SCROLL_SPEED = -0.5f;
    public static final int Tile_GAP = 18;

    // Constructor receives a float that tells us where we need to create our
    // Grass and Pipe objects.
    public ScrollHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;
        tile1 = new Tile(0, 128, 95, 8, SCROLL_SPEED);
        tile2 = new Tile(109, 128, 95, 8, SCROLL_SPEED);
        tile3 = new Tile(218, 128, 95, 8, SCROLL_SPEED);
    }

    public void update(float delta) {

        if(isAlive) {

            tile1.update(delta);
            tile2.update(delta);
            tile3.update(delta);

            if (tile1.isScrolledLeft()) {
                gameWorld.addScore(1);
                tile1.reset(tile3.getTailX() + Tile_GAP);
            } else if (tile2.isScrolledLeft()) {
                gameWorld.addScore(1);
                tile2.reset(tile1.getTailX() + Tile_GAP);
            } else if (tile3.isScrolledLeft()) {
                gameWorld.addScore(1);
                tile3.reset(tile2.getTailX() + Tile_GAP);
            }
        }
    }

    public void stop(){
        this.isAlive = false;
    }

    public void collides(Stanum stanum){

        if(tile1.collides(stanum)||tile2.collides(stanum)||tile3.collides(stanum)){
            stanum.setY(tile1.position.y-15);
        }
    }

    public void onRestart(){
        isAlive = true;
        tile1.onRestart(0,SCROLL_SPEED);
        tile2.onRestart(109,SCROLL_SPEED);
        tile3.onRestart(218,SCROLL_SPEED);
    }

    // The getters for our five instance variables


    public Tile getTile1() {
        return tile1;
    }

    public Tile getTile2() {
        return tile2;
    }

    public Tile getTile3() {
        return tile3;
    }

}
