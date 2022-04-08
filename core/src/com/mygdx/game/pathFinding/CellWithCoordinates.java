package com.mygdx.game.pathFinding;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/** This is stupid. */
public class CellWithCoordinates {
  public TiledMapTileLayer.Cell cell;
  public int x;
  public int y;

  public CellWithCoordinates(TiledMapTileLayer.Cell cell, int i, int j) {
    x = i;
    y = j;
    this.cell = cell;
  }
}
