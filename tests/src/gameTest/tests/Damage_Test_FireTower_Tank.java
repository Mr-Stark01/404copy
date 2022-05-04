package gameTest.tests;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.towers.FireTower;
import com.mygdx.game.towers.Tower;
import com.mygdx.game.units.Tank;
import gameTest.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class Damage_Test_FireTower_Tank {
    Castle castle;
    TmxMapLoader loader;
    TiledMap map;
    PathFinder pathFinder;
    Tower tower;
    boolean buildRun;
    PathFinder epathFinder;
    Castle ecastle;
    Tank tank;
    @Before
    public void init(){
        buildRun=true;
        castle = new Castle("p1",buildRun);
        loader = new TmxMapLoader();
        map = loader.load("maps/map_01.tmx");
        pathFinder = new PathFinder(map,"Client");
        tower = new FireTower(10, 10);
        castle.setSpawn(10,10);
        epathFinder = new PathFinder(map,"Server");
        ecastle= new Castle("p2",buildRun);
        ecastle.setSpawn(10,10);
        ecastle.buyTank(epathFinder);
        ecastle.spawnUnits();
        ecastle.setBuildRound(false);
        for(int i=0;i<300;i++){
            ecastle.spawnOne();
        }
        ecastle.spawnOne();
        tank = (Tank)ecastle.getSpawned().get(0);
    }
    @Test
    public void FireTowerDamageTestRange0() {
        tank.setX(11);
        tank.setY(11);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(300,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(280,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(260,tank.getHealth(),0);
    }
    @Test
    public void FireTowerDamageTestRange1() {
        tank.setX(10);
        tank.setY(10);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(300,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(280,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(260,tank.getHealth(),0);
    }
    @Test
    public void FireTowerDamageTestRange2() {
        tank.setX(9);
        tank.setY(9);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(300,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(280,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(260,tank.getHealth(),0);
    }
    @Test
    public void FireTowerDamageTestRange3() {
        tank.setX(8);
        tank.setY(8);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(300,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(280,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(260,tank.getHealth(),0);
    }
    @Test
    public void FireTowerDamageTestRange4() {
        tank.setX(7);
        tank.setY(7);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(300,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(280,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(260,tank.getHealth(),0);
    }
    @Test
    public void FireTowerDamageTestDeltaTiming() {
        tank.setX(9);
        tank.setY(9);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,0.5f); //Here it just finds it
        assertEquals(300,tank.getHealth(),0);
        tower.update(ecastle,0.5f);
        assertEquals(290,tank.getHealth(),0);
        tower.update(ecastle,0.5f);
        assertEquals(280,tank.getHealth(),0);
        tower.update(ecastle,0.5f);
        assertEquals(270,tank.getHealth(),0);
    }
    @Test
    public void FireTowerDamageTestDeltaTiming2() {
        tank.setX(9);
        tank.setY(9);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,0.5f); //Here it just finds it
        assertEquals(300,tank.getHealth(),0);
        tower.update(ecastle,0.0123f);
        assertEquals(299.7539978027344,tank.getHealth(),0);
        tower.update(ecastle,0.120f);
        assertEquals(297.35400390625,tank.getHealth(),0.1);
        tower.update(ecastle,0.7f);
        assertEquals(283.35400390625,tank.getHealth(),0.2);
    }
}
