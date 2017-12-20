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
    private Texture die, rightStanum, rightJump, rightWalkA, rightWalkB, rightWalkC, rightWalkD, rightWalkE, rightWalkF,  leftStanum, leftJump, leftWalkA, leftWalkB, leftWalkC, leftWalkD, leftWalkE, leftWalkF;
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
        rightWalkE = AssetLoader.rightWalkE;
        rightWalkF = AssetLoader.rightWalkF;
        leftStanum = AssetLoader.leftStanum;
        leftJump = AssetLoader.leftJump;
        leftWalkA = AssetLoader.leftWalkA;
        leftWalkB = AssetLoader.leftWalkB;
        leftWalkC = AssetLoader.leftWalkC;
        leftWalkD = AssetLoader.leftWalkD;
        leftWalkE = AssetLoader.leftWalkE;
        leftWalkF = AssetLoader.leftWalkF;
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

    }

    public void drawMenu(){
        batcher.draw(playButton,85,50,20,22);
        batcher.draw(settingButton,103,61,20,22);
        batcher.draw(scoreboardButton,85,73,20,22);
        batcher.draw(exitButton,103,84,20,22);
    }

    public void drawTiles(){
        batcher.draw(tile, tile1.getX(), tile1.getY(), tile1.getWidth(), tile1.getHeight());
        batcher.draw(tile, tile2.getX(), tile2.getY(), tile2.getWidth(), tile2.getHeight());
        batcher.draw(tile, tile3.getX(), tile3.getY(), tile3.getWidth(), tile3.getHeight());
    }

    public void drawballs(){
        batcher.draw(thorn, ball1.getX(), ball1.getY(), ball1.getWidth()/2.0f, ball1.getHeight()/2.0f, ball1.getWidth(), ball1.getHeight(),1,1,thorns.getRotation());
        batcher.draw(thorn, ball2.getX(), ball2.getY(), ball2.getWidth()/2.0f, ball2.getHeight()/2.0f, ball2.getWidth(), ball2.getHeight(),1,1,thorns.getRotation());
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
        batcher.draw(slider,6,43,12,40);

        batcher.setColor(c.r,c.g,c.b,0.4f);
        batcher.draw(circle,wheel.getX(),wheel.getY(),wheel.getWidth(),wheel.getHeight());

        batcher.setColor(c.r,c.g,c.b,0.2f);
        batcher.draw(ball,181,50,22,26);

        batcher.setColor(c.r,c.g,c.b,0.4f);
        batcher.draw(circle,184,54,16,18);

        batcher.setColor(c.r,c.g,c.b,1f);
        batcher.draw(thorn, thorns.getPositionX(), thorns.getPositionY(),thorns.getWidth()/2.0f,thorns.getHeight()/2.0f, thorns.getWidth(), thorns.getHeight(),1,1, thorns.getRotation());


    }

    public void drawScore(){
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 82);
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
                68 - (3 * length), midPointY - 83);
    }

    public void render(float runTime) {

        // Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Begin SpriteBatch
        batcher.begin();

        batcher.setColor(c.r,c.g,c.b,1f);
        batcher.draw(background,0,0,204,136);

        if(myWorld.isMenu()){
            // Draw shadow first
            AssetLoader.shadow.draw(batcher, "staNUM", (204 / 2)
                    - (25), 7);
            // Draw text
            AssetLoader.font.draw(batcher, "staNUM", (204 / 2)
                    - (26), 6);
            drawMenu();
        }
        else if(myWorld.isReady()){
            // Draw shadow first
            AssetLoader.shadow.draw(batcher, "Touch me", (204 / 2) - (42), 63);
            // Draw text
            AssetLoader.font.draw(batcher, "Touch me", (204 / 2) - (42 - 1), 63);
            drawPlayAssets();
            drawTiles();
            drawballs();
            drawStanum(runTime);
        }
        else if(myWorld.isRunning()){
            drawPlayAssets();
            drawTiles();
            drawballs();
            drawStanum(runTime);

        }
        else if(myWorld.isGameOver()){

        }
        else if(myWorld.isHighScore()){

        }

      /*  drawPlayAssets();

        drawTiles();

        drawballs();

        drawStanum(runTime);

        // TEMPORARY CODE! We will fix this section later:

        if (myWorld.isReady()) {
            // Draw shadow first
            AssetLoader.shadow.draw(batcher, "Touch me", (204 / 2) - (42), 63);
            // Draw text
            AssetLoader.font
                    .draw(batcher, "Touch me", (204 / 2) - (42 - 1), 63);
        } else {

            if (myWorld.isGameOver() || myWorld.isHighScore()) {

                if (myWorld.isGameOver()) {
                    AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
                    AssetLoader.font.draw(batcher, "Game Over", 24, 55);

                    AssetLoader.shadow.draw(batcher, "High Score:", 23, 106);
                    AssetLoader.font.draw(batcher, "High Score:", 22, 105);

                    String highScore = AssetLoader.getHighScore() + "";

                    // Draw shadow first
                    AssetLoader.shadow.draw(batcher, highScore, (204 / 2)
                            - (3 * highScore.length()), 128);
                    // Draw text
                    AssetLoader.font.draw(batcher, highScore, (204 / 2)
                            - (3 * highScore.length() - 1), 127);
                } else {
                    AssetLoader.shadow.draw(batcher, "High Score!", 19, 56);
                    AssetLoader.font.draw(batcher, "High Score!", 18, 55);
                }

                AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
                AssetLoader.font.draw(batcher, "Try again?", 24, 75);

                // Convert integer into String
                String score = myWorld.getScore() + "";

                // Draw shadow first
                AssetLoader.shadow.draw(batcher, score,
                        (204 / 2) - (3 * score.length()), 7);
                // Draw text
                AssetLoader.font.draw(batcher, score,
                        (204 / 2) - (3 * score.length() - 1), 6);

            }

            // Convert integer into String
            String score = myWorld.getScore() + "";

            // Draw shadow first
            AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (204 / 2)
                    - (3 * score.length()), 7);
            // Draw text
            AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (204 / 2)
                    - (3 * score.length() - 1), 6);

        }*/

        // End SpriteBatch
        batcher.end();

       // shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
       // shapeRenderer.setColor(Color.CYAN);
        // shapeRenderer.rect(10,50,30,8);
       // shapeRenderer.rect(myWorld.getStanum().getJumpControl().getBounds().x, myWorld.getStanum().getJumpControl().getBounds().y, myWorld.getStanum().getJumpControl().getBounds().width, myWorld.getStanum().getJumpControl().getBounds().height);
       // shapeRenderer.end();
    }

}
