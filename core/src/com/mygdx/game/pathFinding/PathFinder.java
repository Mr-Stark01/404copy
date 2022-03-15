package com.mygdx.game.pathFinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.units.Knight;

import java.util.ArrayList;

public class PathFinder {


    GridPointGraph gridPointGraph=new GridPointGraph();
    GraphPath<GridPoint> gridPointsPath;
    ArrayList<CellWithCoordinates> cellList;
    ArrayList<GridPoint> gridPoints=new ArrayList<>();
    GridPoint start = null;
    GridPoint end = null;

// Kell méret pályának és https://happycoding.io/tutorials/libgdx/pathfinding
    public PathFinder(TiledMap map){
        TiledMapTileLayer tileyLayer=(TiledMapTileLayer) map.getLayers().get(0);
        cellList=new ArrayList<>();
        for(int i=0;i<tileyLayer.getWidth();i++){
            for(int j=0;j<tileyLayer.getHeight();j++){
                cellList.add(new CellWithCoordinates(tileyLayer.getCell(i,j),i,j));
            }
        }


        for(CellWithCoordinates cell : cellList){

            GridPoint tmp=new GridPoint(cell);

            gridPoints.add(tmp);

            gridPointGraph.addSpawnPoint(tmp);

        }



        for(int i=0;i<cellList.size();i++){
            if(!cellList.get(i).cell.getTile().getProperties().containsKey("blocked")) {

                if(!(i+1%tileyLayer.getHeight()==0)){
                    if (i + 1 < cellList.size()) {
                        gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i + 1));
                    }
                }

                if(!(i%tileyLayer.getHeight()==0)) {
                    if (i - 1 > 0) {
                        gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i - 1));
                    }
                }


                if (i + tileyLayer.getHeight() > 0 && gridPoints.size() > i + tileyLayer.getHeight()) {
                    gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i + tileyLayer.getHeight()));
                }

                if (i - tileyLayer.getHeight() > 0 && gridPoints.size() > i + tileyLayer.getHeight()) {
                    gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i - tileyLayer.getHeight()));
                }

            }
        // Good god why did I do this
            // We need to find another way because hard coding is not an option.
            if((cellList.get(i).cell.getTile().getProperties().get("CellPath")!=null)&&(cellList.get(i).cell.getTile().getProperties().get("CellPath").equals("Client"))){
                    start=gridPoints.get(i);

                    System.out.println("Client ok now for real though");
            }
            if((cellList.get(i).cell.getTile().getProperties().get("CellPath")!=null)&&(cellList.get(i).cell.getTile().getProperties().get("CellPath").equals("RallyPointServer"))){
                    end=gridPoints.get(i);

                System.out.println("Server ok now for real though");
            }
        }


    }
    public void findWay(Knight knight){




        for(GridPoint gridPoint:gridPoints){
            if( gridPoint.x == knight.getX() && knight.getY() == gridPoint.y){

                start = gridPoint;

            }

        }



        knight.setPath(gridPointGraph.findPath(start,end),start);
    }
}
