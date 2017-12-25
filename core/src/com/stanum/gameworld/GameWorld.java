package com.stanum.gameworld;

import com.stanum.AdManager;
import com.stanum.controls.ControlWheel;
import com.stanum.controls.StanumControls;
import com.stanum.gameobjects.Ball;
import com.stanum.gameobjects.ScrollHandler;
import com.stanum.gameobjects.Stanum;
import com.stanum.gameobjects.Thorns;
import com.stanum.helper.AssetLoader;
import com.stanum.helper.UpdateHighscore;
import com.stanum.simplebuttons.SimpleButtons;
import com.stanum.simplebuttons.YesNoButton;

/**
 * Created by varunrana on 14/12/17.
 */

public class GameWorld {

    private Stanum stanum;
    private Thorns thorns;
    private StanumControls slider1, slider2;
    private ControlWheel wheel;
    private ScrollHandler scroller;
    private Ball ball1, ball2;
    private YesNoButton yesButton, noButton, soundButton, controlButton, menuButton;
    private int score = 0;
    private UpdateHighscore onlineHighscore;
    private String data;
    private AdManager ads;

    private boolean isAudio = true;
    private boolean isControls = true;

    //Menu Buttons
    private SimpleButtons play, settings, scoreboard, exit;

    //Game state
    private GameState currentState;
    public enum GameState{
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, PAUSE
    }

    private boolean isDragging = false;
    private boolean isTouching = false;
    private boolean isScoreboard = false;
    private boolean isSettings = false;

    public GameWorld(AdManager ads, int midPointY) {
        this.ads = ads;
        ads.hide();
        currentState = GameState.MENU;
        onlineHighscore = new UpdateHighscore("score");

        stanum = new Stanum(138,113,15, 17);
        thorns = new Thorns(-21,101,30,30);
        wheel = new ControlWheel(4,54,16,18);
        slider1 = new StanumControls(4,26,16,68);
        slider2 = new StanumControls(1,4,19,128);
        scroller = new ScrollHandler(this, midPointY);
        ball1 = new Ball(10,10,13,14, false);
        ball2 = new Ball(181,25,13,14, true);

        play = new SimpleButtons(85,50,20,22);
        settings = new SimpleButtons(103,61,20,22);
        scoreboard = new SimpleButtons(85,73,20,22);
        exit = new SimpleButtons(103,84,20,22);
        yesButton = new YesNoButton(62,88,29,16);
        noButton = new YesNoButton(109,88,29,16);
        soundButton = new YesNoButton(70, 65, 20, 22);
        controlButton = new YesNoButton(111, 65, 20, 22);
        menuButton = new YesNoButton(75,102,55,18);
    }

    public void update(float delta) {

        switch(currentState){
            case MENU:
            break;
            case READY: updateReady(delta);
                break;

            case RUNNING: updateRunning(delta);
            break;
            case PAUSE:
                break;

            default: break;
        }

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

           if (ball1.collides(stanum) || ball2.collides(stanum) || thorns.collides(stanum) || thorns.collision(stanum)) {
                AssetLoader.dead.play();
                stanum.die();
                scroller.stop();
                ads.show();
                currentState = GameState.GAMEOVER;
                if(score > AssetLoader.getHighScore()){
                    AssetLoader.setHighScore(score);
                    currentState = GameState.HIGHSCORE;
                    data = onlineHighscore.update(score);
                }
            }
        }

    }

    public void changeStanumPosition(){
       // if(wheel.getY()>63) {
            if (wheel.getY()+9 > 72) {
                if(stanum.getJumped())
                    stanum.movement(-2.2f);
                else
                    stanum.movement(-1f);
                if(stanum.getX()<=1){
                    stanum.setPositionX(1);
                }
                stanum.setMovingLeft(true);
                stanum.setMovingRight(false);
            }
       // }
        //if(wheel.getY()<63){
            if (wheel.getY() < 54 ) {
                if(stanum.getJumped())
                    stanum.movement(2.2f);
                else
                    stanum.movement(1f);
                if(stanum.getX()>=189){
                    stanum.setPositionX(189);
                }
                stanum.setMovingRight(true);
                stanum.setMovingLeft(false);
            }
        //}
    }


    public void restart(){
        score = 0;
        stanum.onRestart(138,113);
        scroller.onRestart();
        ball1.onRestart(10,10,false);
        ball2.onRestart(181,25,true);
        wheel.onRestart();
    }

    public void menu(){ currentState = GameState.MENU; restart(); }
    public boolean isRunning(){ return currentState == GameState.RUNNING; }
    public boolean isMenu(){ return currentState == GameState.MENU; }
    public boolean isHighScore(){ return currentState == GameState.HIGHSCORE; }
    public boolean isGameOver(){ return currentState == GameState.GAMEOVER; }
    public boolean isReady(){ return currentState == GameState.READY; }
    public boolean isPause(){ return currentState == GameState.PAUSE; }
    public void pause(){ currentState = GameState.READY;}
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
    public SimpleButtons getPlay(){ return play; }
    public SimpleButtons getSettings(){ return settings; }
    public SimpleButtons getScoreboard(){ return scoreboard; }
    public  SimpleButtons getExit(){ return exit; }
    public boolean isScoreboard() { return isScoreboard; }
    public boolean isSettings() { return isSettings; }
    public void setScoreboard(boolean scoreboard) { isScoreboard = scoreboard; }
    public void setSettings(boolean settings) { isSettings = settings; }
    public YesNoButton getYesButton(){ return yesButton; }
    public YesNoButton getNoButton(){ return noButton; }
    public boolean isAudio() { return isAudio; }
    public void setAudio() { isAudio = !isAudio; }
    public boolean isControls() { return isControls; }
    public void setControls(boolean controls) { isControls = controls; }
    public YesNoButton getSoundButton(){ return soundButton; }
    public YesNoButton getControlButton(){ return  controlButton; }
    public YesNoButton getMenuButton() { return  menuButton; }
    public UpdateHighscore getOnlineHighscore(){ return onlineHighscore; }
    public String getData(){ return data; }
}
