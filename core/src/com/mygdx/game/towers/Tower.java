package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Castle;
import com.mygdx.game.units.Unit;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Tower extends Sprite implements Serializable {
  protected int damage;
  protected int price;
  protected int health;

  protected int range;
  protected Unit target;
  protected boolean spawned;
  protected float spawnX;
  protected float spawnY;
  protected Castle owner;
  boolean hasTarget;

  // CONSTRUCTORS
  public Tower(
      int damage, int price, int health, int range, Castle owner, float spawnX, float spawnY) {
    super(new Sprite(new Texture("textures/tower-ph.png")));
    this.damage = damage;
    this.price = price;
    this.health = health;
    this.range = range;
    this.owner = owner;
    this.spawnX = spawnX;
    this.spawnY = spawnY;
    setX(spawnX);
    setY(spawnY);
    setSize(1, 1);
  }

  // FUNCTIONS

  public void spawn() {
    spawned = true;
  }

  public void draw(SpriteBatch spriteBatch, Castle enemy) {
    if (spawned) {
      update(enemy);
      super.draw(spriteBatch);
    }
  }

  public void update(Castle enemy) {
    checkTargetPresence();
    if (hasTarget) {
      System.out.println(target.getX());
      attack();
    } else {
      selectTarget(enemy.getUnits());
    }
  }

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

  public void checkTargetPresence() {
    if (target != null
        && (Math.abs(target.getX() - this.getX()) > range
            || Math.abs(target.getX() - this.getX()) > range)) {
      target = null;
      hasTarget = false;
    }
  }

  public void attack() {
    if (target.getHealth() > 0) {
      target.getDamaged(damage);
      System.out.println(target.getHealth());
    } else {
      target = null;
      hasTarget = false;
    }
    System.out.println(hasTarget);
  }

  // GETTERS & SETTERS
  public int getPrice() {
    return price;
  }

  public void setOwner(Castle owner) {
    this.owner = owner;
  }
}
