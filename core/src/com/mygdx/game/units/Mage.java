package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Castle;

public class Mage extends Unit {
  public Mage(Castle owner) {
    super(10, 20, 10, 1, owner.getSpawnPointX(), owner.getSpawnPointY());
    setTexture(new Texture("textures/mage-unit.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/mage-unit.png")));
    setSize(1,1);
  }

  public String getClassName(){
    return "Mage";
  }
}
