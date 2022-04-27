package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.GridPoint;
import com.mygdx.game.units.Unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The main Tower class. This class handles the Towers
 */
public abstract class Tower extends Sprite implements Serializable,Cloneable {
  protected int damage;
  protected int price;
  protected int health;
  protected float x,y;
  protected int range;
  protected Unit target;
  protected boolean spawned;
  protected float spawnX;
  protected float spawnY;
  boolean hasTarget;

  /**
   * CONSTRUCTOR
   * @param damage int
   * @param price int
   * @param health int
   * @param range int
   * @param spawnX float
   * @param spawnY float
   */
  public Tower(
      int damage, int price, int health, int range, float spawnX, float spawnY) {
    super(new Sprite(new Texture("textures/tower-ph.png")));
    this.damage = damage;
    this.price = price;
    this.health = health;
    this.range = range;
    spawnX=(float)Math.floor(spawnX);
    spawnY=(float)Math.floor(spawnY);
    this.spawnX = spawnX;
    this.spawnY = spawnY;
    setX(spawnX);
    setY(spawnY);
    setSize(2, 2);
  }

  /**
   * spawn Tower
   */
  public void spawn() {
    spawned = true;
  }

  /**
   * draw Tower
   * @param spriteBatch SpriteBatch
   * @param enemy Castle
   */
  public void draw(SpriteBatch spriteBatch, Castle enemy) {
    setX(spawnX);
    setY(spawnY);
    if (spawned) {
      update(enemy);
      super.draw(spriteBatch);
    }
  }

  /**
   * update Tower
   * @param enemy Castle
   */
  public void update(Castle enemy) {
    checkTargetPresence();
    if (hasTarget) {
      System.out.println();
      attack();
    } else {
      selectTarget(enemy.getUnits());
    }
  }

  /**
   * select targets for Tower
   * @param enemyKnights ArrayList<Unit>
   */
  public void selectTarget(ArrayList<Unit> enemyKnights) {
    float distanceX;
    float distanceY;
    boolean tmp = false;
    int i = 0;
    while (!tmp && i < enemyKnights.size()) {
      distanceX = Math.abs(enemyKnights.get(i).getX() - this.getX());
      distanceY = Math.abs(enemyKnights.get(i).getY() - this.getY());
      if (distanceX <= range && distanceY <= range) {
        target = enemyKnights.get(i);
        hasTarget = true;
        tmp = true;
      }
      i++;
    }
  }

  /**
   * checking target presence for Tower
   */
  public void checkTargetPresence() {
    if (target != null
        && (Math.abs(target.getX() - this.getX()) > range
            || Math.abs(target.getX() - this.getX()) > range)) {
      target = null;
      hasTarget = false;
    }
  }

  /**
   * attack Units
   */
  public void attack() {
    if (target.getHealth() > 0) {
      target.getDamaged(damage);
    } else {
      target = null;
      hasTarget = false;
    }
  }
  public void draging(float x,float y){
    setX(x);
    setY(y);
  }
  public void dragDraw(SpriteBatch spriteBatch){
    super.draw(spriteBatch);
  }


  @Override
  public synchronized Tower clone() {
    try {
      Tower clone = (Tower) super.clone();
      clone.spawned=spawned;
      clone.spawnX=spawnX;
      clone.spawnY=spawnY;
      if (target != null) {
        clone.target = target.clone();
      }
      clone.reinitialize();
      clone.setX(clone.spawnX);
      clone.setY(clone.spawnY);
      // TODO: copy mutable state here, so the clone can't change the internals of the original
      return clone;
    } catch (CloneNotSupportedException e) {
      System.out.println(e.getStackTrace());
      throw new AssertionError();
    }
  }

  /**
   * get Price
   */
  public int getPrice() {
    return price;
  }

  /**
   * get Health
   */
  public int getHealth() { return health; }

  /**
   * get Damage
   */
  public int getDamage() { return damage; }

  /**
   * get Range
   */
  public int getRange() { return range; }

  public void reinitialize(){
    set(new Sprite(new Texture("textures/tower-ph.png")));
    setSize(2, 2);
  }
}