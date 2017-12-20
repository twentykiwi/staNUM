package com.stanum.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.stanum.gameobjects.Stanum;
import com.stanum.gameworld.GameWorld;

/**
 * Created by varunrana on 14/12/17.
 */

public class InputHelper implements InputProcessor{

    Stanum myStanum;
    GameWorld myWorld;

    private float inputX = (Gdx.graphics.getWidth() / 204.0f);
    private float inputY = (Gdx.graphics.getHeight() / 136.0f);

    public InputHelper(GameWorld world){
        myWorld = world;
        myStanum = myWorld.getStanum();
    }

    @Override
    public boolean keyDown(int keycode) {
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
        if(myWorld.isReady()) { myWorld.start(); }
            if (myWorld.getSlider1().isTouchDown(screenX / inputX, screenY / inputY)) {
                myWorld.getWheel().setY((int) (screenY / inputY));

                if (myWorld.getWheel().getY() >= 74) { myWorld.getWheel().setY(74); }
                if (myWorld.getWheel().getY() <= 34) { myWorld.getWheel().setY(34); }

            }
            if (myStanum.getJumpControl().isTouchDown(screenX / inputX, screenY / inputY)) {
                myStanum.jump();
            }
            if(myWorld.isGameOver()){ myWorld.restart(); }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(myWorld.getSlider1().isTouchDown(screenX/inputX, screenY/inputY)){
            myWorld.setDragging(false);
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
           if(myWorld.getWheel().getY()>=74) { myWorld.getWheel().setY(74); }
           if(myWorld.getWheel().getY()<=34) { myWorld.getWheel().setY(34); }

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
