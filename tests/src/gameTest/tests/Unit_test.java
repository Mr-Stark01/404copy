package gameTest.tests;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.units.Knight;
import com.mygdx.game.units.Tank;
import com.mygdx.game.units.Unit;
import gameTest.GdxTestRunner;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;

@RunWith(GdxTestRunner.class)
public class Unit_test { // I can't see this name causing any problems ever good job me.
    Castle eCastle;
    Castle castle;
    TmxMapLoader loader;
    TiledMap map;
    PathFinder pathFinder;
    boolean buildRun;
    @Before
    public void init(){
        buildRun=true;
        castle = new Castle("p1",buildRun);
        loader = new TmxMapLoader();
        map = loader.load("maps/map_01.tmx");
        pathFinder = new PathFinder(map,"Client");
        eCastle=mock(Castle.class);
    }

  @Test
  public void ArcherTest() {
        castle.setSpawn(pathFinder.getStart().getX(), pathFinder.getStart().getY());
        castle.buyArcher(pathFinder);
        buildRun = false;
        castle.setBuildRound(buildRun);
        castle.spawnUnits();
        castle.setSpawn(pathFinder.getStart().getX(), pathFinder.getStart().getY());
        for (int i = 0; i < 300; i++) {
          castle.spawnOne();
        }
        SpriteBatch sb = mock(SpriteBatch.class);
        float ogX = castle.getSpawned().get(0).getX();
        float ogY = castle.getSpawned().get(0).getY();
        for (int i = 0; i < 9990; i++) {
          castle.draw(sb, eCastle, 0.05f);
        }
        assertNotEquals(ogX, castle.getSpawned().get(0).getX());
        assertNotEquals(ogY, castle.getSpawned().get(0).getY());
    }
    @Test
    public void MageTest(){
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        castle.buyMage(pathFinder);
        buildRun=false;
        castle.setBuildRound(buildRun);
        castle.spawnUnits();
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        for(int i=0;i<300;i++){
            castle.spawnOne();
        }
        SpriteBatch sb=mock(SpriteBatch.class);
        float ogX=castle.getSpawned().get(0).getX();
        float ogY=castle.getSpawned().get(0).getY();
        for(int i=0;i<9990;i++) {
            castle.draw(sb,eCastle,0.05f);
        }
        assertNotEquals(ogX,castle.getSpawned().get(0).getX());
        assertNotEquals(ogY,castle.getSpawned().get(0).getY());
    }
    @Test
    public void tankTest(){
        Castle castle = new Castle("p1",buildRun);
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load("maps/map_01.tmx");
        PathFinder pathFinder = new PathFinder(map,"Client");
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        castle.buyTank(pathFinder);
        buildRun=false;
        castle.setBuildRound(buildRun);
        castle.spawnUnits();
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        for(int i=0;i<300;i++){
            castle.spawnOne();
        }
        SpriteBatch sb=mock(SpriteBatch.class);
        float ogX=castle.getSpawned().get(0).getX();
        float ogY=castle.getSpawned().get(0).getY();
        for(int i=0;i<9990;i++) {
            castle.draw(sb,eCastle,0.05f);
        }
        assertNotEquals(ogX,castle.getSpawned().get(0).getX());
        assertNotEquals(ogY,castle.getSpawned().get(0).getY());
    }
    @Test
    public void pathFinderTest(){
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        castle.buyArcher(pathFinder);
        buildRun=false;
        castle.setBuildRound(buildRun);
        castle.spawnUnits();
        for(int i=0;i<300;i++){
            castle.spawnOne();
        }
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        Unit unit = castle.getSpawned().get(0);
        assertNotNull(unit);
        SpriteBatch sb=mock(SpriteBatch.class);
        for(int i=0;i<4000;i++) {
            castle.draw(sb,eCastle,0.05f);
        }
        assertEquals(pathFinder.getEnd().getX(),castle.getSpawned().get(0).getX(),0);
        assertEquals(pathFinder.getEnd().getY(),castle.getSpawned().get(0).getY(),0);
    }
}
