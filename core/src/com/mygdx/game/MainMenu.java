package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.network.Client;
import com.mygdx.game.network.ClientHandler;
import com.mygdx.game.network.Server;
import com.mygdx.game.network.ServerHandler;

public class MainMenu implements Screen {

    final MyGdxGame game;

    OrthographicCamera camera;

    public MainMenu(final MyGdxGame game) {
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
        Texture logoButton = new Texture("logo.png");
        Texture exitButton = new Texture("exit_button.png");
        Texture startButton = new Texture("start_button.png");
        Texture optionsButton = new Texture("options_button.png");
        Texture loadButton = new Texture("load_button.png");
        Texture mapButton = new Texture("map_button.png");

        int buttonWid = 250;
        int buttonHei = 100;
        int buttonX = 1920/2-buttonWid/2;
        int exitbuttonY = 100;
        int optionsButtonY = 225;
        int mapButtonY = 350;
        int loadButtonY = 475;
        int startButtonY = 600;


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(bg, 0 , 0, 1920, 1080);
        game.batch.draw(logoButton, 1920/2-700/2 , 800, 700, 150);

        //startButton
        game.batch.draw(startButton, buttonX , startButtonY, buttonWid, buttonHei);
        if(Gdx.input.getX() < buttonX + buttonWid && Gdx.input.getX() > buttonX && 1080 - Gdx.input.getY() < startButtonY + buttonHei + buttonHei/2 && 1080 - Gdx.input.getY() > startButtonY + buttonHei/2){
            if(Gdx.input.isTouched()){
                ClientHandler clh=new ClientHandler(new Client(),"192.168.0.210");
                game.setScreen(new GameScreen(game,clh,32,32));
                dispose();
            }
        }

        //loadButton
        game.batch.draw(loadButton, buttonX , loadButtonY, buttonWid, buttonHei);
        game.batch.draw(mapButton, buttonX , mapButtonY, buttonWid, buttonHei);
        if(Gdx.input.getX() < buttonX + buttonWid && Gdx.input.getX() > buttonX && 1080 - Gdx.input.getY() < loadButtonY + buttonHei + buttonHei/2 && 1080 - Gdx.input.getY() > loadButtonY + buttonHei/2){
            if(Gdx.input.isTouched()){
                game.setScreen(new MapLoaderScreen(game));
                dispose();
            }
        }


        //mapButton
        game.batch.draw(mapButton, buttonX , mapButtonY, buttonWid, buttonHei);
        if(Gdx.input.getX() < buttonX + buttonWid && Gdx.input.getX() > buttonX && 1080 - Gdx.input.getY() < mapButtonY + buttonHei + buttonHei/2 && 1080 - Gdx.input.getY() > mapButtonY + buttonHei/2){
            if(Gdx.input.isTouched()){
                game.setScreen(new MapEditorScreen(game));
                dispose();
            }
        }


        //optionsButton
        game.batch.draw(optionsButton, buttonX , optionsButtonY, buttonWid, buttonHei);
        game.batch.draw(mapButton, buttonX , mapButtonY, buttonWid, buttonHei);
        if(Gdx.input.getX() < buttonX + buttonWid && Gdx.input.getX() > buttonX && 1080 - Gdx.input.getY() < optionsButtonY + buttonHei + buttonHei/2 && 1080 - Gdx.input.getY() > optionsButtonY + buttonHei/2){
            if(Gdx.input.isTouched()){
                game.setScreen(new OptionScreen(game));
                dispose();
            }
        }


        //exitButton
        game.batch.draw(exitButton, buttonX , exitbuttonY, buttonWid, buttonHei);
        if(Gdx.input.getX() < buttonX + buttonWid && Gdx.input.getX() > buttonX && 1080 - Gdx.input.getY() < exitbuttonY + buttonHei + buttonHei/2 && 1080 - Gdx.input.getY() > exitbuttonY + buttonHei/2){
            if(Gdx.input.isTouched()){
                this.dispose();
                Gdx.app.exit();
            }
        }


        game.font.setColor(0,0,0,1);
        game.font.draw(game.batch, "Game by 404", 1700, 40);

        game.batch.end();

        /*
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {

        }
        //Client and server
        else if(Gdx.input.isKeyPressed(Input.Keys.K)){
            ServerHandler serverHandler=new ServerHandler(new Server());
            game.setScreen(new GameScreen(game,serverHandler,32,32));
            dispose();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.C)){
            ClientHandler clh=new ClientHandler(new Client(),"192.168.0.210");
            game.setScreen(new GameScreen(game,clh,32,32));
            dispose();

        }
        */
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

    }

    @Override
    public void dispose() {

    }
}
