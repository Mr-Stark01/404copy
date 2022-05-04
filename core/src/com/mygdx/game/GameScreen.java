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
import com.mygdx.game.towers.Tower;

import java.time.Duration;
import java.time.Instant;
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
  Tower tower;
  // Camera
  OrthographicCamera camera;
  SpriteBatch spriteBatch;
  ArrayList<TiledMapTileLayer.Cell> cellList;

  protected Instant now;
  protected boolean needNewTime=true;
  // map from tiled
  private TiledMapTileLayer tileyLayer;
  private TiledMap map;
  private OrthogonalTiledMapRenderer renderer;
  private PathFinder pathFinder;
  private final SynchronousQueue<Castle> queue = new SynchronousQueue<>();
  private final Hud hud;
  private boolean buildRound =true;

  int picHeightWidth;
  int picY;
  int archerTowerImgX;
  int fireTowerImgX;
  int cannonTowerImgX;
  int archerUnitImgX;
  int mageUnitImgX;
  int tankUnitImgX;

  Texture endscreen_w;
  Texture endscreen_l;
  Texture endscreen_d;

  int exitButtonWid;
  int exitButtonHei;
  int exitButtonY;
  int exitButtonX;

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
    castle = new Castle(player, buildRound);

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
    inputHandler = new InputHandler(camera, scale, castle, pathFinder,hud,spriteBatch);
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

    endscreen_w = new Texture("menu/endsc_win.png");
    endscreen_l = new Texture("menu/endsc_lose.png");
    endscreen_d = new Texture("menu/endsc_draw.png");

    exitButtonWid = 200;
    exitButtonHei = 90;
    exitButtonY = 100;
    exitButtonX = 1920/2-exitButtonWid/2;
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

    inputHandler.update();
    network.setCastle(castle.clone());
    if(inputHandler.tower!=null){
      tower=inputHandler.tower;
      spriteBatch.begin();
      tower.dragDraw(spriteBatch);
      spriteBatch.end();
    }
    spriteBatch.begin();
    inputHandler.draw(spriteBatch);
    spriteBatch.end();
    // Updating Castle
    if (network.castleArrived()) {
      if(network.isNew()){
        EnemyCastle=network.getEnemyCastle().clone();
      }
      // Well would you look at this a bad solution to the problem who would have tought.

        if (player.equals("Client")) {
          buildRound=EnemyCastle.getBuildRound();
          castle.setBuildRound(EnemyCastle.getBuildRound());
          castle.spawnUnits();
          hud.setTime(EnemyCastle.getTime());
        }
      if (player.equals("Server")) {
        if (castle.isReady() && EnemyCastle.isReady()){
          buildRound=false;
          castle.setBuildRound(buildRound);
          castle.spawnUnits();
          castle.setReady(false);
        }
        if (buildRound && needNewTime) {
          needNewTime = false;
          now = Instant.now();
        }
        if(!needNewTime && buildRound ){
          System.out.println(Duration.between(now, Instant.now()).getSeconds());
          hud.setTime(Math.abs(Duration.between(now, Instant.now()).getSeconds()-60));
          castle.setTime(Math.abs(Duration.between(now, Instant.now()).getSeconds()-60));
        }
        if (buildRound && Duration.between(now, Instant.now()).compareTo(Duration.ofSeconds(60)) > 0) { // TODO: Na szoval van itt ez a now változo és enek a küllönbségét kéne kiteni másodpercre a hudba if possible
          buildRound = false;
          castle.setBuildRound(buildRound);
          castle.spawnUnits();
        }
        if(!buildRound && !needNewTime && castle.getSpawned().size() == 0 && EnemyCastle.getSpawned().size() == 0 && castle.getUnits().size()==0 && EnemyCastle.getUnits().size()==0) {
          needNewTime = true;
          buildRound = true;
          castle.setBuildRound(buildRound);
          now = Instant.now();
        }
      }
      spriteBatch.begin();
      castle.draw(spriteBatch,EnemyCastle,delta);
      spriteBatch.end();
      spriteBatch.begin();
      EnemyCastle.draw(spriteBatch,castle,delta);
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

    hud.setGold(castle.getGold());
    hud.setHealth(castle.getHealth());

    // endscreen working only online
    game.batch.begin();

    if(castle != null && EnemyCastle != null){
      if(castle.getHealth() <= 0 && EnemyCastle.getHealth() > 0){
        game.batch.draw(endscreen_l, 0 , 0, 1920, 1080);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
          Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
          camera.unproject(vec);
          if(vec.x < exitButtonX + exitButtonWid && vec.x > exitButtonX && vec.y > exitButtonY  &&  vec.y < exitButtonY + exitButtonHei){
            this.dispose();
            Gdx.app.exit();
          }
        }
      }
      else if(castle.getHealth() > 0 && EnemyCastle.getHealth() <= 0){
        game.batch.draw(endscreen_w, 0 , 0, 1920, 1080);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
          Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
          camera.unproject(vec);
          if(vec.x < exitButtonX + exitButtonWid && vec.x > exitButtonX && vec.y > exitButtonY  &&  vec.y < exitButtonY + exitButtonHei){
            this.dispose();
            Gdx.app.exit();
          }
        }
      }
      else if(castle.getHealth() <= 0 && EnemyCastle.getHealth() <= 0){
        game.batch.draw(endscreen_d, 0 , 0, 1920, 1080);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
          Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
          camera.unproject(vec);
          if(vec.x < exitButtonX + exitButtonWid && vec.x > exitButtonX && vec.y > exitButtonY  &&  vec.y < exitButtonY + exitButtonHei){
            this.dispose();
            Gdx.app.exit();
          }
        }
      }
    }

    game.batch.end();
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

  /**
   * hiding the screen
   */
  @Override
  public void hide() {
    dispose();
  }

  /**
   * hiding the screen
   */
  @Override
  public void dispose() {
    map.dispose();
    renderer.dispose();
  }

  public Instant getNow(){
    return now;
  }
}
