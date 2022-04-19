package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.network.ClientHandler;
import com.mygdx.game.network.NetworkHandler;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.screens.Hud;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

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
  Castle EnemyCastle;
  // Camera
  OrthographicCamera camera;
  SpriteBatch spriteBatch;
  ArrayList<TiledMapTileLayer.Cell> cellList;
  // map from tiled
  private TiledMapTileLayer tileyLayer;
  private TiledMap map;
  private OrthogonalTiledMapRenderer renderer;
  private PathFinder pathFinder;
  private SynchronousQueue<Castle> queue = new SynchronousQueue<>();
  private Hud hud;

  int picHeightWidth;
  int picY;
  int archerTowerImgX;
  int fireTowerImgX;
  int cannonTowerImgX;
  int archerUnitImgX;
  int mageUnitImgX;
  int tankUnitImgX;

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

    hud = new Hud(spriteBatch);
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
    network.setCastle(castle.clone());
    network.start();

    // Camera viewport settings
    camera = new OrthographicCamera();
    camera.viewportHeight = 1080 / scale;
    camera.viewportWidth = 1920 / scale;
    camera.update();

    // For handling game inputs
    inputHandler = new InputHandler(camera, scale, castle, pathFinder);
    Gdx.input.setInputProcessor(inputHandler);

    // Hud
    hud.setHealth(castle.getHealth());
    hud.setGold(castle.getGold());

    picHeightWidth = 80;
    picY = 10;

    archerTowerImgX = 130;
    fireTowerImgX = 460;
    cannonTowerImgX = 795;
    archerUnitImgX = 1122;
    mageUnitImgX = 1425;
    tankUnitImgX = 1730;
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

    // To draw anything that's needed
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    castle.draw(spriteBatch);
    spriteBatch.end();
    inputHandler.update();
    network.setCastle(castle.clone());

    // Updating Castle
    if (network.castleArrived()) {
      if(network.isNew()){
        EnemyCastle=network.getEnemyCastle().clone();
      }
      spriteBatch.begin();
      EnemyCastle.draw(spriteBatch);
      spriteBatch.end();
    }

    //Hud update
    hud.update(1f);
    game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
    hud.stage.draw();

    // Updating camera position
    camera.update();

    // Exit on escape
    if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
      this.dispose();
      Gdx.app.exit();
    }

    // Putting down tower and unit

    //archerTower
    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
      Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
      camera.unproject(vec);
      if(vec.x < archerTowerImgX + picHeightWidth && vec.x  > archerTowerImgX && vec.y > picY && vec.y < picY + picHeightWidth){
        //buy if possible
        if(castle.getGold() >= 10){
          inputHandler.keyDown(Input.Keys.A);
          castle.setGold(castle.getGold()-10);
        }
      }
    }

    //fireTower
    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
      Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
      camera.unproject(vec);
      if(vec.x < fireTowerImgX + picHeightWidth && vec.x  > fireTowerImgX && vec.y > picY && vec.y < picY + picHeightWidth){
        //buy if possible
        if(castle.getGold() >= 20){
          inputHandler.keyDown(Input.Keys.F);
          castle.setGold(castle.getGold()-20);
        }
      }
    }

    //cannonTower
    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
      Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
      camera.unproject(vec);
      if(vec.x < cannonTowerImgX + picHeightWidth && vec.x  > cannonTowerImgX && vec.y > picY && vec.y < picY + picHeightWidth){
        //buy if possible
        if(castle.getGold() >= 30){
          inputHandler.keyDown(Input.Keys.C);
          castle.setGold(castle.getGold()-30);
        }
      }
    }

    //archerUnit
    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
      Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
      camera.unproject(vec);
      if(vec.x < archerUnitImgX + picHeightWidth && vec.x  > archerUnitImgX && vec.y > picY && vec.y < picY + picHeightWidth){
        //buy if possible
        if(castle.getGold() >= 10){
          inputHandler.keyDown(Input.Keys.B);
          castle.setGold(castle.getGold()-10);
        }
      }
    }

    //mageUnit
    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
      Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
      camera.unproject(vec);
      if(vec.x < mageUnitImgX + picHeightWidth && vec.x  > mageUnitImgX && vec.y > picY && vec.y < picY + picHeightWidth){
        //buy if possible
        if(castle.getGold() >= 20){
          inputHandler.keyDown(Input.Keys.M);
          castle.setGold(castle.getGold()-20);
        }
      }
    }

    //tankUnit
    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
      Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
      camera.unproject(vec);
      if(vec.x < tankUnitImgX + picHeightWidth && vec.x  > tankUnitImgX && vec.y > picY && vec.y < picY + picHeightWidth){
        //buy if possible
        if(castle.getGold() >= 15){
          inputHandler.keyDown(Input.Keys.T);
          castle.setGold(castle.getGold()-15);
        }
      }
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
