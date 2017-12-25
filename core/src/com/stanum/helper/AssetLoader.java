package com.stanum.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by varunrana on 14/12/17.
 */

public class AssetLoader {

    public static Animation stanumRightAnimation, stanumLeftAnimation;
    public static Texture die, rightStanum, rightJump, rightWalkA, rightWalkB, rightWalkC, rightWalkD, leftWalkA, leftWalkB, leftWalkC, leftWalkD;
    public static Texture background, tile, slider, circle, ball, thorns;
    public static TextureRegion thorn;
    public static Texture playButton, settingButton, scoreboardButton, exitButton;
    public static Texture sboardBackground, button, speakers, mute, controls,upArrow, downArrow, rightArrow, leftArrow;
    public static BitmapFont font, shadow, small, large;
    public static Sound jump, dead;
    public static Preferences prefs;


    public static void load() {
        rightStanum = new Texture(Gdx.files.internal("data/right_move/idle.PNG"));
        rightJump = new Texture(Gdx.files.internal("data/right_move/jump.PNG"));
        rightWalkA = new Texture(Gdx.files.internal("data/right_move/walk_a.PNG"));
        rightWalkB = new Texture(Gdx.files.internal("data/right_move/walk_b.PNG"));
        rightWalkC = new Texture(Gdx.files.internal("data/right_move/walk_c.PNG"));
        rightWalkD = new Texture(Gdx.files.internal("data/right_move/walk_d.PNG"));

        leftWalkA = new Texture(Gdx.files.internal("data/left_move/walk_a.PNG"));
        leftWalkB = new Texture(Gdx.files.internal("data/left_move/walk_b.PNG"));
        leftWalkC = new Texture(Gdx.files.internal("data/left_move/walk_c.PNG"));
        leftWalkD = new Texture(Gdx.files.internal("data/left_move/walk_d.PNG"));


        playButton = new Texture(Gdx.files.internal("data/buttons/play.png"));
        settingButton = new Texture(Gdx.files.internal("data/buttons/setting.png"));
        scoreboardButton = new Texture(Gdx.files.internal("data/buttons/scoreboard.png"));
        exitButton = new Texture(Gdx.files.internal("data/buttons/exit.png"));
        button = new Texture(Gdx.files.internal("data/minus.png"));

        die = new Texture(Gdx.files.internal("data/dead.PNG"));

        ball = new Texture(Gdx.files.internal("data/ball.png"));

        background = new Texture(Gdx.files.internal("data/background.jpg"));
        tile = new Texture(Gdx.files.internal("data/tile.png"));

        slider = new Texture(Gdx.files.internal("data/slider.png"));
        circle = new Texture(Gdx.files.internal("data/circle13.png"));



        thorns = new Texture(Gdx.files.internal("data/settings.png"));
        thorn = new TextureRegion(thorns,0,0,512,512);

        speakers  = new Texture(Gdx.files.internal("data/speakers.png"));
        mute = new Texture(Gdx.files.internal("data/mute.png"));
        controls = new Texture(Gdx.files.internal("data/controls.png"));

        upArrow = new Texture(Gdx.files.internal("data/up-arrow.png"));
        downArrow = new Texture(Gdx.files.internal("data/down-arrow.png"));
        rightArrow = new Texture(Gdx.files.internal("data/right-arrow.png"));
        leftArrow = new Texture(Gdx.files.internal("data/left-arrow.png"));


        sboardBackground = new Texture(Gdx.files.internal("data/sboardBackground.png"));

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        jump = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));

        stanumRightAnimation = new Animation(0.1f, rightWalkA, rightWalkB, rightWalkC, rightWalkD);
        stanumRightAnimation.setPlayMode(Animation.PlayMode.LOOP);
        stanumLeftAnimation = new Animation(0.1f, leftWalkA, leftWalkB, leftWalkC, leftWalkD);
        stanumLeftAnimation.setPlayMode(Animation.PlayMode.LOOP);

        font = new BitmapFont(Gdx.files.internal("data/data.fnt"));
        font.getData().setScale(0.17f, -0.17f);
        shadow = new BitmapFont(Gdx.files.internal("data/white.fnt"));
        shadow.getData().setScale(0.17f, -0.17f);
        small = new BitmapFont(Gdx.files.internal("data/white.fnt"));
        small.getData().setScale(0.10f, -0.10f);
        large = new BitmapFont(Gdx.files.internal("data/data.fnt"));
        large.getData().setScale(0.17f, -0.27f);

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
        leftWalkA.dispose();
        leftWalkB.dispose();
        leftWalkC.dispose();
        leftWalkD.dispose();
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
        sboardBackground.dispose();
        button.dispose();
        speakers.dispose();
        mute.dispose();
        controls.dispose();
        upArrow.dispose();
        downArrow.dispose();
        leftArrow.dispose();
        rightArrow.dispose();
        dead.dispose();
        jump.dispose();
    }
}
