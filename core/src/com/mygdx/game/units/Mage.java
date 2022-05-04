package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Castle;

/**
 * The main Mage class. This class handles the Mage Unit
 */
public class Mage extends Unit {
  /**
   * CONSTRUCTOR
   * @param owner Castle
   */
  public Mage(Castle owner) {
    super(10, 20, 50, 1, owner.getSpawnPointX(), owner.getSpawnPointY());
    setTexture(new Texture("textures/mage-unit.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/mage-unit.png")));
    setSize(1,1);
  }

  /**
   * get Class Name
   */
  public String getClassName(){
    return "Mage";
  }
}
