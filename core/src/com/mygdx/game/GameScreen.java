package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.network.ClientHandler;
import com.mygdx.game.network.NetworkHandler;
import com.mygdx.game.pathFinding.PathFinder;

import java.util.ArrayList;

/**
 * The main game class. This class handles the base tick of the game and any action have to be
 * originated from here.
 */
public class GameScreen implements Screen {
  static String player;
  static float scale;
  final MyGdxGame game;
  private final NetworkHandler network;
  InputHandler inputHandler;
  Castle castle;
  // Camera
  OrthographicCamera camera;
  SpriteBatch spriteBatch;
  ArrayList<TiledMapTileLayer.Cell> cellList;
  // map from tiled
  private TiledMapTileLayer tileyLayer;
  private TiledMap map;
  private OrthogonalTiledMapRenderer renderer;
  private PathFinder pathFinder;

  /**
   * Everything thats needs to be initiated should be done here or in the show if it's a display
   * thing.
   *
   * @param game For handling inputs and any interactions with the player
   * @param network For handling the exchange of the Castle classes.
   */
  public GameScreen(final MyGdxGame game, NetworkHandler network) {
    this.network = network;
    player = (network.getClass() == ClientHandler.class ? "Client" : "Server");
    this.game = game;
    spriteBatch = new SpriteBatch();
  }

  /** Anything that will be shown to the player Will be initiated here. */
  @Override
  public void show() {
    // Importing the map itself from maps folder
    TmxMapLoader loader = new TmxMapLoader();
    map = loader.load("maps/map_01.tmx");
    tileyLayer = (TiledMapTileLayer) map.getLayers().get(0);
    scale = (float) tileyLayer.getTileWidth();
    renderer = new OrthogonalTiledMapRenderer(map, 1 / scale);
    // Creaating Castle
    castle = new Castle(player);
    // Creating a pathfinder
    pathFinder = new PathFinder(map, player);
    castle.setSpawn(pathFinder.getStart().getX(), pathFinder.getStart().getY());
    // Network setup
    network.setCastle(castle);
    network.start();
    // Camera viewport settings
    camera = new OrthographicCamera();
    camera.viewportHeight = 1080 / scale;
    camera.viewportWidth = 1920 / scale;
    camera.update();
    // For handling game inputs
    inputHandler = new InputHandler(camera, scale, castle, pathFinder);
    Gdx.input.setInputProcessor(inputHandler);
  }

  /**
   * The main game clock so to speak this is the main game loop.
   *
   * @param delta
   */
  @Override
  public void render(float delta) {
    // clearing screen
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    renderer.setView(camera);
    renderer.render();
    // To draw anything thats needed
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    castle.draw(spriteBatch);
    spriteBatch.end();
    inputHandler.update();
    network.setCastle(castle);
    // Updatign camera position
    camera.update();
    // Exit on escape
    if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
      this.dispose();
      Gdx.app.exit();
    }
  }

  /**
   * For resizing of the screen to be rendered
   *
   * @param width
   * @param height
   */
  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}

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
