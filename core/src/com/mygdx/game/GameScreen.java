package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Castle;
import com.mygdx.game.InputHandler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.network.*;
import com.mygdx.game.pathFinding.PathFinder;

import java.util.ArrayList;

public class GameScreen implements Screen {


    static String player;

    private NetworkHandler network;
    static float scale;
    InputHandler inputHandler;
    Castle castle;
    final MyGdxGame game;
    //map from tiled
    private TiledMapTileLayer tileyLayer;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private PathFinder pathFinder;
    //Camera
    OrthographicCamera camera;
    SpriteBatch spriteBatch;

    ArrayList<TiledMapTileLayer.Cell> cellList;


    public GameScreen(final MyGdxGame game, NetworkHandler network){
        this.network=network;

        player=(network.getClass()==ClientHandler.class?"Client":"Server");

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
        pathFinder=new PathFinder(map);
        castle=new Castle(player);

        network.setCastle(castle);
        network.start();
        //Camera viewport settings
        camera = new OrthographicCamera();
        camera.viewportHeight=1080/scale;
        camera.viewportWidth=1920/scale;
        camera.update();
        inputHandler = new InputHandler(camera,scale,castle,pathFinder);
        Gdx.input.setInputProcessor(inputHandler);
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
        inputHandler.update();

        /*for(int x=0;x<layer.getWidth();x++) {
            for(int y=0;y<layer.getHeight();x++) {
                layer.getCell(x,y);
            }
        }*/


        // update castle


        if (Gdx.input.isKeyPressed(Input.Keys.G)) {



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
