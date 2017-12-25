package com.stanum.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by varunrana on 18/12/17.
 */

public class Thorns {
    private int positionX;
    private int positionY;
    private float rotation;
    private int width;
    private int height;

    private Circle boundingThorns;
    private Rectangle groundCheck;

    public Thorns(int x, int y, int width, int height){
        positionX = x;
        positionY = y;
        this.width = width;
        this.height = height;
        boundingThorns = new Circle();
        groundCheck = new Rectangle(0,136,204,5);
    }

    public void update(float delta){
        boundingThorns.set(positionX+15,positionY+15,15);
        rotation += 220*delta;
    }

    public  boolean collides(Stanum stanum){
        if(Intersector.overlaps(boundingThorns, stanum.getBoundingRectUpper())){
            return true;
        }
        return false;
    }

    public boolean collision(Stanum stanum){
        if(Intersector.overlaps(groundCheck, stanum.getBoundingRectUpper())){
            return true;
        }
        return false;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public float getRotation() {
        return rotation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Circle getBoundingThorns(){ return boundingThorns; }
}
