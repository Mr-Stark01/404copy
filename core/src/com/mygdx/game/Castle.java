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

/** Represent the player and the castle both.
 *
 */
public class Castle implements Serializable {
    protected float health=500,gold=5000;

    protected ArrayList<Tower> towers;

    protected ArrayList<Knight> knights;

    private String player;
    private float spawnPointX,spawnPointY;

    /**
     * Creates a castle for the player and set's it's coordinates.
     * @param player
     */
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
        this.gold -= tower.getPrice();
        this.towers.add(tower);
        tower.setOwner(this);
    }

    /**
     * Buys a knight and gives it the route that it will have to follow.
     * @param pathFinder
     */
    public void buyKnight(PathFinder pathFinder){
        if (gold>50){
            gold-=50;
            knights.add(new Knight(spawnPointX,spawnPointY));
            pathFinder.findWay(knights.get(knights.size()-1));
        }
    }

    /**
     * Spawns the units that were bought in the buy phase.
     */
    public void spawnUnits(){
        for(Knight knight:knights){
            knight.spawn();
        }
    }

    /**
     * Draws anything that is connected to this specific player.
     * Such as towers and units.
     * @param spriteBatch
     */
    public void draw(SpriteBatch spriteBatch){
        for(Knight knight:knights){
            knight.draw(spriteBatch);
        }
    }


    /**
     *
     * @return All the units that are stored and not dead willbe returned by this.
     */
    public ArrayList<Knight> getKnights() {
        return knights;
    }


    /**For updating the specific instance of the castle with the castle give to it by the networkd handler.
     *
     * @param castle The castle given to it by the Network.
     */
    public void update(Castle castle){


        this.knights=castle.getKnights();
        this.gold=castle.getGold();
        if (this.health != castle.health /*&& thisIsServer*/ ){

        }
    }



}
