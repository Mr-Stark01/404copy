package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CannonTower extends Tower {
  public CannonTower(float spawnX, float spawnY) {
    super(30, 30, 30, 1, spawnX, spawnY);
    setTexture(new Texture("textures/canon-tower.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/canon-tower.png")));
    setSize(2,2);
  }
}
