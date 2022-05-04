package gameTest.tests;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.towers.CannonTower;
import com.mygdx.game.towers.FireTower;
import com.mygdx.game.towers.Tower;
import com.mygdx.game.units.Mage;
import gameTest.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class Damage_Test_CanonTower_Mage {
    Castle castle;
    TmxMapLoader loader;
    TiledMap map;
    PathFinder pathFinder;
    Tower tower;
    boolean buildRun;
    PathFinder epathFinder;
    Castle ecastle;
    Mage mage;
    @Before
    public void init(){
        buildRun=true;
        castle = new Castle("p1",buildRun);
        loader = new TmxMapLoader();
        map = loader.load("maps/map_01.tmx");
        pathFinder = new PathFinder(map,"Client");
        tower = new CannonTower(10, 10);
        castle.setSpawn(10,10);
        epathFinder = new PathFinder(map,"Server");
        ecastle= new Castle("p2",buildRun);
        ecastle.setSpawn(10,10);
        ecastle.buyMage(epathFinder);
        ecastle.spawnUnits();
        ecastle.setBuildRound(false);
        for(int i=0;i<300;i++){
            ecastle.spawnOne();
        }
        ecastle.spawnOne();
        mage = (Mage)ecastle.getSpawned().get(0);
    }
    @Test
    public void CanonTowerDamageTestRange0() {
        mage.setX(11);
        mage.setY(11);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(50,mage.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(20,mage.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(-10,mage.getHealth(),0);
    }
    @Test
    public void CanonTowerDamageTestRange1() {
        mage.setX(10);
        mage.setY(10);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(50,mage.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(20,mage.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(-10,mage.getHealth(),0);
    }
    @Test
    public void CanonTowerDamageTestRange2() {
        mage.setX(9);
        mage.setY(9);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(50,mage.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(20,mage.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(-10,mage.getHealth(),0);
    }
    @Test
    public void CanonTowerDamageTestRange4() {
        mage.setX(7);
        mage.setY(7);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(50,mage.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(50,mage.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(50,mage.getHealth(),0);
    }
    @Test
    public void CanonTowerDamageTestDeltaTiming() {
        mage.setX(9);
        mage.setY(9);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,0.125f); //Here it just finds it
        assertEquals(50,mage.getHealth(),0);
        tower.update(ecastle,0.125f);
        assertEquals(46.25,mage.getHealth(),0);
        tower.update(ecastle,0.125f);
        assertEquals(42.5,mage.getHealth(),0);
        tower.update(ecastle,0.125f);
        assertEquals(38.75,mage.getHealth(),0);
        tower.update(ecastle,0.125f);
        assertEquals(35,mage.getHealth(),0);
    }
    @Test
    public void CanonTowerDamageTestDeltaTiming2() {
        mage.setX(9);
        mage.setY(9);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,0.5f); //Here it just finds it
        assertEquals(50,mage.getHealth(),0);
        tower.update(ecastle,0.0123f);
        assertEquals(49.63100051879883,mage.getHealth(),0);
        tower.update(ecastle,0.120f);
        assertEquals(46.031002044677734,mage.getHealth(),0.1);
        tower.update(ecastle,0.7f);
        assertEquals(25.031002044677734,mage.getHealth(),0.2);
    }
}
