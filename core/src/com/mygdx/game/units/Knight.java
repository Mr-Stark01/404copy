package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Knight extends Sprite {
    private final Vector2 velocity = new Vector2();


    public Knight(Sprite sprite){
        super(sprite);
    }

    public void draw(SpriteBatch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }
    //bs currently
    public void update(float delta) {
        //gravity itself

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            setX(getX()+10);
        }



        setY(getY()+velocity.x *delta);
    }
}
