package com.stanum.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.stanum.controls.ControlWheel;
import com.stanum.gameobjects.Ball;
import com.stanum.gameobjects.ScrollHandler;
import com.stanum.gameobjects.Stanum;
import com.stanum.gameobjects.Thorns;
import com.stanum.gameobjects.Tile;
import com.stanum.helper.AssetLoader;
import com.stanum.simplebuttons.SimpleButtons;

/**
 * Created by varunrana on 14/12/17.
 */

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private Color c;
    private BitmapFont font;

    //game Obtects
    private Stanum stanum;
    private ControlWheel wheel;
    private Tile tile1, tile2, tile3;
    private Ball ball1, ball2;
    private ScrollHandler scroller;
    private Thorns thorns;

    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;
    private int height  = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();

    //Game Assets
    private Texture background, tile, circle, slider, ball;
    private TextureRegion thorn;
    private Texture die, rightStanum, rightJump, rightWalkA, rightWalkB, rightWalkC, rightWalkD, leftWalkA, leftWalkB, leftWalkC, leftWalkD;
    private Animation rightStanumAnimation, leftStanumAnimation;
    private Texture playButton, settingButton, scoreboardButton, exitButton;

    //Menu Buttons
    private SimpleButtons play, settings, scoreboard, exit;


    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;
        this.midPointY = midPointY;
        this.gameHeight = gameHeight;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 204, 136);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        c = batcher.getColor();
        font = new BitmapFont();

        initGameObjetcs();
        initAssets();
    }

    private void initAssets(){
        background = AssetLoader.background;
        ball = AssetLoader.ball;
        circle = AssetLoader.circle;
        tile = AssetLoader.tile;
        slider = AssetLoader.slider;
        thorn = AssetLoader.thorn;

        die = AssetLoader.die;
        rightStanum  = AssetLoader.rightStanum;
        rightJump = AssetLoader.rightJump;
        rightWalkA = AssetLoader.rightWalkA;
        rightWalkB = AssetLoader.rightWalkB;
        rightWalkC = AssetLoader.rightWalkC;
        rightWalkD = AssetLoader.rightWalkD;
        leftWalkA = AssetLoader.leftWalkA;
        leftWalkB = AssetLoader.leftWalkB;
        leftWalkC = AssetLoader.leftWalkC;
        leftWalkD = AssetLoader.leftWalkD;
        rightStanumAnimation = AssetLoader.stanumRightAnimation;
        leftStanumAnimation = AssetLoader.stanumLeftAnimation;
        playButton = AssetLoader.playButton;
        scoreboardButton = AssetLoader.scoreboardButton;
        settingButton = AssetLoader.settingButton;
        exitButton = AssetLoader.exitButton;
    }

    private void initGameObjetcs(){
        stanum = myWorld.getStanum();
        scroller = myWorld.getScroller();
        wheel = myWorld.getWheel();
        ball1 = myWorld.getBall1();
        ball2 = myWorld.getBall2();
        tile1 = scroller.getTile1();
        tile2 = scroller.getTile2();
        tile3 = scroller.getTile3();
        thorns = myWorld.getThorns();
        play = myWorld.getPlay();
        settings = myWorld.getSettings();
        scoreboard = myWorld.getScoreboard();
        exit = myWorld.getExit();

    }

    public void drawMenu(){
        // Draw text
        AssetLoader.font.draw(batcher, "staNUM", (204 / 2) - (26), 13);
        batcher.draw(playButton, play.getX(), play.getY(), play.getWidth(), play.getHeight());
        batcher.draw(settingButton, settings.getX(), settings.getY(), settings.getWidth(), settings.getHeight());
        batcher.draw(scoreboardButton, scoreboard.getX(), scoreboard.getY(), scoreboard.getWidth(), scoreboard.getHeight());
        batcher.draw(exitButton, exit.getX(), exit.getY(), exit.getWidth(), exit.getHeight());
    }

    public void drawTiles(){
        batcher.draw(tile, tile1.getX(), tile1.getY(), tile1.getWidth(), tile1.getHeight());
        batcher.draw(tile, tile2.getX(), tile2.getY(), tile2.getWidth(), tile2.getHeight());
        batcher.draw(tile, tile3.getX(), tile3.getY(), tile3.getWidth(), tile3.getHeight());
    }

    public void drawballs(){
        batcher.draw(thorn, ball1.getX(), ball1.getY(), ball1.getWidth()/2.0f, ball1.getHeight()/2.0f, ball1.getWidth(), ball1.getHeight(),1,1,thorns.getRotation());
        batcher.draw(thorn, ball2.getX(), ball2.getY(), ball2.getWidth()/2.0f, ball2.getHeight()/2.0f, ball2.getWidth(), ball2.getHeight(),1,1,thorns.getRotation());
        batcher.draw(thorn, thorns.getPositionX(), thorns.getPositionY(),thorns.getWidth()/2.0f,thorns.getHeight()/2.0f, thorns.getWidth(), thorns.getHeight(),1,1, thorns.getRotation());
    }

    public void drawStanum(float runTime){
        if(stanum.isAlive()) {
            if (stanum.isMovingRight())
                batcher.draw((Texture) rightStanumAnimation.getKeyFrame(runTime), stanum.getX(), stanum.getY(), stanum.getWidth(), stanum.getHeight());
            else if (stanum.isMovingLeft())
                batcher.draw((Texture) leftStanumAnimation.getKeyFrame(runTime), stanum.getX(), stanum.getY(), stanum.getWidth(), stanum.getHeight());
            else
                batcher.draw(rightStanum, stanum.getX(), stanum.getY(), stanum.getWidth(), stanum.getHeight());
        }else
            batcher.draw(die, stanum.getX(), stanum.getY(), stanum.getWidth(), stanum.getHeight());
    }

    public void drawPlayAssets(){

        batcher.setColor(c.r,c.g,c.b,0.1f);
        batcher.draw(slider,6,48,12,30);

        batcher.setColor(c.r,c.g,c.b,0.4f);
        batcher.draw(circle,wheel.getX(),wheel.getY(),wheel.getWidth(),wheel.getHeight());

        batcher.setColor(c.r,c.g,c.b,0.2f);
        batcher.draw(ball,181,50,22,26);

        batcher.setColor(c.r,c.g,c.b,0.4f);
        batcher.draw(circle,184,54,16,18);

        batcher.setColor(c.r,c.g,c.b,1f);


    }

    public void drawScore(){
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),102 - (3 * length), 7);
    }

    public void drawGameover(){
        AssetLoader.shadow.draw(batcher, "Game Over", 62, 25);
        AssetLoader.shadow.draw(batcher, "Your Score:", 48, 46);

        int length = ("" + myWorld.getScore()).length();
        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),142, 46);

        AssetLoader.shadow.draw(batcher, "best score:", 48, 62);
        String highScore = AssetLoader.getHighScore() + "";
        // Draw text
        AssetLoader.shadow.draw(batcher, highScore, 142, 62);
        AssetLoader.shadow.draw(batcher, "Try again?", 59, 78);
        batcher.draw(AssetLoader.button,62,94,29,16);
        AssetLoader.small.draw(batcher, "yes", 68, 99);
        batcher.draw(AssetLoader.button,109,94,29,16);
        AssetLoader.small.draw(batcher, "no", 118, 99);
    }

    public void drawHighScore(){
        AssetLoader.font.draw(batcher, "High Score!", 18, 55);
        AssetLoader.font.draw(batcher, "Try again?", 24, 75);
    }

    public void instruction(){
        batcher.setColor(c.r,c.g,c.b,0.2f);
        batcher.draw(AssetLoader.sboardBackground,0,0,204,136);
        batcher.setColor(c.r,c.g,c.b,1f);
        batcher.draw(AssetLoader.rightArrow,21,56, 21,15);
        batcher.draw(AssetLoader.upArrow,18, 45,8,15);
        batcher.draw(AssetLoader.downArrow,18,67,8,15);
        AssetLoader.small.draw(batcher, "movement", 43, 61);
        AssetLoader.small.draw(batcher, "forward", 23, 37);
        AssetLoader.small.draw(batcher, "backward", 23, 84);
        batcher.draw(AssetLoader.leftArrow,162,56, 21,15);
        AssetLoader.small.draw(batcher, "jump", 141, 61);
    }

    public void drawSettings(){
        batcher.setColor(c.r,c.g,c.b,0.5f);
        batcher.draw(AssetLoader.sboardBackground,40,30,124,76);
        batcher.setColor(c.r,c.g,c.b,0.5f);
        batcher.draw(AssetLoader.sboardBackground,40,30,124,20);
        AssetLoader.shadow.draw(batcher, "Settings", 66, 35);

        batcher.setColor(c.r,c.g,c.b,1f);
        if(myWorld.isAudio())
            batcher.draw(AssetLoader.speakers, 70, 65, 20, 22);
        else
            batcher.draw(AssetLoader.mute, 70, 65, 20, 22);

        batcher.setColor(c.r,c.g,c.b,1f);
        if(myWorld.isControls())
            batcher.draw(AssetLoader.controls, 111, 65, 20, 22);
        else{
            batcher.setColor(c.r,c.g,c.b,1f);
            batcher.draw(background,0,0,204,136);
            drawPlayAssets();
            instruction();

        }
    }

    public void render(float runTime) {

        // Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Begin SpriteBatch
        batcher.begin();

        batcher.setColor(c.r,c.g,c.b,1f);
        batcher.draw(background,0,0,1080,720);

        if(myWorld.isMenu()){
            if(myWorld.isScoreboard()){
                batcher.setColor(c.r,c.g,c.b,0.5f);
                batcher.draw(AssetLoader.sboardBackground,40,30,124,76);
                batcher.setColor(c.r,c.g,c.b,0.5f);
                batcher.draw(AssetLoader.sboardBackground,40,30,124,20);
                AssetLoader.shadow.draw(batcher, "Score Board", 54, 35);
            }
            else if(myWorld.isSettings()){
                drawSettings();
            }
            else
                drawMenu();

        }
        else if(myWorld.isReady()){
            // Draw text
            AssetLoader.font.draw(batcher, "Touch me", (204 / 2) - (42 - 1), 63);
            drawPlayAssets();
            drawTiles();
            drawballs();
            drawStanum(runTime);
        }
        else if(myWorld.isRunning()){
            drawScore();
            drawPlayAssets();
            drawTiles();
            drawballs();
            drawStanum(runTime);

        }
        else if(myWorld.isGameOver()){
            batcher.setColor(c.r,c.g,c.b,0.6f);
            batcher.draw(AssetLoader.sboardBackground,35,20,134,96);
            batcher.draw(AssetLoader.sboardBackground,35,20,134,20);
            drawGameover();

        }
        else if(myWorld.isHighScore()){
            drawScore();
            drawHighScore();
        }

        // End SpriteBatch
        batcher.end();

        // shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // shapeRenderer.setColor(Color.CYAN,0.4f);
        // shapeRenderer.circle(100,50,20);
        //shapeRenderer.circle(myWorld.getThorns().getBoundingThorns().x, myWorld.getThorns().getBoundingThorns().y, myWorld.getThorns().getBoundingThorns().radius);
        // shapeRenderer.end();

        //  shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.BLACK);
        //  shapeRenderer.identity();
        //  shapeRenderer.translate(1.0f, 1.0f, 0);
        //shapeRenderer.rotate(0.0f, 0.0f, 1.0f, (float) Math.toDegrees(getBody().getAngle()));
        //shapeRenderer.circle(30,54,9, 200);
        //shapeRenderer.end();
    }

}
