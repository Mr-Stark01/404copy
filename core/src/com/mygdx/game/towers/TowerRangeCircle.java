package com.mygdx.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerRangeCircle extends Sprite {
  public TowerRangeCircle(int range) {
    super(new Sprite(new Texture("textures/circle.png")));
    setX(10);
    setY(10);
    setSize(range*2, range*2);
    }
    public void draw(SpriteBatch spriteBatch){
      super.draw(spriteBatch);
    }
}
