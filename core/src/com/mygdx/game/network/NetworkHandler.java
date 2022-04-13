package com.mygdx.game.network;

import com.mygdx.game.Castle;

public interface NetworkHandler {
  void start();

  void run();

  void setCastle(Castle ownCastle);

  boolean isNew();
  boolean castleArrived();

  Castle getEnemyCastle();
}
