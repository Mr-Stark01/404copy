package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;
import java.io.IOException;

/**
 * The main MainMenu class. This class handles the Main Menu
 */
public class MainMenu implements Screen {

    final MyGdxGame game;

    //
    int buttonWid ;
    int buttonHei ;
    int buttonX ;
    int exitbuttonY ;
    int optionsButtonY ;
    int mapButtonY ;
    int loadButtonY ;
    int startButtonY ;
    int exitButtonWid;
    int optionButtonWid;
    int mapEditorButtonWid;
    int startButtonWid;

    Texture bg;

    OrthographicCamera camera;

    /**
     * Everything thats needs to be initiated should be done here
     *
     * @param game final MyGdxGame
     */
    public MainMenu(final MyGdxGame game) {
        this.game = game;

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1920, 1080);
        game.batch.setProjectionMatrix(camera.combined);
    }

    /** Anything that will be shown to the player Will be initiated here. */
    @Override
    public void show() {
        bg = new Texture("menu/background_main.png");

        buttonWid = 250;
        buttonHei = 90;
        buttonX = 1920/2-buttonWid/2;
        exitbuttonY = 100;
        optionsButtonY = 225;
        mapButtonY = 350;
        loadButtonY = 475;
        startButtonY = 600;
        exitButtonWid = 200;
        optionButtonWid = 400;
        mapEditorButtonWid = 530;
        startButtonWid = 281;
    }

    /**
     * Updating the screen
     *
     * @param delta float
     */
    @Override
    public void render(float delta) {
        camera.update();
        game.batch.begin();
        game.batch.draw(bg, 0 , 0, 1920, 1080);

        //startButton
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX + buttonWid && vec.x  > buttonX && vec.y > startButtonY && vec.y < startButtonY + buttonHei){
                game.setScreen(new StartMenu(game));
                dispose();
            }
        }

        //loadButton
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX - ((mapEditorButtonWid - buttonWid)/2)  + mapEditorButtonWid && vec.x > buttonX - ((mapEditorButtonWid - buttonWid)/2) && vec.y >loadButtonY  && vec.y < loadButtonY + buttonHei){
                game.setScreen(new MapLoaderScreen(game));
                dispose();
            }
        }

    //here we go
        //mapButton
        if (Gdx.input.justTouched()) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(vec);
                if (vec.x < buttonX - ((mapEditorButtonWid - buttonWid) / 2) + mapEditorButtonWid && vec.x > buttonX - ((mapEditorButtonWid - buttonWid) / 2) && vec.y > mapButtonY && vec.y < mapButtonY + buttonHei) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(java.net.URI.create("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //optionsButton
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX - ((optionButtonWid - buttonWid)/2)  + optionButtonWid && vec.x > buttonX - ((optionButtonWid - buttonWid)/2) && vec.y >  optionsButtonY  && vec.y < optionsButtonY + buttonHei){

                game.setScreen(new OptionScreen(game));
                dispose();
            }
        }


        //exitButton
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX - ((exitButtonWid - buttonWid)/2)  + exitButtonWid && vec.x > buttonX - ((exitButtonWid - buttonWid)/2) && vec.y > exitbuttonY  &&  vec.y < exitbuttonY + buttonHei){
                    this.dispose();
                    Gdx.app.exit();
            }
        }


        game.font.setColor(0,0,0,1);
        game.font.draw(game.batch, "Game by 404", 1700, 40);

        game.batch.end();
    }

    /**
     * resizing the screen
     *
     * @param width int
     * @param height int
     */
    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    /**
     * hiding the screen
     */
    @Override
    public void hide() {}

    /**
     * hiding the screen
     */
    @Override
    public void dispose() {}
}
