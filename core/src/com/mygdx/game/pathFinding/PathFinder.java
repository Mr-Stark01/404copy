package com.mygdx.game.pathFinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.Pair;
import com.mygdx.game.units.Unit;

import java.util.ArrayList;

public class PathFinder {
  GridPointGraph gridPointGraph = new GridPointGraph();
  GraphPath<GridPoint> gridPointsPath;
  ArrayList<CellWithCoordinates> cellList;
  ArrayList<GridPoint> gridPoints = new ArrayList<>();
  GridPoint start = null;
  GridPoint end = null;
  String player;
  /** This is stupid. */
  public PathFinder(TiledMap map, String player) {
    this.player = player;
    TiledMapTileLayer tileyLayer = (TiledMapTileLayer) map.getLayers().get(0);
    cellList = new ArrayList<>();
    for (int i = 0; i < tileyLayer.getWidth(); i++) {
      for (int j = 0; j < tileyLayer.getHeight(); j++) {
        cellList.add(new CellWithCoordinates(tileyLayer.getCell(i, j), i, j));
      }
    }
    for (CellWithCoordinates cell : cellList) {
      GridPoint tmp = new GridPoint(cell);
      gridPoints.add(tmp);
      gridPointGraph.addSpawnPoint(tmp);
    }
    for (int i = 0; i < cellList.size(); i++) {
      if (!(cellList.get(i).cell.getTile().getProperties().containsKey("blocked") || cellList.get(i).cell.getTile().getProperties().containsKey("grass"))) {
        if (!(i + 1 % tileyLayer.getHeight() == 0)) {
          if (i + 1 < cellList.size()) {
            gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i + 1));
          }
        }
        if (!(i % tileyLayer.getHeight() == 0)) {
          if (i - 1 > 0) {
            gridPointGraph.connectSpawnPoint(gridPoints.get(i), gridPoints.get(i - 1));
          }
        }
        if (i + tileyLayer.getHeight() > 0 && gridPoints.size() > i + tileyLayer.getHeight()) {
          gridPointGraph.connectSpawnPoint(
              gridPoints.get(i), gridPoints.get(i + tileyLayer.getHeight()));
        }
        if (i - tileyLayer.getHeight() > 0 && gridPoints.size() > i + tileyLayer.getHeight()) {
          gridPointGraph.connectSpawnPoint(
              gridPoints.get(i), gridPoints.get(i - tileyLayer.getHeight()));
        }
      }
      // Good god why did I do this
      // We need to find another way because hard coding is not an option.
      if (player.equals("Client")) {
        if ((cellList.get(i).cell.getTile().getProperties().get("CellPath") != null)
            && (cellList.get(i).cell.getTile().getProperties().get("CellPath").equals("Client"))) {
          start = gridPoints.get(i);
        }
        if ((cellList.get(i).cell.getTile().getProperties().get("CellPath") != null)
            && (cellList
                .get(i)
                .cell
                .getTile()
                .getProperties()
                .get("CellPath")
                .equals("RallyPointServer"))) {
          end = gridPoints.get(i);
        }
      } else {
        if ((cellList.get(i).cell.getTile().getProperties().get("CellPath") != null)
            && (cellList.get(i).cell.getTile().getProperties().get("CellPath").equals("Server"))) {
          start = gridPoints.get(i);
        }
        if ((cellList.get(i).cell.getTile().getProperties().get("CellPath") != null)
            && (cellList
                .get(i)
                .cell
                .getTile()
                .getProperties()
                .get("CellPath")
                .equals("RallyPointClient"))) {
          end = gridPoints.get(i);
        }
      }
    }
  }

  // Kell méret pályának és https://happycoding.io/tutorials/libgdx/pathfinding
  public GridPoint getStart() {
    return start;
  }

  public GridPoint getEnd() {
    return end;
  }

  // This is another warcrime by me :D
  public void deleteBlocked(ArrayList<Pair>blocked){
    int[] todelete = new int[30];
    int i=0,j=0;
    for (GridPoint gridPoint : gridPoints) {
      for(Pair block: blocked){
        if (gridPoint.x == block.getX() && block.getY()== gridPoint.y) {
          todelete[j]=i;
          j++;
        }
      }
      i++;
    }
    for(int tmp:todelete){
      gridPoints.remove(tmp);
    }

  }

  public void findWay(Unit unit) {
    for (GridPoint gridPoint : gridPoints) {
      if (gridPoint.x == unit.getX() && unit.getY() == gridPoint.y) {
        start = gridPoint;
      }
    }
    unit.setPath(gridPointGraph.findPath(start, end), start);
  }
}
