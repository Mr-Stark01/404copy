package com.mygdx.game.network;

import com.mygdx.game.Castle;

public interface NetworkHandler {
    public void start();
    public void run();
    public void setCastle(Castle ownCastle);
    public Castle getEnemyCastle();
}
