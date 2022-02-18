package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

public class GameScreen implements Screen {
    final MyGdxGame game;
    OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public GameScreen(final MyGdxGame game){
        this.game=game;




    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/Base.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);
        //Camera creation

        camera.viewportHeight=480;
        camera.viewportWidth=800;
        camera.update();
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

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
