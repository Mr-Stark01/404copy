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
    Castle castle;
    TmxMapLoader loader;
    TiledMap map;
    PathFinder pathFinder;
    @Before
    public void init(){
        castle = new Castle("p1");
        loader = new TmxMapLoader();
        map = loader.load("maps/map_01.tmx");
        pathFinder = new PathFinder(map,"Client");
    }
    @Test
    public void ArcherTest(){
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        castle.buyArcher(pathFinder);
        castle.spawnUnits();
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        Unit unit = castle.getUnits().get(0);
        SpriteBatch sb=mock(SpriteBatch.class);
        float ogX=castle.getUnits().get(0).getX();
        float ogY=castle.getUnits().get(0).getY();
        for(int i=0;i<999;i++) {
            castle.draw(sb);
        }
        assertNotEquals(ogX,castle.getUnits().get(0).getX());
        assertNotEquals(ogY,castle.getUnits().get(0).getY());
    }
    @Test
    public void MageTest(){
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        castle.buyArcher(pathFinder);
        castle.spawnUnits();
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        Unit unit = castle.getUnits().get(0);
        SpriteBatch sb=mock(SpriteBatch.class);
        float ogX=castle.getUnits().get(0).getX();
        float ogY=castle.getUnits().get(0).getY();
        for(int i=0;i<999;i++) {
            castle.draw(sb);
        }
        assertNotEquals(ogX,castle.getUnits().get(0).getX());
        assertNotEquals(ogY,castle.getUnits().get(0).getY());
    }
    @Test
    public void tankTest(){
        Castle castle = new Castle("p1");
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load("maps/map_01.tmx");
        PathFinder pathFinder = new PathFinder(map,"Client");
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        castle.buyTank(pathFinder);
        castle.spawnUnits();
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        Unit tank = castle.getUnits().get(0);
        SpriteBatch sb=mock(SpriteBatch.class);
        float ogX=castle.getUnits().get(0).getX();
        float ogY=castle.getUnits().get(0).getY();
        for(int i=0;i<999;i++) {
            castle.draw(sb);
        }
        assertNotEquals(ogX,castle.getUnits().get(0).getX());
        assertNotEquals(ogY,castle.getUnits().get(0).getY());
    }
    @Test
    public void pathFinderTest(){ // I can't see this name causeing any problems ever good job me.
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        castle.buyArcher(pathFinder);
        castle.spawnUnits();
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        Unit unit = castle.getUnits().get(0);
        SpriteBatch sb=mock(SpriteBatch.class);
        for(int i=0;i<4000;i++) {
            castle.draw(sb);
        }
        assertEquals(pathFinder.getEnd().getX(),castle.getUnits().get(0).getX(),0);
        assertEquals(pathFinder.getEnd().getY(),castle.getUnits().get(0).getY(),0);
    }
}
