package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.network.Client;
import com.mygdx.game.network.ClientHandler;
import com.mygdx.game.network.Server;

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
            game.setScreen(new GameScreen(game,32,32));
            dispose();
        }
        //Client and server
        else if(Gdx.input.isKeyPressed(Input.Keys.K)){
            Server server=new Server();
            server.start(6666);
            System.out.println("the end Server");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.C)){
            Client client=new Client();
            ClientHandler clh=new ClientHandler(client,"192");
            clh.start();

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
