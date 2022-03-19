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
    private Label goldLabel, gold1Label, scoreLabel,healthLabel;


    public Hud(SpriteBatch sb) {

        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;
        //define tracking variables
        gold = 0;
        health = 0;

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

        goldLabel.setFontScale(2);


        //define a table used to organize hud's labels
        Table table = new Table();
        table.top();
        table.setFillParent(true);


        //Textures for Da menu
        Image image = new Image(new Texture("menu/404.png"));
        image.setSize(1536,150);
        image.setX(192);
        image.setY(930);

        //add labels to table, padding the top, and giving them all equal width with expandX
        table.add(healthLabel).expandX().padTop(10);

        table.add(goldLabel).expandX().padTop(10);


        table.row();
        table.add(scoreLabel).expandX();

        table.add(gold1Label).expandX();


        //add table to the stage



        stage.addActor(image);
        stage.addActor(table);

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