package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.units.Knight;

import java.io.Serializable;
import java.util.ArrayList;

public class Castle implements Serializable {
    protected float health=500,gold=5000;

    protected ArrayList<Knight> knights;
    public String id="asdasd";
    public Castle() {
        knights = new ArrayList<Knight>();

    }

    public float getGold(){
        return gold;
    }

    public void buyKnight(PathFinder pathFinder){
        if (gold>50){
            gold-=50;
            knights.add(new Knight());
            pathFinder.findWay(knights.get(knights.size()-1));
        }
    }
    public void spawnUnits(){
        for(Knight knight:knights){
            knight.spawn();
        }
    }

    public void setId(String id){
        this.id=id;
    }

    public void draw(SpriteBatch spriteBatch){
        for(Knight knight:knights){
            knight.draw(spriteBatch);
        }
    }

    public String getId(){
        return id;
    }


    public ArrayList<Knight> getKnights() {
        return knights;
    }


    /**For updating castle from the network Handler */
    public void update(Castle castle){

        this.id=castle.getId();
        this.knights=castle.getKnights();
        this.gold=castle.getGold();
        if (this.health != castle.health /*&& thisIsServer*/ ){

        }
    }



}
