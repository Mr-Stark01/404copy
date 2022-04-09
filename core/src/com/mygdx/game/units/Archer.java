package com.mygdx.game.units;

import com.mygdx.game.Castle;

public class Archer extends Unit {
  public Archer(Castle owner) {
    super(5, 10, 10, 2, owner, owner.getSpawnPointX(), owner.getSpawnPointY());
  }

  public  String getClassName(){
    return "Archer";
  }
}
