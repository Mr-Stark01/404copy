package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.units.Knight;

import java.io.Serializable;
import java.util.ArrayList;

public class Castle implements Serializable {
    protected float health=500,gold=5000;
    protected MapLayers mainLayer;
    protected ArrayList<Knight> knights;
    protected String id="This is the big pc";
    public Castle() {
        knights = new ArrayList<Knight>();

    }

    public MapLayers getCastleLayer(){
        return mainLayer;
    }

    public void SetLayer(MapLayers mainLayer){
        this.mainLayer=mainLayer;
    }

    public void buyKnight(){
        if (gold>50){
            gold-=50;
            knights.add(new Knight());
            knights.get(knights.size()-1).SetCollisionLayer((TiledMapTileLayer)mainLayer.get(0));
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

    public String getId(){
        return id;
    }

    //To get the enemies view of the castle
    public void update(Castle castle){
        this.knights=castle.knights;
        this.gold=castle.gold;
        if (this.health != castle.health /*&& thisIsServer*/ ){

        }
    }



}
