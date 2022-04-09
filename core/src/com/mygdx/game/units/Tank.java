package com.mygdx.game.units;

import com.mygdx.game.Castle;

public class Tank extends Unit {
  public Tank(Castle owner) {
    super(5, 15, 30, 1, owner, owner.getSpawnPointX(), owner.getSpawnPointY());
  }

  public String getClassName(){
    return "Tank";
  }
}
