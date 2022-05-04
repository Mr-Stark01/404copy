package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * The main CannonTower class. This class handles the Cannon Tower
 */
public class CannonTower extends Tower {
  /**
   * CONSTRUCTOR
   * @param spawnX float
   * @param spawnY float
   */
  public CannonTower(float spawnX, float spawnY) {
    super(30, 30, 30, 2, spawnX, spawnY);
    setTexture(new Texture("textures/canon-tower.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/canon-tower.png")));
    setSize(2,2);
  }
}
