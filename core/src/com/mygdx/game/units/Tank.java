package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Castle;

/**
 * The main Tank class. This class handles the Tank Unit
 */
public class Tank extends Unit {
  /**
   * CONSTRUCTOR
   * @param owner Castle
   */
  public Tank(Castle owner) {
    super(5, 15, 300, 1, owner.getSpawnPointX(), owner.getSpawnPointY());
    setTexture(new Texture("textures/tank-unit.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/tank-unit.png")));
    setSize(1,1);
  }

  /**
   * get Class Name
   */
  public String getClassName(){
    return "Tank";
  }
}
