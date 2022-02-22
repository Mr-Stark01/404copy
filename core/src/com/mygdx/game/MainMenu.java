package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to 404", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();




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
