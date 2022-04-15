package com.mygdx.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Castle;

public class Archer extends Unit {
  public Archer(Castle owner) {
    super(5, 10, 10, 2,  owner.getSpawnPointX(), owner.getSpawnPointY());
    setTexture(new Texture("textures/archer-unit.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/archer-unit.png")));
    setSize(1,1);
  }

  public  String getClassName(){
    return "Archer";
  }
}
