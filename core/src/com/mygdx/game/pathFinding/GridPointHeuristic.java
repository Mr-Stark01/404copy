package com.mygdx.game.pathFinding;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

/** This is stupid. */
public class GridPointHeuristic implements Heuristic<GridPoint> {

  @Override
  public float estimate(GridPoint node, GridPoint endNode) {
    return Vector2.dst(node.x, node.y, endNode.x, endNode.y);
  }
}
