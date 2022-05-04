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
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * The main Hud class. This class handles the Hud
 */
public class Hud implements Disposable {

    public Stage stage;
    private final Viewport viewport;

    //score && time tracking variables
    private float gold;
    private float health;
    private int seconds;
    private final ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;

    //Scene2D Widgets
    private final Label readyButtonPlaceHolder1;
    private final Label readyButtonPlaceHolder2;
    private final Label goldLabel;
    private final Label timeLabel;
    private final Label currentGoldLabel;
    private final Label currentHealthLabel;
    private final Label currentTimeLabel;
    private final Label healthLabel;
    private final Label archerTowerLabel;
    private final Label fireTowerLabel;
    private final Label cannonTowerLabel;
    private final Label archerUnitLabel;
    private final Label mageUnitLabel;
    private final Label tankUnitLabel;
    private final Image archerTowerImg;
    private final Image fireTowerImg;
    private final Image cannonTowerImg;
    private final Image archerUnitImg;
    private final Image mageUnitImg;
    private final Image tankUnitImg;
    private final Label aLabel;
    private final Label fLabel;
    private final Label cLabel;
    private final Label bLabel;
    private final Label mLabel;
    private final Label tLabel;

    /**
     * Everything thats needs to be initiated should be done here or in the show if it's a display
     *
     * @param sb SpriteBatch for display
     */
    public Hud(SpriteBatch sb) {

        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;

        //setup the HUD viewport using a new camera seperate from gamecam
        //define stage using that viewport and games spritebatch
        viewport = new FitViewport(1920, 1080, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define labels using the String, and a Label style consisting of a font and color
        BitmapFont font1 = new BitmapFont(Gdx.files.internal("fonts/test.fnt"), Gdx.files.internal("fonts/test.png"), false);

        currentGoldLabel = new Label(String.format("%.0f", gold), new Label.LabelStyle(font1, Color.WHITE));
        currentHealthLabel = new Label(String.format("%.0f", health), new Label.LabelStyle(font1, Color.WHITE));
        currentTimeLabel = new Label(String.format("%d", seconds), new Label.LabelStyle(font1, Color.WHITE));
        readyButtonPlaceHolder1 = new Label("", new Label.LabelStyle(font1, Color.WHITE));
        readyButtonPlaceHolder2 = new Label("", new Label.LabelStyle(font1, Color.WHITE));
        goldLabel = new Label("Gold", new Label.LabelStyle(font1, Color.WHITE));
        timeLabel = new Label("Time", new Label.LabelStyle(font1, Color.WHITE));
        healthLabel = new Label("Health", new Label.LabelStyle(font1, Color.WHITE));

        archerTowerLabel = new Label("ArcherTower", new Label.LabelStyle(font1, Color.WHITE));
        fireTowerLabel = new Label("FireTower", new Label.LabelStyle(font1, Color.WHITE));
        cannonTowerLabel = new Label("CannonTower", new Label.LabelStyle(font1, Color.WHITE));

        archerUnitLabel = new Label("Archer", new Label.LabelStyle(font1, Color.WHITE));
        mageUnitLabel = new Label("Mage", new Label.LabelStyle(font1, Color.WHITE));
        tankUnitLabel = new Label("Tank", new Label.LabelStyle(font1, Color.WHITE));

        aLabel = new Label("A", new Label.LabelStyle(font1, Color.WHITE));
        fLabel = new Label("F", new Label.LabelStyle(font1, Color.WHITE));
        cLabel = new Label("C", new Label.LabelStyle(font1, Color.WHITE));

        bLabel = new Label("B", new Label.LabelStyle(font1, Color.WHITE));
        mLabel = new Label("M", new Label.LabelStyle(font1, Color.WHITE));
        tLabel = new Label("T", new Label.LabelStyle(font1, Color.WHITE));

        //images for bottom hud part
        archerTowerImg = new Image(new Texture("textures/archer-tower.png"));
        fireTowerImg = new Image(new Texture("textures/mage-tower.png"));
        cannonTowerImg = new Image(new Texture("textures/canon-tower.png"));

        archerUnitImg = new Image(new Texture("textures/archer-unit.png"));
        mageUnitImg = new Image(new Texture("textures/mage-unit.png"));
        tankUnitImg = new Image(new Texture("textures/tank-unit.png"));

        //Label scaling
        goldLabel.setFontScale(4);
        currentGoldLabel.setFontScale(3);
        healthLabel.setFontScale(4);
        readyButtonPlaceHolder1.setFontScale(4);
        timeLabel.setFontScale(4);
        currentHealthLabel.setFontScale(3);
        currentTimeLabel.setFontScale(3);
        readyButtonPlaceHolder2.setFontScale(3);

        archerTowerLabel.setFontScale(2);
        fireTowerLabel.setFontScale(2);
        cannonTowerLabel.setFontScale(2);
        archerUnitLabel.setFontScale(2);
        mageUnitLabel.setFontScale(2);
        tankUnitLabel.setFontScale(2);

        aLabel.setFontScale(2);
        fLabel.setFontScale(2);
        cLabel.setFontScale(2);
        bLabel.setFontScale(2);
        mLabel.setFontScale(2);
        tLabel.setFontScale(2);


        //define a table used to organize hud's labels
        Table tableTop = new Table();
        tableTop.top();
        tableTop.setFillParent(true);

        Table tableBot = new Table();
        tableBot.bottom();
        tableBot.setFillParent(true);


        //images for Da menu
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
        readyButton.setX(1920-400);
        readyButton.setY(1080-100);

        //add labels to table, padding the top, and giving them all equal width with expandX

        //top table
        tableTop.add(healthLabel).expandX().padTop(10);
        tableTop.add(goldLabel).expandX().padTop(10);
        tableTop.add(timeLabel).expandX().padTop(10);
        tableTop.add(readyButtonPlaceHolder1).expandX().padTop(10);
        //row
        tableTop.row();
        tableTop.add(currentHealthLabel).expandX();
        tableTop.add(currentGoldLabel).expandX();
        tableTop.add(currentTimeLabel).expandX();
        tableTop.add(readyButtonPlaceHolder2).expandX();

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
        tableBot.add(archerTowerImg).width(80).height(80).expandX();
        tableBot.add(fireTowerImg).width(80).height(80).expandX();
        tableBot.add(cannonTowerImg).width(80).height(80).expandX();
            //unit
        tableBot.add(archerUnitImg).width(80).height(80).expandX();
        tableBot.add(mageUnitImg).width(80).height(80).expandX();
        tableBot.add(tankUnitImg).width(80).height(80).expandX();
        //row
        tableBot.row();
        //pics
            //tower
        tableBot.add(aLabel).expandX();
        tableBot.add(fLabel).expandX();
        tableBot.add(cLabel).expandX();
            //unit
        tableBot.add(bLabel).expandX();
        tableBot.add(mLabel).expandX();
        tableBot.add(tLabel).expandX();


        //add table to the stage
        stage.addActor(imageTop);
        stage.addActor(imageBot);
        stage.addActor(readyButton);
        stage.addActor(tableTop);
        stage.addActor(tableBot);
    }

    /**
     * Updating vars for hud
     *
     * @param dt float
     */
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
        currentGoldLabel.setText(String.format("%.0f", gold));
        currentHealthLabel.setText(String.format("%.0f", health));
        currentTimeLabel.setText(String.format("%d", seconds));
    }





    @Override
    public void dispose() {
        stage.dispose();
    }

    public void setTime(long seconds){
        this.seconds = (int) seconds;
    }

    /**
     * Updating vars for hud
     *
     * @param gold float
     */
    public void setGold(float gold) {
        this.gold = gold;
    }

    /**
     * Updating vars for hud
     *
     * @param health float
     */
    public void setHealth(float health) {
        this.health = health;
    }
}