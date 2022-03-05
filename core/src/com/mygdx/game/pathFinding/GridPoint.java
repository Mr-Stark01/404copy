package com.mygdx.game.pathFinding;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class GridPoint {
    float x;
    float y;
    String name;

    /** Index used by the A* algorithm. Keep track of it so we don't have to recalculate it later. */
    int index;

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public GridPoint(CellWithCoordinates cell){
        this.x=cell.x;
        this.y=cell.y;
        if(cell.cell.getTile().getProperties().containsKey("Spawn")) {
            this.name = (String) cell.cell.getTile().getProperties().get("Spawn");
        }
        else{
            this.name="WhoCares";
        }
    }
    public void setIndex(int index){
        this.index = index;
    }
}
