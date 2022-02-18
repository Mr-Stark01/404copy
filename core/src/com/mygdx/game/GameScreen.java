package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.units.Knight;

public class GameScreen implements Screen {

    Knight knightTest;
    final MyGdxGame game;
    //map from tiled
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    //Camera
    OrthographicCamera camera;
    SpriteBatch spriteBatch;

    public GameScreen(final MyGdxGame game){
        this.game=game;
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {
        //Importing the map itself from maps folder
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/Base.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        knightTest = new Knight(new Sprite(new Texture("textures/knight.png")));

        //Camera viewport settings
        camera = new OrthographicCamera();
        camera.viewportHeight=1080;
        camera.viewportWidth=1920;
        camera.update();
    }

    @Override
    public void render(float delta) {

        // clearing screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();


        //To draw anything thats needed
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        knightTest.draw(spriteBatch);
        spriteBatch.end();

        // Bad solution for input handling needs to change away from polling to eventhandlers
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-10,0);
            System.out.println("LEFT");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0,10);
            System.out.println("UP");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0,-10);
            System.out.println("DOWN");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(10,0);
            System.out.println("RIGHT");
        }

        //Updatign camera position
        camera.update();

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
        map.dispose();
        renderer.dispose();
    }
}
