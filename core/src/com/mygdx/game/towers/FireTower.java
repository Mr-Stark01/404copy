package com.mygdx.game.towers;

import com.mygdx.game.Castle;

public class FireTower extends Tower {
  public FireTower(Castle owner, float spawnX, float spawnY) {
    super(20, 20, 20, 2, owner, spawnX, spawnY);
  }
}
