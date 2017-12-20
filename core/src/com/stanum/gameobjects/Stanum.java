package com.stanum.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.stanum.controls.StanumControls;

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
    private boolean jumpCompleted = true;
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;
    private boolean isAlive = true;

    private float inputX = (Gdx.graphics.getWidth() / 204.0f);
    private float inputY = (Gdx.graphics.getHeight() / 136.0f);

    private StanumControls jumpControl;
    private Rectangle boundingRectBottom;
    private Rectangle boundingRectUpper;

    public Stanum(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 3);
        acceleration = new Vector2(0, 460);
        jumpControl = new StanumControls(181,50,22,26);
       // control = new StanumControls(4,54,16,18);
        //left_control = new StanumControls(5,113,22,22);
       // right_control = new StanumControls(36,113,22,22);
       // jump_control = new StanumControls(176,113,22,22);
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

            // velocity.add(acceleration.cpy().scl(delta));
            // position.add(velocity.cpy().scl(delta));

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
                jumpCompleted = false;

                if (position.y + velocity.y >= 113) {
                    jumped = false;
                    jumpCompleted = true;
                }

            }

        }
    }

    public void movement(int speedX){
        velocity.x = speedX;
        position.x += velocity.x;
    }

    public void die(){
        isAlive = false;
        velocity.y = 0;
    }

    public void jump(){
        if(jumpCompleted) {
            velocity.y = -7;
            jumped = true;
        }
    }

    public void onRestart(int x, int y){
        position.x = x;
        position.y = y;
        velocity.x = 0;
        velocity.y = 3;
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

    public void setVelocityY(int velocityY ){ velocity.y = velocityY; }

    public void setY(float y){ position.y = y; }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setJumped(boolean jumped){ this.jumped = jumped; }

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
