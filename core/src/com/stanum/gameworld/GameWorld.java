package com.stanum.gameworld;

import com.badlogic.gdx.Gdx;
import com.stanum.controls.ControlWheel;
import com.stanum.controls.StanumControls;
import com.stanum.gameobjects.Ball;
import com.stanum.gameobjects.ScrollHandler;
import com.stanum.gameobjects.Stanum;
import com.stanum.gameobjects.Thorns;
import com.stanum.helper.AssetLoader;

/**
 * Created by varunrana on 14/12/17.
 */

public class GameWorld {

    private Stanum stanum;
    private Thorns thorns;
    private StanumControls slider1, slider2, jumpControl;
    private ControlWheel wheel;
    private ScrollHandler scroller;
    private Ball ball1, ball2;
    private int score = 0;

    private GameState currentState;
    public enum GameState{
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    private boolean isDragging = false;
    private boolean isTouching = false;

    public GameWorld(int midPointY) {
        // Initialize bird here
        currentState = GameState.MENU;
        stanum = new Stanum(118,113,15, 17);
        thorns = new Thorns(-21,101,30,30);
        wheel = new ControlWheel(4,54,16,18);
        slider1 = new StanumControls(4,26,16,68);
        slider2 = new StanumControls(1,4,19,128);
        scroller = new ScrollHandler(this, midPointY);
        ball1 = new Ball(10,10,13,14, false);
        ball2 = new Ball(181,25,13,14, true);
    }

    public void update(float delta) {

        switch(currentState){
            case MENU: menu();
            break;
            case READY: updateReady(delta);
                break;

            case RUNNING: updateRunning(delta);
            break;

            default: break;
        }

    }

    private void menu(){

    }

    private void updateReady(float delta){

    }
    public void updateRunning(float delta){
        if(delta > .15f) { delta = .15f; }

        if(stanum.isAlive()) {
            stanum.update(delta);
            scroller.collides(stanum);

            if (isDragging && slider2.isTouching()) {
                changeStanumPosition();
            } else {
                stanum.setMovingRight(false);
                stanum.setMovingLeft(false);
            }

            scroller.update(delta);
            ball1.update();
            ball2.update();
            thorns.update(delta);

            if (ball1.collides(stanum) || ball2.collides(stanum)) {
                stanum.die();
                scroller.stop();
                currentState = GameState.GAMEOVER;
                if(score > AssetLoader.getHighScore()){
                    AssetLoader.setHighScore(score);
                    currentState = GameState.HIGHSCORE;
                }
            }

            if(stanum.getY() > 138){
                stanum.die();
                scroller.stop();
                currentState = GameState.GAMEOVER;
                if(score > AssetLoader.getHighScore()){
                    AssetLoader.setHighScore(score);
                    currentState = GameState.HIGHSCORE;
                }
            }
        }

    }

    public void changeStanumPosition(){
        if(wheel.getY()>60) {
            if (wheel.getY() > 68) {
                stanum.setMovingLeft(true);
                stanum.setMovingRight(false);
                if(stanum.getJumped())
                    stanum.movement(-2);
                else
                    stanum.movement(-1);
                if(stanum.getX()<=1){
                    stanum.setPositionX(1);
                }
            }
        }
        if(wheel.getY()<60){
            if (wheel.getY() < 44 ) {
                stanum.setMovingRight(true);
                stanum.setMovingLeft(false);
                if(stanum.getJumped())
                    stanum.movement(2);
                else
                    stanum.movement(1);
                if(stanum.getX()>=189){
                    stanum.setPositionX(189);
                }
            }
        }
    }


    public void restart(){
        currentState = GameState.READY;
        score = 0;
        stanum.onRestart(118,113);
        scroller.onRestart();
        currentState = GameState.READY;
        ball1.onRestart(10,10,false);
        ball2.onRestart(181,25,true);
        wheel.onRestart();
    }

    public boolean isRunning(){ return currentState == GameState.RUNNING; }
    public boolean isMenu(){ return currentState == GameState.MENU; }
    public boolean isHighScore(){ return currentState == GameState.HIGHSCORE; }
    public boolean isGameOver(){ return currentState == GameState.GAMEOVER; }
    public boolean isReady(){ return currentState == GameState.READY; }
    public void start(){ currentState = GameState.RUNNING; }
    public void ready(){ currentState = GameState.READY; }
    public Stanum getStanum() { return stanum; }
    public StanumControls getSlider1() { return slider1; }
    public StanumControls getSlider2() { return slider2; }
    public ControlWheel getWheel(){ return wheel; }
    public ScrollHandler getScroller() { return scroller; }
    public Ball getBall1() { return ball1; }
    public Ball getBall2() { return ball2; }
    public Thorns getThorns(){ return thorns; }
    public int getScore() { return score; }
    public void addScore(int increment){ score +=increment; }
    public boolean isDragging() {
        return isDragging;
    }
    public void setDragging(boolean dragging) {
        isDragging = dragging;
    }
    public void setTouching(boolean touching) {
        isTouching = touching;
    }
}
