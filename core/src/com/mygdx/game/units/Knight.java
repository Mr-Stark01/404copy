package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Knight extends Sprite {
    private final Vector2 velocity = new Vector2();
    // this is a 2dgame there is no proper gravity dingus
    private final float speed = 60 *2 ,gravity = 60* 1.8f;

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
        velocity.y -=gravity * delta;

        if (velocity.y> speed){
            velocity.y=speed;
        }
        else if(velocity.y<speed){
            velocity.y=-speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            velocity.x=speed;
        }
        else{
            velocity.x=-speed;
        }

        setX(getX()+velocity.x *delta);
        setY(getY()+velocity.x *delta);
    }
}
