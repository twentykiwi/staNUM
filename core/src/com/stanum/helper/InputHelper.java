package com.stanum.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.stanum.AdManager;
import com.stanum.gameobjects.Stanum;
import com.stanum.gameworld.GameWorld;

/**
 * Created by varunrana on 14/12/17.
 */

public class InputHelper implements InputProcessor{

    Stanum myStanum;
    GameWorld myWorld;
    AdManager ads;
    private boolean menuLevel1, menuLevel2;

    private float inputX = (Gdx.graphics.getWidth() / 204.0f);
    private float inputY = (Gdx.graphics.getHeight() / 136.0f);

    public InputHelper(AdManager ads, GameWorld world){
        this.ads = ads;
        myWorld = world;
        myStanum = myWorld.getStanum();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
        menuLevel1 = true;
        menuLevel2 = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            ads.hide();
            myWorld.menu();
            myWorld.setScoreboard(false);
            myWorld.setSettings(false);
            menuLevel1 = true;
            menuLevel2 = false;
            return true;
        }
        if(keycode == Input.Keys.MENU){
            myWorld.menu();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
       /* if(myWorld.isMenu()){
            if(myWorld.getPlay().isTouchDown(screenX / inputX, screenY / inputY)){
                myWorld.ready();
            }
        }*/

        if(myWorld.isMenu()){

            if(myWorld.isMenu() && menuLevel1 ) {
                if (myWorld.getPlay().isTouchDown(screenX / inputX, screenY / inputY)) {
                   // myWorld.ready();
                }
                if (myWorld.getExit().isTouchDown(screenX / inputX, screenY / inputY)) {
                   // AssetLoader.dispose();
                   // Gdx.app.exit();
                }
                if (myWorld.getScoreboard().isTouchDown(screenX / inputX, screenY / inputY)) {
                   // menuLevel2=true;
                   // menuLevel1 = false;
                   // myWorld.setScoreboard(true);
                }
                if (myWorld.getSettings().isTouchDown(screenX / inputX, screenY / inputY)) {
                   // menuLevel2=true;
                   // menuLevel1 = false;
                   // myWorld.setSettings(true);
                   // myWorld.setControls(true);
                }
            }
            if(myWorld.isMenu() && menuLevel2 ) {
                // myWorld.setControls(true);
                if (myWorld.getSoundButton().isTouchDown(screenX / inputX, screenY / inputY)) {
                   // myWorld.setAudio();
                }
                if (myWorld.getControlButton().isTouchDown(screenX / inputX, screenY / inputY)) {
                   // myWorld.setControls(false);
                }
            }

        }

        if(myWorld.isReady()) { ads.hide(); myWorld.start(); }
            if (myWorld.getSlider1().isTouchDown(screenX / inputX, screenY / inputY)) {
                myWorld.getWheel().setY((int) ((screenY / inputY)-5));

                if (myWorld.getWheel().getY() >= 69) { myWorld.getWheel().setY(69); }
                if (myWorld.getWheel().getY() <= 39) { myWorld.getWheel().setY(39); }

            }
            if (myStanum.getJumpControl().isTouchDown(screenX / inputX, screenY / inputY)) {
                if (myWorld.getScroller().getTile1().collides(myStanum) || myWorld.getScroller().getTile2().collides(myStanum) || myWorld.getScroller().getTile3().collides(myStanum))
                    myStanum.jump();
            }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(myWorld.getSlider1().isTouchDown(screenX/inputX, screenY/inputY)){
            myWorld.setDragging(false);
        }
        if(myWorld.isMenu()){

            if(myWorld.isMenu() && menuLevel1 ) {
                if (myWorld.getPlay().isTouchUp(screenX / inputX, screenY / inputY)) {
                    ads.hide();
                    myWorld.ready();
                }
                if (myWorld.getExit().isTouchUp(screenX / inputX, screenY / inputY)) {
                    AssetLoader.dispose();
                    Gdx.app.exit();
                }
                if (myWorld.getScoreboard().isTouchUp(screenX / inputX, screenY / inputY)) {
                    ads.show();
                    menuLevel2=true;
                    menuLevel1 = false;
                    myWorld.setScoreboard(true);
                }
                if (myWorld.getSettings().isTouchUp(screenX / inputX, screenY / inputY)) {
                    ads.show();
                    menuLevel2=true;
                    menuLevel1 = false;
                    myWorld.setSettings(true);
                    myWorld.setControls(true);
                }
            }
            if(myWorld.isMenu() && menuLevel2 ) {
                // myWorld.setControls(true);
                if (myWorld.getSoundButton().isTouchUp(screenX / inputX, screenY / inputY)) {
                    myWorld.setAudio();
                }
                if (myWorld.getControlButton().isTouchUp(screenX / inputX, screenY / inputY)) {
                    ads.hide();
                    myWorld.setControls(false);
                }
            }

        }
        if(myWorld.isGameOver()){
            if(myWorld.getYesButton().isTouchDown(screenX/inputX, screenY/inputY)){
                ads.hide();
                myWorld.ready();
                myWorld.restart();

            }
            if(myWorld.getNoButton().isTouchDown(screenX/inputX, screenY/inputY)){
                ads.hide();
                myWorld.menu();
            }
        }
        if(myWorld.isHighScore()){
            if(myWorld.getMenuButton().isTouchDown(screenX/inputX, screenY/inputY)){
                ads.hide();
                myWorld.menu();
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
      //  Gdx.app.log("touch:" ,""+myWorld.getSlider2().isTouchUp(screenX/inputX, screenY/inputY));
        myWorld.setTouching(myWorld.getSlider2().isTouchUp(screenX/inputX, screenY/inputY));
       if(myWorld.getSlider2().isTouchDown(screenX/inputX, screenY/inputY)){
           myWorld.setDragging(true);
           myWorld.getWheel().setY((int)(screenY/inputY)-5);
           if(myWorld.getWheel().getY()>=69) { myWorld.getWheel().setY(69); }
           if(myWorld.getWheel().getY()<=39) { myWorld.getWheel().setY(39); }

       }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
