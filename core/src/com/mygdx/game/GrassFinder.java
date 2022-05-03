package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.pathFinding.CellWithCoordinates;

import java.util.ArrayList;

public class GrassFinder {
  ArrayList<CellWithCoordinates> grassList;
  /** This is stupid. */
  public GrassFinder(TiledMap map, String player) {
    TiledMapTileLayer tileyLayer = (TiledMapTileLayer) map.getLayers().get(0);
    grassList = new ArrayList<>();
    for (int i = 0; i < tileyLayer.getWidth(); i++) {
      for (int j = 0; j < tileyLayer.getHeight(); j++) {
        if ((grassList.get(i).cell.getTile().getProperties().containsKey("grass"))) {
          grassList.add(new CellWithCoordinates(tileyLayer.getCell(i, j), i, j));

        }
      }
    }
  }
}
