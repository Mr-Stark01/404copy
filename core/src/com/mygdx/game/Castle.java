package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.towers.Tower;
import com.mygdx.game.units.Archer;
import com.mygdx.game.units.Knight;
import com.mygdx.game.units.Unit;

import java.io.Serializable;
import java.util.ArrayList;

/** Represent the player and the castle both.
 *
 */
public class Castle implements Serializable {
    protected float health=500,gold=5000;
    protected int archerPrice=50;

    protected ArrayList<Tower> towers;

    protected ArrayList<Unit> units;

    private String player;
    private float spawnPointX,spawnPointY;

    public int getArcherPrice(){
        return archerPrice;
    }
    /**
     * Creates a castle for the player and set's it's coordinates.
     * @param player
     */
    public Castle(String player) {
        this.player=player;
        towers = new ArrayList<Tower>();
        units = new ArrayList<Unit>();
    }

    public void setSpawn(float x,float y){
        spawnPointX=x;
        spawnPointY=y;

    }

    /**
     *
     * @return The ammount of gold.
     */
    public float getGold(){
        return gold;
    }

    /**
     * Buys a new tower that can be deployed.
     * Lower the amount of money the player has.
     * @param tower
     */
    public void buyTower(Tower tower){
        if(tower.getPrice()<=gold){
            gold -= tower.getPrice();
            towers.add(tower);
        }
    }

    public void spawnTowers(){
        for (Tower tower:towers){
            tower.spawn();
        }
    }

    /**
     * Buys a knight and gives it the route that it will have to follow.
     * @param pathFinder
     */
    public void buyArcher(PathFinder pathFinder){
        if (gold>=archerPrice){
            gold-=archerPrice;
            units.add(new Archer(this));
            pathFinder.findWay(units.get(units.size()-1));
        }
    }
    /**
     * Spawns the units that were bought in the buy phase.
     */
    public void spawnUnits(){
        for(Unit unit:units){
            unit.spawn();
        }
    }

    /**
     * Draws anything that is connected to this specific player.
     * Such as towers and units.
     * @param spriteBatch
     */
    public void draw(SpriteBatch spriteBatch){
        for(Unit unit:units){
            unit.draw(spriteBatch);
        }
        for (Tower tower:towers){
            tower.draw(spriteBatch, this); //enemy lesz this helyett
        }
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }


    /**For updating the specific instance of the castle with the castle give to it by the networkd handler.
     *
     * @param castle The castle given to it by the Network.
     */
    public void update(Castle castle){


        this.units=castle.getUnits();
        this.towers=castle.getTowers();
        this.gold=castle.getGold();
        if (this.health != castle.health /*&& thisIsServer*/ ){

        }
    }

    public float getSpawnPointX() {
        return this.spawnPointX;
    }

    public float getSpawnPointY() {
        return this.spawnPointY;
    }
}
