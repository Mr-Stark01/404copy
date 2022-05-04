package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * The main ArcherTower class. This class handles the Archer Tower
 */
public class ArcherTower extends Tower {
  /**
   * CONSTRUCTOR
   * @param spawnX float
   * @param spawnY float
   */
  public ArcherTower( float spawnX, float spawnY) {
    super(10, 10, 10, 4, spawnX, spawnY);
    setTexture(new Texture("textures/archer-tower.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/archer-tower.png")));
    setSize(2,2);
  }
}
