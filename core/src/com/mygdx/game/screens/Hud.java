package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    //score && time tracking variables
    private Integer gold;
    private Integer health;
    private Integer score;
    private boolean timeUp;
    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;

    //Scene2D Widgets
    private Label goldLabel, gold1Label, scoreLabel,healthLabel,archerTowerLabel,fireTowerLabel,cannonTowerLabel,archerUnitLabel,knightUnitLabel,mageUnitLabel,tankUnitLabel;


    public Hud(SpriteBatch sb) {

        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;
        //define tracking variables
        gold = 0;
        health = 500;

        score = 0;

        //setup the HUD viewport using a new camera seperate from gamecam
        //define stage using that viewport and games spritebatch
        viewport = new FitViewport(1920, 1080, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define labels using the String, and a Label style consisting of a font and color
        BitmapFont font1 = new BitmapFont(Gdx.files.internal("fonts/test.fnt"),
                Gdx.files.internal("fonts/test.png"), false);

        gold1Label = new Label(String.format("%05d", gold), new Label.LabelStyle(font1, Color.WHITE));
        scoreLabel = new Label(String.format("%03d", health), new Label.LabelStyle(font1, Color.WHITE));
        goldLabel = new Label("Gold:", new Label.LabelStyle(font1, Color.WHITE));
        healthLabel = new Label("Health:", new Label.LabelStyle(font1, Color.WHITE));

        archerTowerLabel = new Label("ArcherTower", new Label.LabelStyle(font1, Color.WHITE));
        fireTowerLabel = new Label("FireTower", new Label.LabelStyle(font1, Color.WHITE));
        cannonTowerLabel = new Label("CannonTower", new Label.LabelStyle(font1, Color.WHITE));

        archerUnitLabel = new Label("Archer", new Label.LabelStyle(font1, Color.WHITE));
        knightUnitLabel = new Label("Knight", new Label.LabelStyle(font1, Color.WHITE));
        mageUnitLabel = new Label("Mage", new Label.LabelStyle(font1, Color.WHITE));
        tankUnitLabel = new Label("Tank", new Label.LabelStyle(font1, Color.WHITE));

        goldLabel.setFontScale(4);
        gold1Label.setFontScale(3);
        healthLabel.setFontScale(4);
        scoreLabel.setFontScale(3);

        archerTowerLabel.setFontScale(2);
        fireTowerLabel.setFontScale(2);
        cannonTowerLabel.setFontScale(2);
        archerUnitLabel.setFontScale(2);
        knightUnitLabel.setFontScale(2);
        mageUnitLabel.setFontScale(2);
        tankUnitLabel.setFontScale(2);


        //define a table used to organize hud's labels
        Table tableTop = new Table();
        tableTop.top();
        tableTop.setFillParent(true);

        Table tableBot = new Table();
        tableBot.bottom();
        tableBot.setFillParent(true);


        //Textures for Da menu
        Image imageTop = new Image(new Texture("menu/white.png"));
        imageTop.setSize(1920,100);
        imageTop.setX(0);
        imageTop.setY(1080-100);

        Image imageBot = new Image(new Texture("menu/white.png"));
        imageBot.setSize(1920,100);
        imageBot.setX(0);
        imageBot.setY(0);

        //ready button
        Image readyButton = new Image(new Texture("menu/button_ready.png"));
        readyButton.setSize(250,100);
        readyButton.setX(1920/2-125);
        readyButton.setY(1080-100);

        //add labels to table, padding the top, and giving them all equal width with expandX

        //top table
        tableTop.add(healthLabel).expandX().padTop(10);
        tableTop.add(goldLabel).expandX().padTop(10);
        //row
        tableTop.row();
        tableTop.add(scoreLabel).expandX();
        tableTop.add(gold1Label).expandX();

        //bot table
            //tower
        tableBot.add(archerTowerLabel).expandX().padBottom(70);
        tableBot.add(fireTowerLabel).expandX().padBottom(70);
        tableBot.add(cannonTowerLabel).expandX().padBottom(70);
            //unit
        tableBot.add(archerUnitLabel).expandX().padBottom(70);
        tableBot.add(knightUnitLabel).expandX().padBottom(70);
        tableBot.add(mageUnitLabel).expandX().padBottom(70);
        tableBot.add(tankUnitLabel).expandX().padBottom(70);
        //row
        tableBot.row();
        //pics



        //add table to the stage
        stage.addActor(imageTop);
        stage.addActor(imageBot);
        stage.addActor(readyButton);
        stage.addActor(tableTop);
        stage.addActor(tableBot);
    }

    public void update(float dt) {
        /*
        timeCount += dt;
        if (timeCount >= 1) {
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
            }
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
        */

    }





    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isTimeUp() {
        return timeUp;
    }


}