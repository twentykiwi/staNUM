package com.stanum.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by varunrana on 14/12/17.
 */

public class StanumControls{

    private Rectangle bounds;
    private boolean isPressed = false;
    private int  x;
    private int y;
    private int width;
    private int height;
    private int radius;
    private float inputX = (Gdx.graphics.getWidth()/204.0f);
    private float inputY = (Gdx.graphics.getHeight()/136.0f);

    public StanumControls(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y-4, width+2, height+8);
    }

    public boolean isTouching(){
        if(Gdx.input.isTouched()) {
            if (isTouchDown(Gdx.input.getX() / inputX, Gdx.input.getY() / inputY)) {
                return true;
            } else {
                return false;
            }
        }
        else{ return false; }
    }

    public boolean isClicked(float screenX, float screenY) {
        return this.bounds.contains(screenX, screenY);
    }

    public boolean isTouchDown(float screenX, float screenY) {
        if (!this.bounds.contains(screenX, screenY)) {
            return false;
        }
        this.isPressed = true;
        return true;
    }

    public boolean isTouchUp(float screenX, float screenY) {
        if (this.bounds.contains(screenX, screenY) && this.isPressed) {
            this.isPressed = false;
            return true;
        }
        this.isPressed = false;
        return false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getHeight() { return  this.height; }

    public  int getWidth() { return  this.width; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds(){ return bounds; }
}
