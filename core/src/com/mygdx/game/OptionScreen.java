package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class OptionScreen implements Screen {

    final MyGdxGame game;
    OrthographicCamera camera;

    Texture bg;
    Texture onButton;
    Texture offButton;

    Texture backButton;

    int buttonWid;
    int buttonHei;
    int buttonX;
    int backbuttonY;

    int onoffButtonWid;
    int onoffButtonHei;
    int onoffButtonX;
    int onoffButtonY;

    boolean musicIsOn;

    public OptionScreen(final MyGdxGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

    }

    @Override
    public void show() {
        //ScreenUtils.clear(255, 98, 0, 1);
        bg = new Texture("menu/background_options.jpg");
        onButton = new Texture("menu/musicOnButton.png");
        offButton = new Texture("menu/musicOffButton.png");
        backButton = new Texture("menu/back_button.png");

        buttonWid = 250;
        buttonHei = 100;
        buttonX = 1920-buttonWid-50;
        backbuttonY = 50;

        musicIsOn = true;

        onoffButtonWid = 600;
        onoffButtonHei = 100;
        onoffButtonX = 1920/2-onoffButtonWid/2;
        onoffButtonY = 500;
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(bg, 0 , 0, 1920, 1080);
        game.font.setColor(0,0,0,1);
        game.font.draw(game.batch, "Game by 404", 1700, 40);

        if(musicIsOn){
            game.batch.draw(offButton, onoffButtonX , onoffButtonY, onoffButtonWid, onoffButtonHei);
            if(Gdx.input.justTouched()) {
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                    camera.unproject(vec);
                    if (vec.x < onoffButtonX + onoffButtonWid && vec.x > onoffButtonX && vec.y > onoffButtonY && vec.y < onoffButtonY + onoffButtonHei) {
                        musicIsOn = false;
                        MyGdxGame.stopMusic();
                    }
                }
            }
        }
        else{
            game.batch.draw(onButton, onoffButtonX , onoffButtonY, onoffButtonWid, onoffButtonHei);
            if(Gdx.input.justTouched()) {
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                    camera.unproject(vec);
                    if (vec.x < onoffButtonX + onoffButtonWid && vec.x > onoffButtonX && vec.y > onoffButtonY && vec.y < onoffButtonY + onoffButtonHei) {
                        musicIsOn = true;
                        MyGdxGame.startMusic();
                    }
                }
            }
        }

        //backButton
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
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

