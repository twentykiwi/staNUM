package com.stanum.simplebuttons;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by varunrana on 22/12/17.
 */

public class YesNoButton {
    private Rectangle bounding;
    private boolean isPressed = false;

    public YesNoButton(int x, int y, int width, int height) {
        bounding = new Rectangle(x, y, width, height);
    }

    public boolean isTouchDown(float screenX, float screenY) {

        if (bounding.contains(screenX, screenY)) {
            isPressed = true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(float screenX, float screenY) {

        // It only counts as a touchUp if the button is in a pressed state.
        if (bounding.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }
        return false;
    }

    public Rectangle getBounding(){ return bounding; }

}
