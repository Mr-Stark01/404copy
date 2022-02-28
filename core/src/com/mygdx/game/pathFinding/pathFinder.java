package com.mygdx.game.pathFinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public class pathFinder {


    GridPointGraph gridPointGraph=new GridPointGraph();
    GraphPath<GridPoint> gridPointsPath;
    ArrayList<TiledMapTileLayer.Cell> cellList;
    ArrayList<GridPoint> gridPoints=new ArrayList<>();

// Kell méret pályának és https://happycoding.io/tutorials/libgdx/pathfinding
    public pathFinder(TiledMap map){
        TiledMapTileLayer tileyLayer=(TiledMapTileLayer) map.getLayers().get(0);
        cellList=new ArrayList<>();
        for(int i=0;i<tileyLayer.getWidth();i++){
            for(int j=0;j<tileyLayer.getHeight();j++){
                cellList.add(tileyLayer.getCell(i,j));
            }
        }


        for(TiledMapTileLayer.Cell cell : cellList){

            GridPoint tmp=new GridPoint(cell);

            gridPoints.add(tmp);

            gridPointGraph.addSpawnPoint(tmp);

        }

        GridPoint start = null;
        GridPoint end = null;

        for(int i=0;i<cellList.size();i++){
            if(!cellList.get(i).getTile().getProperties().containsKey("Blocked")) {
                if(i+1<cellList.size()) {
                    gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i + 1));
                }
                if(i-1>=0) {
                    gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i - 1));
                }
                if (i + tileyLayer.getHeight() >= 0 && gridPoints.size() > i + tileyLayer.getHeight()) {
                    gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i + tileyLayer.getHeight()));
                }
                if (i - tileyLayer.getHeight() >= 0 && gridPoints.size() > i + tileyLayer.getHeight()) {
                    gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i - tileyLayer.getHeight()));
                }
            }

            if((cellList.get(i).getTile().getProperties().get("Spawn")!=null)&&(cellList.get(i).getTile().getProperties().get("Spawn").equals("Client"))){
                    start=gridPoints.get(i);

                    System.out.println("Client ok now for real though");
            }
            if((cellList.get(i).getTile().getProperties().get("Spawn")!=null)&&(cellList.get(i).getTile().getProperties().get("Spawn").equals("Server"))){
                    end=gridPoints.get(i);

                System.out.println("Server ok now for real though");
            }
        }

        GraphPath<GridPoint> idk=gridPointGraph.findPath(start,end);
    }
}
