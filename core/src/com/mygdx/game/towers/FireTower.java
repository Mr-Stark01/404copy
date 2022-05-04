package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * The main FireTower class. This class handles the Fire Tower
 */
public class FireTower extends Tower {
  /**
   * CONSTRUCTOR
   * @param spawnX float
   * @param spawnY float
   */
  public FireTower( float spawnX, float spawnY) {
    super(20, 20, 20, 3, spawnX, spawnY);
    setTexture(new Texture("textures/mage-tower.png"));
  }

  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/mage-tower.png")));
    setSize(2,2);
  }

}
