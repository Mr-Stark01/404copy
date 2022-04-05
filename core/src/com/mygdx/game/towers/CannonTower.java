package com.mygdx.game.towers;

import com.mygdx.game.Castle;

public class CannonTower extends Tower {
    public CannonTower(Castle owner, float spawnX, float spawnY) {
        super(30, 30, 30, 1, owner, spawnX, spawnY);
    }
}
