package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

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
        else if(Gdx.input.isKeyPressed(Input.Keys.K)){
            Server server=new Server();
            server.start(6666);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.C)){
            System.out.println("Client Started");
            Client client=new Client();
            client.startConnection("192.168.0.210", 6666);
            String resp=client.sendMessage("1hello server");
            System.out.println(resp);
             resp=client.sendMessage("2hello server");
            System.out.println(resp);
             resp=client.sendMessage("3hello server");
            System.out.println(resp);
            resp=client.sendMessage(".");
            System.out.println(resp);
            client.tearDown();

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
