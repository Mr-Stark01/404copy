package com.mygdx.game.units;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.GridPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Unit extends Sprite implements Serializable,Cloneable {
  public boolean reachedDestinition=false;
  protected int damage;
  protected int price;
  protected int health;
  protected int range;
  protected boolean spawned = false;
  float speed = 0.1f; // increase this to make it faster decrease it to make it slower
  GridPoint previousPoint;
  ArrayList<GridPoint> pathQ = new ArrayList<>();
  private float deltaX = 0;
  private float deltaY = 0;
  private final float spawnPointX;
  private final float spawnPointY;
  private float x,y;

  public Unit(
      int damage,
      int price,
      int health,
      int range,
      float spawnPointX,
      float spawnPointY) {
    super(new Sprite(new Texture("textures/placeholder.png")));
    this.spawnPointX = spawnPointX;
    this.spawnPointY = spawnPointY;
    setX(spawnPointX);
    setY(spawnPointY);
    setSize(1, 1);
    this.damage = damage;
    this.price = price;
    this.health = health;
    this.range = range;
  }

  public void spawn() {
    spawned = true;
    setSpeedToNextPoint();
  }

  public void setPath(GraphPath<GridPoint> path, GridPoint start) {
    previousPoint = start;
    setX(start.getX());
    setY(start.getY());
    for (int i = 1; i < path.getCount(); i++) {
      pathQ.add(path.get(i));
    }
  }

  private void setSpeedToNextPoint() {
    GridPoint nextPoint = pathQ.get(0);
    float angle =
        MathUtils.atan2(
            nextPoint.getY() - previousPoint.getY(), nextPoint.getX() - previousPoint.getX());
    deltaX = MathUtils.cos(angle) * speed;
    deltaY = MathUtils.sin(angle) * speed;
  }

  private void checkCollision() {
    if (pathQ.size() > 0) {
      GridPoint targetCity = pathQ.get(0);
      if (Vector2.dst(getX(), getY(), targetCity.getX(), targetCity.getY()) < 0.1) {
        reachNextPoint();
      }
    }
  }

  public void step() {
    setX(getX() + deltaX);
    setY(getY() + deltaY);
    x=getX();
    y=getY();
    checkCollision();
  }

  private void reachNextPoint() {
    GridPoint nextPoint = pathQ.get(0);
    setX(nextPoint.getX());
    setY(nextPoint.getY());
    this.previousPoint = nextPoint;
    pathQ.remove(0);
    if (pathQ.size() == 0) {
      reachDestination();
    } else {
      setSpeedToNextPoint();
    }
  }

  private void reachDestination() {
    deltaX = 0;
    deltaY = 0;
    reachedDestinition=true;
  }

  public void draw(SpriteBatch spriteBatch) {
    if (spawned) {
      step();
    }
    super.draw(spriteBatch);
  }

  // Get Set
  public int getPrice() {
    return price;
  }
  public int getHealth() {
    return health;
  }
  public int getRange() {
    return range;
  }
  public int getDamage() {
    return damage;
  }
  public void getDamaged(int damage) {
    health -= damage;
  }
  public boolean getSpawned(){
    return spawned;
  }

  @Override
  public synchronized Unit clone() {
    try {
      Unit clone = (Unit) super.clone();
      clone.pathQ=new ArrayList<GridPoint>();
      clone.spawned=spawned;
      clone.previousPoint = new GridPoint(previousPoint.getX(), previousPoint.getY(), previousPoint.getName());
      Iterator<GridPoint> it = pathQ.iterator();
      while (it.hasNext()) {
        GridPoint s = it.next();
        GridPoint newS = new GridPoint(s.getX(),s.getY() ,s.getName());
        clone.pathQ.add(newS);
      }
      clone.reinitialize();
      clone.setX(x);
      clone.setY(y);
      // TODO: copy mutable state here, so the clone can't change the internals of the original
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public void reinitialize(){
    set(new Sprite(new Texture("textures/placeholder.png")));
    setSize(1, 1);
  }

  public String getClassName(){
    return "nope";
  }
}

