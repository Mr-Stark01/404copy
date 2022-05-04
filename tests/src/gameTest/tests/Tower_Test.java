package gameTest.tests;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.towers.ArcherTower;
import com.mygdx.game.towers.CannonTower;
import com.mygdx.game.towers.FireTower;
import com.mygdx.game.towers.Tower;
import com.mygdx.game.units.Archer;
import com.mygdx.game.units.Mage;
import com.mygdx.game.units.Tank;
import com.mygdx.game.units.Unit;
import gameTest.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(GdxTestRunner.class)
public class Tower_Test {
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
    }
    @Test
    public void ArcherTowerTest(){
        Tower tower = new ArcherTower(10, 10);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertEquals(tower.getPrice(),10);
        assertEquals(tower.getHealth(),10,0);
        assertEquals(tower.getDamage(),10,0);
        assertEquals(tower.getRange(),4);
    }
    @Test
    public void CannonTowerTest(){
        Tower tower = new CannonTower(10, 10);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertEquals(tower.getPrice(),30);
        assertEquals(tower.getHealth(),30,0);
        assertEquals(tower.getDamage(),30,0);
        assertEquals(tower.getRange(),2);
    }
    @Test
    public void FireTowerTest(){
        Tower tower = new FireTower(10, 10);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertEquals(tower.getPrice(),20);
        assertEquals(tower.getHealth(),20,0);
        assertEquals(tower.getDamage(),20,0);
        assertEquals(tower.getRange(),3);
    }
    @Test
    public void FireTowerTestAttack() {
        Tower tower = new FireTower(10, 10);
        castle.setSpawn(10,10);
        PathFinder epathFinder = new PathFinder(map,"Server");
        Castle ecastle = new Castle("p2",buildRun);
        ecastle.setSpawn(10,10);
        ecastle.buyTank(epathFinder);
        ecastle.spawnUnits();
        ecastle.setBuildRound(false);
        for(int i=0;i<300;i++){
            ecastle.spawnOne();
        }
        ecastle.spawnOne();
        Tank tank = (Tank)ecastle.getSpawned().get(0);
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
    public void ArcherTowerTestAttack() {
        Tower tower = new ArcherTower(10, 10);
        castle.setSpawn(10,10);
        PathFinder epathFinder = new PathFinder(map,"Server");
        Castle ecastle = new Castle("p2",buildRun);
        ecastle.setSpawn(10,10);
        ecastle.buyTank(epathFinder);
        ecastle.spawnUnits();
        ecastle.setBuildRound(false);
        for(int i=0;i<300;i++){
            ecastle.spawnOne();
        }
        ecastle.spawnOne();
        Tank tank = (Tank)ecastle.getSpawned().get(0);
        tank.setX(11);
        tank.setY(11);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        for(int i=300;i>0;i-=10){
            tower.update(ecastle,1f);
            assertEquals(i,tank.getHealth(),0);
        }
    }
    @Test
    public void CannonTowerAttackTest() {
        Tower tower = new CannonTower(10, 10);
        castle.setSpawn(10,10);
        PathFinder epathFinder = new PathFinder(map,"Server");
        Castle ecastle = new Castle("p2",buildRun);
        ecastle.setSpawn(10,10);
        ecastle.buyTank(epathFinder);
        ecastle.spawnUnits();
        ecastle.setBuildRound(false);
        for(int i=0;i<300;i++){
            ecastle.spawnOne();
        }
        ecastle.spawnOne();
        Tank tank = (Tank)ecastle.getSpawned().get(0);
        tank.setX(11);
        tank.setY(11);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertTrue(tower.isSpawned());
        tower.update(ecastle,1f);
        assertEquals(300,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(270,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(240,tank.getHealth(),0);
        tower.update(ecastle,1f);
        assertEquals(210,tank.getHealth(),0);
    }
}
