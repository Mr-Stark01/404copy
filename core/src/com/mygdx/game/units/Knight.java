package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Knight extends Sprite {

    private TiledMapTileLayer collisionLayer;
    public Knight(Sprite sprite) {
        super(sprite);
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    //bs currently
    public void update(float delta) {
        //gravity itself

        if (Gdx.input.isKeyPressed(Input.Keys.W) && !collisionLayer.getCell((int) getX()/32, (int) getY()/32+1).getTile().getProperties().containsKey("blocked")) {
            if (true) {


                setY(getY() + 10);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && !collisionLayer.getCell((int) getX()/32+1, (int) getY()/32).getTile().getProperties().containsKey("blocked")) {
            setX(getX() + 10);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && !collisionLayer.getCell((int) getX()/32, (int) getY()/32-1).getTile().getProperties().containsKey("blocked")) {
            setY(getY() - 10);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !collisionLayer.getCell((int) getX()/32-1, (int) getY()/32).getTile().getProperties().containsKey("blocked")) {
            setX(getX() - 10);
        }





    }

    public TiledMapTileLayer getCollisionLayer(){
        return collisionLayer;
    }

    public void SetCollisionLayer(TiledMapTileLayer collisionLayer){
        this.collisionLayer=collisionLayer;
    }

}
