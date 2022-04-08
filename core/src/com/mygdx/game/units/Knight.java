package com.mygdx.game.units;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.mygdx.game.pathFinding.GridPoint;

import java.io.Serializable;

public class Knight extends Sprite implements Serializable {

  protected int attackPower = 10;
  protected boolean spawned = false;
  /*
  private int health;
  */
  float speed = 1 / 32f;
  GridPoint previousPoint;

  GraphPath<GridPoint> path;
  Queue<GridPoint> pathQueue = new Queue<>();
  private float deltaX = 0;
  private float deltaY = 0;

  public Knight(float spawnPointX, float spawnPointY) {
    super(new Sprite(new Texture("textures/placeholder.png")));

    setX(spawnPointX);
    setY(spawnPointY);
    setSize(1, 1);
  }

  public void draw(SpriteBatch spriteBatch) {

    if (spawned) {
      update();

      super.draw(spriteBatch);
    }
  }

  public float getX() {
    return super.getX();
  }

  public void spawn() {
    spawned = true;
  }

  public int attack() {
    return attackPower;
  }

  public void getDamaged(int dmg) {
    attackPower -= dmg;
  }

  /* FONTOS */
  public void setPath(GraphPath<GridPoint> path, GridPoint start) {

    previousPoint = start;
    setX(start.getX());
    setY(start.getY());
    this.path = path;
    for (int i = 1; i < path.getCount(); i++) {
      pathQueue.addLast(path.get(i));
    }
  }

  private void setSpeedToNextPoint() {
    GridPoint nextPoint = pathQueue.first();
    float angle =
        MathUtils.atan2(
            nextPoint.getY() - previousPoint.getX(), nextPoint.getX() - previousPoint.getY());
    deltaX = MathUtils.cos(angle) * speed;
    deltaY = MathUtils.sin(angle) * speed;
  }

  private void checkCollision() {
    if (pathQueue.size > 0) {
      GridPoint targetCity = pathQueue.first();
      if (Vector2.dst(getX(), getY(), targetCity.getX(), targetCity.getY()) < 5) {
        reachNextPoint();
      }
    }
  }

  public void step() {
    setX(getX() + deltaX);
    setY(getY() + deltaY);
    checkCollision();
  }

  private void reachNextPoint() {

    GridPoint nextPoint = pathQueue.first();

    setX(nextPoint.getX());
    setY(nextPoint.getY());

    this.previousPoint = nextPoint;
    pathQueue.removeFirst();

    if (pathQueue.size == 0) {
      reachDestination();
    } else {
      setSpeedToNextPoint();
    }
  }

  private void reachDestination() {
    deltaX = 0;
    deltaY = 0;
  }

  // bs currently
  public void update() {
    step();
  }
  /* FONTOS */
  /*
  if (!collisionLayer.getCell((int) getX()/32+1, (int) getY()/32).getTile().getProperties().containsKey("blocked")) {
      setX(getX() + 10);

  }

  // Manual Movement

  if (Gdx.input.isKeyPressed(Input.Keys.W) && !collisionLayer.getCell((int) getX()/32+1, (int) getY()/32).getTile().getProperties().containsKey("blocked")) {
      if (getY()+65<=3200) {
          setY(getY() + 32);
      }
  }
  if (Gdx.input.isKeyPressed(Input.Keys.D) && !collisionLayer.getCell((int) getX()/32, (int) getY()/32+1).getTile().getProperties().containsKey("blocked")) {

      if (getX()+65<=9600) {
          setX(getX() + 32);
      }
  }
  if (Gdx.input.isKeyPressed(Input.Keys.S)&&!collisionLayer.getCell((int) getX()/32, (int) (getY()/32)-1).getTile().getProperties().containsKey("blocked")) {
      if (getY()-33>=0) {
          setY(getY() - 32);
      }
  }
  if (Gdx.input.isKeyPressed(Input.Keys.A) && !collisionLayer.getCell((int) getX()/32-1, (int) getY()/32).getTile().getProperties().containsKey("blocked")) {
      if (getX()-33>=0) {
          setX(getX() - 32);
      }
  }

   */

  /* idk
      public TiledMapTileLayer getCollisionLayer(){
          return collisionLayer;
      }

      public void SetCollisionLayer(TiledMapTileLayer collisionLayer){
          this.collisionLayer=collisionLayer;
      }

      @Override
      public boolean keyDown(int keycode) {
          switch (keycode){
              case Input.Keys.S:
                  System.out.println("here");
                  if (!collisionLayer.getCell((int) getX()/32, (int) getY()/32-1).getTile().getProperties().containsKey("blocked")) {
                      moveDown();
                  }
                  break;
          }

          return false;
      }

      @Override
      public boolean keyUp(int keycode) {
          return false;
      }

      @Override
      public boolean keyTyped(char character) {
          return false;
      }

      @Override
      public boolean touchDown(int screenX, int screenY, int pointer, int button) {
          return false;
      }

      @Override
      public boolean touchUp(int screenX, int screenY, int pointer, int button) {
          return false;
      }

      @Override
      public boolean touchDragged(int screenX, int screenY, int pointer) {
          return false;
      }

      @Override
      public boolean mouseMoved(int screenX, int screenY) {
          return false;
      }

      @Override
      public boolean scrolled(float amountX, float amountY) {
          return false;
      }

  */

  public int getAttackPower() {
    return attackPower;
  }
}
