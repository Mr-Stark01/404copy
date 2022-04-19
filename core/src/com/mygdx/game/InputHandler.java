package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.towers.ArcherTower;
import com.mygdx.game.towers.CannonTower;
import com.mygdx.game.towers.FireTower;
import com.mygdx.game.towers.Tower;

import java.util.HashMap;
import java.util.Map;
// Currently set up to do both polling and eventhandling which is stupid

/** For handling inputs by the player. This works by interupt rather then pooling. */
public class InputHandler implements InputProcessor {
  OrthographicCamera camera;
  Map<Integer, Boolean> flags;
  Castle castle;
  PathFinder pathFinder;
  float scale;


  public InputHandler(
      OrthographicCamera camera, float scale, Castle castle, PathFinder pathFinder) {
    this.scale = scale;
    this.camera = camera;
    this.castle = castle;
    this.pathFinder = pathFinder;
    flags = new HashMap<>();
    flags.put(Input.Keys.LEFT, false);
    flags.put(Input.Keys.UP, false);
    flags.put(Input.Keys.RIGHT, false);
    flags.put(Input.Keys.DOWN, false);
    flags.put(Input.Keys.P, false);
    flags.put(Input.Keys.M, false);
  }

  /**
   * This is massively rarded and makes the whole thing not work by interrupt. Which is a design
   * genius step by me congrats DIE.
   */
  public void update() {
    if (flags.get(Input.Keys.RIGHT)) {
      camera.translate(50 / scale, 0);
    }
    if (flags.get(Input.Keys.LEFT)) {
      camera.translate(-50 / scale, 0);
    }
    if (flags.get(Input.Keys.UP)) {
      camera.translate(0, 50 / scale);
    }
    if (flags.get(Input.Keys.DOWN)) {
      camera.translate(0, -50 / scale);
    }
    if (flags.get(Input.Keys.P)) {
      if (camera.zoom > 0.1) {
        camera.zoom = camera.zoom + (-0.02f / scale);
      }
    }
    if (flags.get(Input.Keys.M)) {
      if (camera.zoom < scale * 3) {
        camera.zoom = camera.zoom + 0.02f / scale;
      }
    }
  }
  // cool so this is better but I still hate it
  /**
   * Handles keypresses
   *
   * @param keycode The code of the key that was pressed
   * @return True if this has been implemented and false if not.
   */
  @Override
  public boolean keyDown(int keycode) {
    if (Input.Keys.A == keycode) {
      Vector3 T1 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
      if(castle.getGold() >= 10){
        Tower tower = new ArcherTower(T1.x, T1.y); // mouse koordináták kellenek
        castle.buyTower(tower);
        castle.spawnTowers();
      }
    }
    if (Input.Keys.C == keycode) {
      Vector3 T1 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
      if(castle.getGold() >= 30) {
        Tower tower = new CannonTower(T1.x, T1.y); // mouse koordináták kellenek
        castle.buyTower(tower);
        castle.spawnTowers();
      }
    }
    if (Input.Keys.F == keycode) {
      Vector3 T1 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
      if(castle.getGold() >= 20) {
        Tower tower = new FireTower(T1.x, T1.y); // mouse koordináták kellenek
        castle.buyTower(tower);
        castle.spawnTowers();
      }
    }
    if (Input.Keys.B == keycode) {
      if(castle.getGold() >= 10) {
        castle.buyArcher(pathFinder);
      }
    }
    if (Input.Keys.T == keycode) {
      if(castle.getGold() >= 15) {
        castle.buyTank(pathFinder);
      }
    }
    if (Input.Keys.M == keycode) {
      if(castle.getGold() >= 20) {
        castle.buyMage(pathFinder);
      }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.J)) {
      castle.spawnUnits();
    }
    flags.put(keycode, true);
    return true;
  }
  /**
   * Handles key release
   *
   * @param keycode The code of the key that was pressed
   * @return True if this has been implemented and false if not.
   */
  @Override
  public boolean keyUp(int keycode) {
    flags.put(keycode, false);
    return true;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  /**
   * Handles mouse or touch screen
   *
   * @param screenX The X coordinates for the press
   * @param screenY The Y coordinates for the press
   * @param pointer
   * @param button The button that was pressed if any.
   * @return
   */
  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    final Vector3 mouseInWorld2D = new Vector3();
    mouseInWorld2D.x = screenX;
    mouseInWorld2D.y = screenY;
    mouseInWorld2D.z = 0;
    camera.unproject(mouseInWorld2D);

    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  /**
   * If the mouse left key is being pressed this will constantly update the coordinates.
   *
   * @param screenX
   * @param screenY
   * @param pointer
   * @return
   */
  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    float x = Gdx.input.getDeltaX() / scale;
    float y = Gdx.input.getDeltaY() / scale;
    x = x * camera.zoom;
    y = y * camera.zoom;
    camera.translate(-x, y);
    return true;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  /**
   * If the scroll wheel is in use.
   *
   * @param amountX
   * @param amountY
   * @return
   */
  @Override
  public boolean scrolled(float amountX, float amountY) {
    amountY = amountY / 10;

    if (amountY > 0 && camera.zoom + amountY < 3) {
      camera.zoom = camera.zoom + amountY;
    }
    if (amountY < 0 && camera.zoom + amountY > 0.1) {
      camera.zoom = camera.zoom + amountY;
    }
    return true;
  }
}
