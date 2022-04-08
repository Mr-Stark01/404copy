package com.mygdx.game.pathFinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;
/** This is stupid. */
public class Way implements Connection<GridPoint> {
  GridPoint fromGridPoint;
  GridPoint toGridPoint;
  float cost;

  public Way(GridPoint fromGridPoint, GridPoint toGridPoint) {
    this.fromGridPoint = fromGridPoint;
    this.toGridPoint = toGridPoint;
    cost = Vector2.dst(fromGridPoint.x, fromGridPoint.y, toGridPoint.x, toGridPoint.y);
  }

  @Override
  public float getCost() {
    return cost;
  }

  @Override
  public GridPoint getFromNode() {
    return fromGridPoint;
  }

  @Override
  public GridPoint getToNode() {
    return toGridPoint;
  }
}
