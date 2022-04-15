package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Castle;

public class Tank extends Unit {
  public Tank(Castle owner) {
    super(5, 15, 30, 1, owner.getSpawnPointX(), owner.getSpawnPointY());
    setTexture(new Texture("textures/tank-unit.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/tank-unit.png")));
    setSize(1,1);
  }

  public String getClassName(){
    return "Tank";
  }
}
