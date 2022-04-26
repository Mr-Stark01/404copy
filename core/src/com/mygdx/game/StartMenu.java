package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.network.Client;
import com.mygdx.game.network.ClientHandler;
import com.mygdx.game.network.Server;
import com.mygdx.game.network.ServerHandler;

import java.util.regex.Pattern;

/**
 * The main StartMenu class. This class handles the game Start
 */
public class StartMenu implements Screen, TextInputListener {

  final MyGdxGame game;
  OrthographicCamera camera;

  Texture bg;

  //Button variables
  int backButtonX;
  int backbuttonY;
  int backButtonWid;
  int backButtonHei;
  int clientButtonY;
  int serverButtonY;
  int serverButtonWid;
  int buttonX;
  int buttonWid;
  int buttonHei;

  String text;

  /**
   * Everything thats needs to be initiated should be done here
   *
   * @param game final MyGdxGame
   */
  public StartMenu(final MyGdxGame game) {
    this.game = game;
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1920, 1080);
  }

  /**
   * Checcking for correct IP
   *
   * @param text String
   */
  public boolean correctIPCheck(String text) {
    String IPV4_REGEX = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
    Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);
    if (text == null) {
      return false;
    }
    if (!IPv4_PATTERN.matcher(text).matches()) {
      return false;
    }
    String[] parts = text.split("\\.");
    // verify that each of the four subgroups of IPv4 addresses is legal
    try {
      for (String segment : parts) {
        // x.0.x.x is accepted but x.01.x.x is not
        if (Integer.parseInt(segment) > 255 || (segment.length() > 1 && segment.startsWith("0"))) {
          return false;
        }
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /** Anything that will be shown to the player Will be initiated here. */
  @Override
  public void show() {
    // ScreenUtils.clear(255, 98, 0, 1);
    bg = new Texture("menu/start_menu.png");
    backButtonWid = 250;
    backButtonHei = 100;
    backButtonX = 1920 - backButtonWid - 50;
    backbuttonY = 50;
    buttonWid = 300;
    buttonX = 1920 / 2 - buttonWid / 2;
    buttonHei = 90;
    clientButtonY = 600;
    serverButtonY = 480;
    serverButtonWid = 250;
    text = "";
  }

  /**
   * Updating the screen
   *
   * @param delta float
   */
  @Override
  public void render(float delta) {
    camera.update();
    game.batch.setProjectionMatrix(camera.combined);
    game.batch.begin();
    game.batch.draw(bg, 0, 0, 1920, 1080);
    game.font.setColor(0, 0, 0, 1);
    game.font.draw(game.batch, "Game by 404", 1700, 40);
    // joinButton
    if (Gdx.input.justTouched()) {
      if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
        Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(vec);
        if (vec.x < buttonX - ((buttonWid - buttonWid) / 2) + buttonWid
            && vec.x > buttonX - ((buttonWid - buttonWid) / 2)
            && vec.y > clientButtonY
            && vec.y < clientButtonY + buttonHei) {
          // join game
          if (text == "") {
            Gdx.input.getTextInput(this, "Set IP address", "", "192.168.0.210");
          }
          if (text != "") {
            ClientHandler clh = new ClientHandler(new Client(), text);
            game.setScreen(new GameScreen(game, clh));
          }
          dispose();
        }
      }

      // hostButton
      if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
        Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(vec);
        if (vec.x < buttonX - ((serverButtonWid - buttonWid) / 2) + serverButtonWid
            && vec.x > buttonX - ((serverButtonWid - buttonWid) / 2)
            && vec.y > serverButtonY
            && vec.y < serverButtonY + buttonHei) {
          // host game
          ServerHandler serverHandler = new ServerHandler(new Server());
          game.setScreen(new GameScreen(game, serverHandler));
          dispose();
        }
      }
    }

    // backButton
    if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
      Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(vec);
      if (vec.x < backButtonX + backButtonWid
          && vec.x > backButtonX
          && vec.y > backbuttonY
          && vec.y < backbuttonY + backButtonHei) {
        game.setScreen(new MainMenu(game));
        dispose();
      }
    }
    game.batch.end();
  }

  /**
   * resizing the screen
   *
   * @param width int
   * @param height int
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
  public void dispose() {}

  /**
   * text set
   * @param text String
   */
  @Override
  public void input(String text) {
    if (correctIPCheck(text)) {
      this.text = text;
    }
  }

  /**
   * text handle
   */
  @Override
  public void canceled() {
    text = "Cancelled";
  }
}
