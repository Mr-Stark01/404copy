package com.mygdx.game.units;

import com.mygdx.game.Castle;

public class Mage extends Unit {
  public Mage(Castle owner) {
    super(10, 20, 10, 1, owner.getSpawnPointX(), owner.getSpawnPointY());
  }

  public String getClassName(){
    return "Mage";
  }
}
