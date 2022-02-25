package com.mygdx.game.pathFinding;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class GridPoint {
    float x;
    float y;
    String name;

    /** Index used by the A* algorithm. Keep track of it so we don't have to recalculate it later. */
    int index;

    public GridPoint(TiledMapTileLayer.Cell cell){
        this.x=cell.getTile().getOffsetX();
        this.y=cell.getTile().getOffsetY();
        if(cell.getTile().getProperties().containsKey("Spawn")) {
            this.name = (String) cell.getTile().getProperties().get("Spawn");
        }
        else{
            this.name="WhoCares";
        }
    }
    public void setIndex(int index){
        this.index = index;
    }
}
