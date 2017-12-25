package com.stanum.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by varunrana on 16/12/17.
 */

public class Tile extends Scrollable{

    private Random r;
    private boolean isScored = false;


    // When Pipe's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Tile(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        // Initialize a Random object for Random number generation
        r = new Random();
        boundingTile = new Rectangle();
    }

    @Override
    public void update(float delta){
        super.update(delta);
    }

    @Override
    public void reset(float newX) {
        //width = r.nextInt(90) + 15;
        // Call the reset method in the superclass (Scrollable)
        super.reset(newX);
        // Change the height to a random number
        //width = r.nextInt(90) + 15;
    }

    public boolean collides(Stanum stanum){
        if(position.y < stanum.getY()+stanum.getHeight()){
            return (Intersector.overlaps(stanum.getBoundingRectBottom(),boundingTile));
        }
        return false;
    }

    public void onRestart(float x, float scrollSpeed){
        position.x = x;
        velocity.x = scrollSpeed;
    }

    public boolean isScored(){
        return isScored;
    }

    public void setScored(boolean b){ isScored = b; }
}
