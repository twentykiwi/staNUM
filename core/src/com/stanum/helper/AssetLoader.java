package com.stanum.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by varunrana on 14/12/17.
 */

public class AssetLoader {

    public static Animation stanumRightAnimation, stanumLeftAnimation;
    public static Texture die, rightStanum, rightJump, rightWalkA, rightWalkB, rightWalkC, rightWalkD, rightWalkE, rightWalkF,  leftStanum, leftJump, leftWalkA, leftWalkB, leftWalkC, leftWalkD, leftWalkE, leftWalkF;
    public static Texture background, tile, slider, circle, ball, thorns;
    public static TextureRegion thorn;
    public static Texture playButton, settingButton, scoreboardButton, exitButton;
    public static BitmapFont font, shadow;
    private static Preferences prefs;


    public static void load() {
        rightStanum = new Texture(Gdx.files.internal("data/right_move/idle.PNG"));
        rightJump = new Texture(Gdx.files.internal("data/right_move/jump.PNG"));
        rightWalkA = new Texture(Gdx.files.internal("data/right_move/walk_a.PNG"));
        rightWalkB = new Texture(Gdx.files.internal("data/right_move/walk_b.PNG"));
        rightWalkC = new Texture(Gdx.files.internal("data/right_move/walk_c.PNG"));
        rightWalkD = new Texture(Gdx.files.internal("data/right_move/walk_d.png"));
        rightWalkE = new Texture(Gdx.files.internal("data/right_move/walk_e.png"));
        rightWalkF = new Texture(Gdx.files.internal("data/right_move/walk_f.png"));

        leftStanum = new Texture(Gdx.files.internal("data/left_move/idle.PNG"));
        leftJump = new Texture(Gdx.files.internal("data/left_move/jump.PNG"));
        leftWalkA = new Texture(Gdx.files.internal("data/left_move/walk_a.PNG"));
        leftWalkB = new Texture(Gdx.files.internal("data/left_move/walk_b.PNG"));
        leftWalkC = new Texture(Gdx.files.internal("data/left_move/walk_c.PNG"));
        leftWalkD = new Texture(Gdx.files.internal("data/left_move/walk_d.PNG"));
        leftWalkE = new Texture(Gdx.files.internal("data/left_move/walk_e.png"));
        leftWalkF = new Texture(Gdx.files.internal("data/left_move/walk_f.png"));

        playButton = new Texture(Gdx.files.internal("data/buttons/play.png"));
        settingButton = new Texture(Gdx.files.internal("data/buttons/setting.png"));
        scoreboardButton = new Texture(Gdx.files.internal("data/buttons/scoreboard.png"));
        exitButton = new Texture(Gdx.files.internal("data/buttons/exit.png"));

        die = new Texture(Gdx.files.internal("data/dead.PNG"));

        ball = new Texture(Gdx.files.internal("data/ball.png"));

        background = new Texture(Gdx.files.internal("data/background.jpg"));
        tile = new Texture(Gdx.files.internal("data/tile.png"));

        slider = new Texture(Gdx.files.internal("data/slider.png"));
        circle = new Texture(Gdx.files.internal("data/circle1.png"));

        thorns = new Texture(Gdx.files.internal("data/thorns.png"));

        thorn = new TextureRegion(thorns,0,0,931,1024);

        stanumRightAnimation = new Animation(0.1f, rightWalkA, rightWalkB, rightWalkC, rightWalkD, rightWalkE, rightWalkF);
        stanumRightAnimation.setPlayMode(Animation.PlayMode.LOOP);
        stanumLeftAnimation = new Animation(0.1f, leftWalkA, leftWalkB, leftWalkC, leftWalkD, leftWalkE, leftWalkF);
        stanumLeftAnimation.setPlayMode(Animation.PlayMode.LOOP);

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.22f, -.22f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.22f, -.22f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("staNUM");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose(){
        die.dispose();
        rightStanum.dispose();
        rightJump.dispose();
        rightWalkA.dispose();
        rightWalkB.dispose();
        rightWalkC.dispose();
        rightWalkD.dispose();
        rightWalkE.dispose();
        rightWalkF.dispose();
        leftStanum.dispose();
        leftJump.dispose();
        leftWalkA.dispose();
        leftWalkB.dispose();
        leftWalkC.dispose();
        leftWalkD.dispose();
        leftWalkE.dispose();
        leftWalkF.dispose();
        ball.dispose();
        background.dispose();
        tile.dispose();
        circle.dispose();
        slider.dispose();
        thorns.dispose();
        font.dispose();
        shadow.dispose();
        playButton.dispose();
        settingButton.dispose();
        scoreboardButton.dispose();
        exitButton.dispose();
    }
}
