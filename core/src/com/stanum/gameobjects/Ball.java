package com.stanum.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

/**
 * Created by varunrana on 16/12/17.
 */

public class Ball{
    private float x;
    private float y;
    private float velocityX;
    private float velocityY;
    private float accelerationX;
    private float accelerationY;
    private boolean swap;
    private int width;
    private int height;
    private Circle boundingCircle;

    public Ball(float x, float y, int width, int height, boolean swap){
        this.swap = swap;
        this.x = x;
        this.y = y;
        velocityY = 0.0f;
        velocityX = 0.5f;
        accelerationY = 0.2f;
        this.width = width;
        this.height = height;
        boundingCircle = new Circle();
    }
    public void update(){
        boundingCircle.set(x+7,y+8,5);

        velocityY += accelerationY;
        y += velocityY;
        if(y >= 128-height){
            y = 128-height-1;
            velocityY = -velocityY;
        }
        if(x >= 204-width || x <= 0){
            swap = !swap;
        }
        if(swap){
            x  -= velocityX;
        }
        if(!swap){
            x += velocityX;
        }
    }

    public void onRestart(int x, int y, boolean swap){
        this.x = x;
        this.y = y;
        velocityY = 0;
        this.velocityX = 0.5f;
        accelerationY = 0.2f;
        this.swap = swap;
    }

    public  boolean collides(Stanum stanum){
        if(Intersector.overlaps(boundingCircle, stanum.getBoundingRectUpper())){
            return true;
        }
        return false;
    }

    public  boolean collision(Ball ball){
        if(Intersector.overlaps(boundingCircle,ball.boundingCircle)){
            return true;
        }
        return false;
    }

    public float getX(){ return x; }

    public float getY(){ return y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public Circle getBoundingCircle(){ return boundingCircle; }

    public void setSwap(){ swap = !swap; }

   // public boolean getSwap(){ return swap; }
}
