package com.mygdx.game.pathFinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

/** This is stupid. */
public class GridPointGraph implements IndexedGraph<GridPoint> {
  GridPointHeuristic gridPointHeuristic = new GridPointHeuristic();
  Array<GridPoint> gridPoints = new Array<>();
  Array<Way> ways = new Array<>();
  ObjectMap<GridPoint, Array<Connection<GridPoint>>> waysMap = new ObjectMap<>();
  private int lastNodeIndex = 0;

  public void addSpawnPoint(GridPoint gridPoint) {
    gridPoint.index = lastNodeIndex;
    lastNodeIndex++;
    gridPoints.add(gridPoint);
  }

  public void connectSpawnPoint(GridPoint fromGridPoint, GridPoint toGridPoint) {
    Way way = new Way(fromGridPoint, toGridPoint);
    if (!waysMap.containsKey(fromGridPoint)) {
      waysMap.put(fromGridPoint, new Array<Connection<GridPoint>>());
    }
    waysMap.get(fromGridPoint).add(way);
    ways.add(way);
  }

  public GraphPath<GridPoint> findPath(GridPoint startGridPoint, GridPoint goalGridPoint) {
    GraphPath<GridPoint> spawnPointPath = new DefaultGraphPath<>();
    new IndexedAStarPathFinder<>(this)
        .searchNodePath(startGridPoint, goalGridPoint, gridPointHeuristic, spawnPointPath);
    return spawnPointPath;
  }

  @Override
  public int getIndex(GridPoint node) {
    return node.index;
  }

  @Override
  public int getNodeCount() {
    return lastNodeIndex;
  }

  @Override
  public Array<Connection<GridPoint>> getConnections(GridPoint fromNode) {
    if (waysMap.containsKey(fromNode)) {
      return waysMap.get(fromNode);
    }
    return new Array<>(0);
  }
}
