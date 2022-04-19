package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Castle;

public class FireTower extends Tower {
  public FireTower( float spawnX, float spawnY) {
    super(20, 20, 20, 2, spawnX, spawnY);
    setTexture(new Texture("textures/mage-tower.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/mage-tower.png")));
    setSize(2,2);
  }

}
