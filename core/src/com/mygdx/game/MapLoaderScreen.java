package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class MapLoaderScreen implements Screen {

    final MyGdxGame game;
    OrthographicCamera camera;


    public MapLoaderScreen(final MyGdxGame game){
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
        Texture bg = new Texture("menu/bg.jpg");
        Texture exitButton = new Texture("menu/back_button.png");

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
        game.font.draw(game.batch, "Map Loader", 1920/2, 800);

        //backButton
        game.batch.draw(exitButton, buttonX , backbuttonY, buttonWid, buttonHei);
        if(Gdx.input.isTouched()){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX + buttonWid && vec.x > buttonX && vec.y > backbuttonY  && vec.y < backbuttonY + buttonHei){
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

