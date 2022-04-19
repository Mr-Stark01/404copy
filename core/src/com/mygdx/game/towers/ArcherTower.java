package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Castle;

public class ArcherTower extends Tower {

  public ArcherTower( float spawnX, float spawnY) {
    super(1, 10, 10, 3, spawnX, spawnY);
    setTexture(new Texture("textures/archer-tower.png"));
  }
  @Override
  public void reinitialize(){
    set(new Sprite(new Texture("textures/archer-tower.png")));
    setSize(2,2);
  }
}
