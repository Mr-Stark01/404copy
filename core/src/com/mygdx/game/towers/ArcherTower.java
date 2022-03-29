package com.mygdx.game.towers;

import com.mygdx.game.Castle;

public class ArcherTower extends Tower {

    public ArcherTower(Castle owner, float spawnX, float spawnY) {
        super(1, 10, 10, 3, owner, spawnX, spawnY);
    }
}
