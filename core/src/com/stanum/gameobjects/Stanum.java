package com.stanum.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.stanum.controls.StanumControls;
import com.stanum.helper.AssetLoader;

/**
 * Created by varunrana on 14/12/17.
 */

public class Stanum {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width;
    private int height;
    private boolean jumped = false;
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;
    private boolean isAlive = true;

    private StanumControls jumpControl;
    private Rectangle boundingRectBottom;
    private Rectangle boundingRectUpper;

    public Stanum(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 1.5f);
        acceleration = new Vector2(0, 460);
        jumpControl = new StanumControls(181,50,22,26);
        boundingRectBottom = new Rectangle();
        boundingRectUpper = new Rectangle();

    }

    public void update(float delta) {

        if(isAlive) {

            boundingRectUpper.set(position.x + 3, position.y + 2, 9, 9);
            boundingRectBottom.set(position.x + 4, position.y + 15, 7, 2);

            position.x  += -0.5f;
            //  Gdx.app.log("velocity:",""+velocity.y);
            //  Gdx.app.log("position:",""+position.y);

            //Moves character


            // if (velocity.y > 200) {
            //   velocity.y = 200;
            //}
            // Updates Y Position
            position.y += velocity.y;
            //  if (position.y + velocity.y >= 113) { position.y = 113; velocity.y = 0; }
            // else { position.y += velocity.y; }

            // Handles Jumping
            if (jumped == true) {
                velocity.y += 1;

                if (position.y + velocity.y >= 113) {
                    jumped = false;
                }

            }

        }
    }

    public void movement(float speedX){
        velocity.x = speedX;
        position.x += velocity.x;
    }

    public void die(){
        isAlive = false;
        velocity.y = 0;
    }

    public void jump(){
            velocity.y = -7;
            AssetLoader.jump.play();
            jumped = true;
    }

    public void onRestart(int x, int y){
        position.x = x;
        position.y = y;
        velocity.x = 0;
        velocity.y = 1.5f;
        isAlive = true;
        isMovingRight = false;
        isMovingLeft = false;
    }

    public StanumControls getJumpControl(){ return jumpControl; }

    public Rectangle getBoundingRectBottom() { return boundingRectBottom; }

    public Rectangle getBoundingRectUpper() { return boundingRectUpper; }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void setY(float y){ position.y = y; }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean isAlive(){ return isAlive; }

    public boolean getJumped(){ return jumped; }

    public void setPositionX(int x) { position.x = x; }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }
}
