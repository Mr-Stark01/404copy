package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CameraHandler;
import com.mygdx.game.Castle;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.network.*;
import com.mygdx.game.units.Knight;

public class OptionScreen implements Screen {

    final MyGdxGame game;
    OrthographicCamera camera;


    public OptionScreen(final MyGdxGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(255, 98, 0, 1);
        Texture bg = new Texture("bg.jpg");
        Texture exitButton = new Texture("back_button.png");

        int buttonWid = 250;
        int buttonHei = 100;
        int buttonX = 1920-buttonWid-50;
        int backbuttonY = 50;

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(bg, 0 , 0, 1920, 1080);
        game.font.setColor(0,0,0,1);
        game.font.draw(game.batch, "Game by 404", 1700, 40);
        game.font.draw(game.batch, "Options", 1920/2, 800);

        //backButton
        game.batch.draw(exitButton, buttonX , backbuttonY, buttonWid, buttonHei);
        if(Gdx.input.getX() < buttonX + buttonWid && Gdx.input.getX() > buttonX && 1080 - Gdx.input.getY() < backbuttonY + buttonHei + buttonHei/2 && 1080 - Gdx.input.getY() > backbuttonY + buttonHei/2){
            if(Gdx.input.isTouched()){
                game.setScreen(new MainMenu(game));
                dispose();
            }
        }

        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
    }
}

