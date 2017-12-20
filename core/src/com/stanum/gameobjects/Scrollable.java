package com.stanum.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by varunrana on 16/12/17.
 */

public class Scrollable {

    // Protected is similar to private, but allows inheritance by subclasses.
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;

    protected float scrollSpeed;
    protected Rectangle boundingTile;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
        position = new Vector2(x, y);
        velocity = new Vector2(this.scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta) {
        //position.add(velocity.cpy().scl(delta));
        position.x += velocity.x;
        boundingTile.set(position.x,position.y,width,height);

        // If the Scrollable object is no longer visible:
        if (position.x + width < 0) {
            isScrolledLeft = true;
        }

    }

    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    public void stop(){
        velocity.x = 0;
    }

    // Getters for instance variables
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBoundingTile() { return boundingTile; }
}
