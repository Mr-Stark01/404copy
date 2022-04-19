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
    private float gold;
    private float health;
    private Integer score;
    private boolean timeUp;
    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;

    //Scene2D Widgets
    private Label goldLabel, gold1Label, scoreLabel,healthLabel,archerTowerLabel,fireTowerLabel,cannonTowerLabel,archerUnitLabel,mageUnitLabel,tankUnitLabel;
    private Image archerTowerImg,fireTowerImg,cannonTowerImg,archerUnitImg,mageUnitImg,tankUnitImg;

    public Hud(SpriteBatch sb) {

        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;

        //setup the HUD viewport using a new camera seperate from gamecam
        //define stage using that viewport and games spritebatch
        viewport = new FitViewport(1920, 1080, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define labels using the String, and a Label style consisting of a font and color
        BitmapFont font1 = new BitmapFont(Gdx.files.internal("fonts/test.fnt"),
                Gdx.files.internal("fonts/test.png"), false);

        gold1Label = new Label(String.format("%.0f", gold), new Label.LabelStyle(font1, Color.WHITE));
        scoreLabel = new Label(String.format("%.0f", health), new Label.LabelStyle(font1, Color.WHITE));
        goldLabel = new Label("Gold:", new Label.LabelStyle(font1, Color.WHITE));
        healthLabel = new Label("Health:", new Label.LabelStyle(font1, Color.WHITE));

        archerTowerLabel = new Label("ArcherTower", new Label.LabelStyle(font1, Color.WHITE));
        fireTowerLabel = new Label("FireTower", new Label.LabelStyle(font1, Color.WHITE));
        cannonTowerLabel = new Label("CannonTower", new Label.LabelStyle(font1, Color.WHITE));

        archerUnitLabel = new Label("Archer", new Label.LabelStyle(font1, Color.WHITE));
        mageUnitLabel = new Label("Mage", new Label.LabelStyle(font1, Color.WHITE));
        tankUnitLabel = new Label("Tank", new Label.LabelStyle(font1, Color.WHITE));

        archerTowerImg = new Image(new Texture("textures/archer-tower.png"));
        fireTowerImg = new Image(new Texture("textures/mage-tower.png"));
        cannonTowerImg = new Image(new Texture("textures/canon-tower.png"));

        archerUnitImg = new Image(new Texture("textures/archer-unit.png"));
        mageUnitImg = new Image(new Texture("textures/mage-unit.png"));
        tankUnitImg = new Image(new Texture("textures/tank-unit.png"));

        goldLabel.setFontScale(4);
        gold1Label.setFontScale(3);
        healthLabel.setFontScale(4);
        scoreLabel.setFontScale(3);

        archerTowerLabel.setFontScale(2);
        fireTowerLabel.setFontScale(2);
        cannonTowerLabel.setFontScale(2);
        archerUnitLabel.setFontScale(2);
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
        imageBot.setSize(1920,150);
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
        tableBot.add(archerTowerLabel).expandX().padBottom(20);
        tableBot.add(fireTowerLabel).expandX().padBottom(20);
        tableBot.add(cannonTowerLabel).expandX().padBottom(20);
            //unit
        tableBot.add(archerUnitLabel).expandX().padBottom(20);
        tableBot.add(mageUnitLabel).expandX().padBottom(20);
        tableBot.add(tankUnitLabel).expandX().padBottom(20);
        //row
        tableBot.row();
        //pics
            //tower
        tableBot.add(archerTowerImg).width(80).height(80).expandX().padBottom(10);
        tableBot.add(fireTowerImg).width(80).height(80).expandX().padBottom(10);
        tableBot.add(cannonTowerImg).width(80).height(80).expandX().padBottom(10);
            //unit
        tableBot.add(archerUnitImg).width(80).height(80).expandX().padBottom(10);
        tableBot.add(mageUnitImg).width(80).height(80).expandX().padBottom(10);
        tableBot.add(tankUnitImg).width(80).height(80).expandX().padBottom(10);


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
        gold1Label.setText(String.format("%.0f", gold));
        scoreLabel.setText(String.format("%.0f", health));
    }





    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isTimeUp() {
        return timeUp;
    }

    public void setGold(float gold) {
        this.gold = gold;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}