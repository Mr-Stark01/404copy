package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.network.*;
import com.mygdx.game.pathFinding.pathFinder;
import com.mygdx.game.units.Knight;

import java.util.ArrayList;

public class GameScreen implements Screen {



    private NetworkHandler network;
    static float scale;
    CameraHandler cameraHandler;
    Castle castle;
    final MyGdxGame game;
    //map from tiled
    private TiledMapTileLayer tileyLayer;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private pathFinder pathFinder;
    //Camera
    OrthographicCamera camera;
    SpriteBatch spriteBatch;

    ArrayList<TiledMapTileLayer.Cell> cellList;


    public GameScreen(final MyGdxGame game,NetworkHandler network){
            this.network=network;

        this.game=game;
        spriteBatch = new SpriteBatch();


    }

    @Override
    public void show() {
        //Importing the map itself from maps folder
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/Base.tmx");
        tileyLayer=(TiledMapTileLayer) map.getLayers().get(0);

        scale=(float)tileyLayer.getTileWidth();
        renderer = new OrthogonalTiledMapRenderer(map,1/scale);
        //path
        pathFinder=new pathFinder(map);
        castle=new Castle();

        network.setCastle(castle);
        network.start();
        //Camera viewport settings
        camera = new OrthographicCamera();
        camera.viewportHeight=1080/scale;
        camera.viewportWidth=1920/scale;
        camera.update();
        cameraHandler = new CameraHandler(camera,scale);
        Gdx.input.setInputProcessor(cameraHandler);
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
        castle.draw(spriteBatch);
        spriteBatch.end();
        cameraHandler.update();

        /*for(int x=0;x<layer.getWidth();x++) {
            for(int y=0;y<layer.getHeight();x++) {
                layer.getCell(x,y);
            }
        }*/


        // update castle


        if (Gdx.input.isKeyPressed(Input.Keys.G)) {
            System.out.println(network.getEnemyCastle().getId());
            castle.setId("coolio");

        }

        if (Gdx.input.isKeyPressed(Input.Keys.J)) {

            castle.spawnUnits();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.B)) {
            castle.setId("not");
            castle.buyKnight();

        }
        network.setCastle(castle);


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
