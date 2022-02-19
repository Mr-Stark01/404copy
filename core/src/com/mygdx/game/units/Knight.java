package com.mygdx.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


public class Knight extends Sprite{
    private int attackPower=10;
    private TiledMapTileLayer collisionLayer;
    protected boolean spawned=false;
    public Knight() {
        super(new Sprite(new Texture("textures/placeholder.png")));
        setX(64);
        setY(500);
    }

    public void draw(SpriteBatch spriteBatch) {

        if(spawned) {
            update();
            super.draw(spriteBatch);
            System.out.println("drawened");
        }

    }

    public void spawn(){
        spawned=true;
    }

    public void attack(){

    }

    //bs currently
    public void update() {
        if (true) {///kurav élet
            setX(getX() + 1);
            System.out.println("asd");
        }

        // Manual Movement
        /*
        if (Gdx.input.isKeyPressed(Input.Keys.W) && !collisionLayer.getCell((int) getX()/32+1, (int) getY()/32).getTile().getProperties().containsKey("blocked")) {
            if (getY()+65<=3200) {
                setY(getY() + 32);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && !collisionLayer.getCell((int) getX()/32, (int) getY()/32+1).getTile().getProperties().containsKey("blocked")) {

            if (getX()+65<=9600) {
                setX(getX() + 32);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)&&!collisionLayer.getCell((int) getX()/32, (int) (getY()/32)-1).getTile().getProperties().containsKey("blocked")) {
            if (getY()-33>=0) {
                setY(getY() - 32);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !collisionLayer.getCell((int) getX()/32-1, (int) getY()/32).getTile().getProperties().containsKey("blocked")) {
            if (getX()-33>=0) {
                setX(getX() - 32);
            }
        }

         */
    }


    public TiledMapTileLayer getCollisionLayer(){
        return collisionLayer;
    }

    public void SetCollisionLayer(TiledMapTileLayer collisionLayer){
        this.collisionLayer=collisionLayer;
    }
    /* idk
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.S:
                System.out.println("here");
                if (!collisionLayer.getCell((int) getX()/32, (int) getY()/32-1).getTile().getProperties().containsKey("blocked")) {
                    moveDown();
                }
                break;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

*/
}
