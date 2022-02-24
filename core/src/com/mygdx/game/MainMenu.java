package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.network.Client;
import com.mygdx.game.network.ClientHandler;
import com.mygdx.game.network.Server;
import com.mygdx.game.network.ServerHandler;

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

    Texture bg;
    Texture logoButton ;
    Texture exitButton ;
    Texture startButton;
    Texture optionsButton;
    Texture loadButton ;
    Texture mapButton ;

    OrthographicCamera camera;

    public MainMenu(final MyGdxGame game) {
        this.game = game;

        camera = new OrthographicCamera();

        camera.setToOrtho(false, 1920, 1080);
        game.batch.setProjectionMatrix(camera.combined);
    }


    @Override
    public void show() {
        ScreenUtils.clear(255, 98, 0, 1);

        bg = new Texture("menu/bg.jpg");
        logoButton = new Texture("menu/logo.png");
        exitButton = new Texture("menu/exit_button.png");
        startButton = new Texture("menu/start_button.png");
        optionsButton = new Texture("menu/options_button.png");
        loadButton = new Texture("menu/load_button.png");
        mapButton = new Texture("menu/map_button.png");


        buttonWid = 250;
        buttonHei = 100;
        buttonX = 1920/2-buttonWid/2;
        exitbuttonY = 100;
        optionsButtonY = 225;
        mapButtonY = 350;
        loadButtonY = 475;
        startButtonY = 600;
    }

    @Override
    public void render(float delta) {



        camera.update();
        game.batch.begin();
        game.batch.draw(bg, 0 , 0, 1920, 1080);
        game.batch.draw(logoButton, 1920/2-700/2 , 800, 700, 150);
        //startButton
        game.batch.draw(startButton, buttonX , startButtonY, buttonWid, buttonHei);


        if(Gdx.input.isTouched()){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX + buttonWid && vec.x  > buttonX && vec.y > startButtonY && vec.y < startButtonY + buttonHei){
                ClientHandler clh=new ClientHandler(new Client(),"192.168.0.210");
                game.setScreen(new GameScreen(game,clh,32,32));
                dispose();
            }
        }

        //loadButton
        game.batch.draw(loadButton, buttonX , loadButtonY, buttonWid, buttonHei);
        if(Gdx.input.isTouched()){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX + buttonWid && vec.x > buttonX && vec.y >loadButtonY  && vec.y < loadButtonY + buttonHei){
                game.setScreen(new MapLoaderScreen(game));
                dispose();
            }
        }

    //here we go
        //mapButton
        game.batch.draw(mapButton, buttonX , mapButtonY, buttonWid, buttonHei);
        if(Gdx.input.isTouched()){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX + buttonWid && vec.x > buttonX && vec.y >  buttonHei  &&  vec.y < mapButtonY + buttonHei){
                game.setScreen(new MapEditorScreen(game));
                dispose();
            }
        }


        //optionsButton
        game.batch.draw(optionsButton, buttonX , optionsButtonY, buttonWid, buttonHei);
        if(Gdx.input.isTouched()){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX + buttonWid && vec.x > buttonX && vec.y >  buttonHei  && vec.y < optionsButtonY + buttonHei){

                game.setScreen(new OptionScreen(game));
                dispose();
            }
        }


        //exitButton
        game.batch.draw(exitButton, buttonX , exitbuttonY, buttonWid, buttonHei);
        if(Gdx.input.isTouched()){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX + buttonWid && vec.x > buttonX && vec.y > buttonHei  &&  vec.y < exitbuttonY + buttonHei){

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
        */

        //Client and server
        if(Gdx.input.isKeyPressed(Input.Keys.K)){
            ServerHandler serverHandler=new ServerHandler(new Server());
            game.setScreen(new GameScreen(game,serverHandler,32,32));
            dispose();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.C)){
            ClientHandler clh=new ClientHandler(new Client(),"192.168.0.210");
            game.setScreen(new GameScreen(game,clh,32,32));
            dispose();

        }

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
