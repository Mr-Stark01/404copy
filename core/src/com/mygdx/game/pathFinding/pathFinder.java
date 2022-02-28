package com.mygdx.game.pathFinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

public class pathFinder {


    GridPointGraph gridPointGraph;
    GraphPath<GridPoint> gridPointsPath;
    ArrayList<TiledMapTileLayer.Cell> cellList;
// Kell méret pályának és https://happycoding.io/tutorials/libgdx/pathfinding
    public pathFinder(ArrayList<TiledMapTileLayer.Cell> cellList){
        for(TiledMapTileLayer.Cell cell : cellList){
            gridPointGraph.addSpawnPoint(new GridPoint(cell));
        }
    }
}
