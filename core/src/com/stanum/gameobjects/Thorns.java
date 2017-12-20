package com.stanum.gameobjects;

/**
 * Created by varunrana on 18/12/17.
 */

public class Thorns {
    private int positionX;
    private int positionY;
    private float rotation;
    private int width;
    private int height;

    public Thorns(int x, int y, int width, int height){
        positionX = x;
        positionY = y;
        this.width = width;
        this.height = height;
    }

    public void update(float delta){
        rotation += 220*delta;
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
}
