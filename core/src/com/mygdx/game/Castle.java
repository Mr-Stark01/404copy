package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.towers.Tower;
import com.mygdx.game.units.Knight;

import java.io.Serializable;
import java.util.ArrayList;

public class Castle implements Serializable {
    protected float health=500,gold=5000;

    protected ArrayList<Tower> towers;

    protected ArrayList<Knight> knights;

    private String player;
    private float spawnPointX,spawnPointY;
    public Castle(String player) {
        this.player=player;
        knights = new ArrayList<Knight>();
        if(player=="Server"){
            spawnPointX=19;
            spawnPointY=45;
        }
        else{
            spawnPointX=282;
            spawnPointY=46;
        }

    }

    public float getGold(){
        return gold;
    }

    public void buyTower(Tower tower){
        this.gold -= tower.getPrice();
        this.towers.add(tower);
        tower.setOwner(this);
    }

    public void buyKnight(PathFinder pathFinder){
        if (gold>50){
            gold-=50;
            knights.add(new Knight(spawnPointX,spawnPointY));
            pathFinder.findWay(knights.get(knights.size()-1));
        }
    }
    public void spawnUnits(){
        for(Knight knight:knights){
            knight.spawn();
        }
    }


    public void draw(SpriteBatch spriteBatch){
        for(Knight knight:knights){
            knight.draw(spriteBatch);
        }
    }




    public ArrayList<Knight> getKnights() {
        return knights;
    }


    /**For updating castle from the network Handler */
    public void update(Castle castle){


        this.knights=castle.getKnights();
        this.gold=castle.getGold();
        if (this.health != castle.health /*&& thisIsServer*/ ){

        }
    }



}
