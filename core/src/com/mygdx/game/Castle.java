package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.pathFinding.GridPoint;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.towers.FireTower;
import com.mygdx.game.towers.Tower;
import com.mygdx.game.units.Archer;
import com.mygdx.game.units.Mage;
import com.mygdx.game.units.Tank;
import com.mygdx.game.units.Unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/** Represent the player and the castle both. */
public class Castle implements Serializable,Cloneable {
  protected float health = 500f, gold = 5000f;
  protected int archerPrice = 50, magePrice = 20, tankPrice = 30;
  protected ArrayList<Tower> towers;
  protected ArrayList<Unit> units;
  private final String player;
  private float spawnPointX, spawnPointY;
  /**
   * Creates a castle for the player and set's it's coordinates.
   *
   * @param player
   */
  public Castle(String player) {
    this.player = player;
    towers = new ArrayList<>();
    units = new ArrayList<>();
  }

  /**
   * Buys a knight and gives it the route that it will have to follow.
   *
   * @param pathFinder
   */
  public void buyArcher(PathFinder pathFinder) {
    if (gold >= archerPrice) {
      gold -= archerPrice;
      units.add(new Archer(this));
      pathFinder.findWay(units.get(units.size() - 1));
    }
  }

  public void buyTank(PathFinder pathFinder) {
    if (gold >= tankPrice) {
      gold -= tankPrice;
      units.add(new Tank(this));
      pathFinder.findWay(units.get(units.size() - 1));
    }
  }

  public void buyMage(PathFinder pathFinder) {
    if (gold >= magePrice) {
      gold -= magePrice;
      units.add(new Mage(this));
      pathFinder.findWay(units.get(units.size() - 1));
    }
  }

  /** Spawns the units that were bought in the buy phase. */
  public void spawnUnits() {
    for (Unit unit : units) {
      unit.spawn();
    }
  }

  public void buyTower(Tower tower) {
    if (tower.getPrice() <= gold) {
      gold -= tower.getPrice();
      towers.add(tower);
    }
  }

  public void spawnTowers() {
    for (Tower tower : towers) {
      tower.spawn();
    }
  }
  /**
   * Draws anything that is connected to this specific player. Such as towers and units.
   *
   * @param spriteBatch
   */
  public void draw(SpriteBatch spriteBatch) {

    for (Unit unit : units) {
      unit.draw(spriteBatch);
      if(unit.reachedDestinition){
        this.health=this.health-unit.getDamage();
      }
    }
    for (Tower tower : towers) {
      tower.draw(spriteBatch, this); // enemy lesz this helyett
    }
  }
  // Getter and Setters
  public void setSpawn(float x, float y) {
    spawnPointX = x;
    spawnPointY = y;
  }

  public float getSpawnPointX() {
    return this.spawnPointX;
  }

  public float getSpawnPointY() {
    return this.spawnPointY;
  }

  public int getArcherPrice() {
    return archerPrice;
  }

  public int getMagePrice() {
    return magePrice;
  }

  public int getTankPrice() {
    return tankPrice;
  }

  public ArrayList<Tower> getTowers() {
    return towers;
  }

  public float getGold() {
    return gold;
  }

  public float getHealth() {
    return health;
  }

  public void setGold(float gold) {
    this.gold = gold;
  }

  public ArrayList<Unit> getUnits() {
    return units;
  }

  public void reinitialize(){
    for (Unit unit : units) {
      unit.reinitialize();
    }
    for (Tower tower : towers) {
      tower.reinitialize();
    }
  }

  @Override
  public Castle clone() {
    try {
      Castle clone = (Castle) super.clone();
      clone.towers=new ArrayList<>();
      clone.units=new ArrayList<>();

      for (Unit unit : units) {
          Unit newUnit = unit.clone();
          clone.units.add(newUnit);
      }
      for (Tower tower : towers) {
        Tower newTower = tower.clone();
        clone.towers.add(newTower);
      }
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public boolean check(ArrayList<Unit> units,ArrayList<Tower> towers){
    return units==this.units && towers==this.towers;
  }
}
