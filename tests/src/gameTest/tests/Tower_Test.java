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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

@RunWith(GdxTestRunner.class)
public class Tower_Test {
    Castle castle;
    TmxMapLoader loader;
    TiledMap map;
    PathFinder pathFinder;
    @Before
    public void init(){
        castle = new Castle("p1",true);
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
        assertEquals(tower.getHealth(),10);
        assertEquals(tower.getDamage(),1);
        assertEquals(tower.getRange(),3);
    }
    @Test
    public void CannonTowerTest(){
        Tower tower = new CannonTower(10, 10);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertEquals(tower.getPrice(),30);
        assertEquals(tower.getHealth(),30);
        assertEquals(tower.getDamage(),30);
        assertEquals(tower.getRange(),1);
    }
    @Test
    public void FireTowerTest(){
        Tower tower = new FireTower(10, 10);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertEquals(tower.getPrice(),20);
        assertEquals(tower.getHealth(),20);
        assertEquals(tower.getDamage(),20);
        assertEquals(tower.getRange(),2);
    }
    @Test
    public void FireTowerTestAttack() {
        Tower tower = new FireTower(10, 10);
        Castle ecastle = new Castle("p2",true);
        castle.setSpawn(10,10);
        ecastle.setSpawn(10,10);
        TmxMapLoader eloader = new TmxMapLoader();
        TiledMap emap = loader.load("maps/map_01.tmx");
        PathFinder epathFinder = new PathFinder(map,"Server");
        ecastle.buyArcher(epathFinder);
        Archer archer = (Archer)ecastle.getUnits().get(0);
        archer.setX(11);
        archer.setY(11);
        castle.buyTower(tower);
        castle.spawnTowers();
        assertEquals(10,archer.getHealth());
        tower.update(ecastle);
        assertEquals(10,archer.getHealth());
        tower.update(ecastle);
        assertEquals(-10,archer.getHealth());
        tower.update(ecastle);
        assertEquals(-10,archer.getHealth());
    }
    @Test
    public void ArcherTowerTestAttack() {
        Tower tower = new ArcherTower(10, 10);
        Castle ecastle = new Castle("p2",true);
        castle.setSpawn(10,10);
        ecastle.setSpawn(10,10);
        TmxMapLoader eloader = new TmxMapLoader();
        TiledMap emap = loader.load("maps/map_01.tmx");
        PathFinder epathFinder = new PathFinder(map,"Server");
        ecastle.buyMage(epathFinder);
        Mage mage = (Mage)ecastle.getUnits().get(0);
        mage.setX(11);
        mage.setY(11);
        castle.buyTower(tower);
        castle.spawnTowers();
        for(int i=10;i>0;i--){
            tower.update(ecastle);
            assertEquals(i,mage.getHealth());
        }
    }
    @Test
    public void TowerAttackTest() {
        Tower tower = new CannonTower(10, 10);
        Castle ecastle = new Castle("p2",true);
        castle.setSpawn(10,10);
        ecastle.setSpawn(10,10);
        TmxMapLoader eloader = new TmxMapLoader();
        TiledMap emap = loader.load("maps/map_01.tmx");
        PathFinder epathFinder = new PathFinder(map,"Server");
        ecastle.buyTank(epathFinder);
        Tank tank = (Tank)ecastle.getUnits().get(0);
        tank.setX(11);
        tank.setY(11);
        castle.buyTower(tower);
        castle.spawnTowers();
        tower.update(ecastle);
        assertEquals(30,tank.getHealth());
        tower.update(ecastle);
        assertEquals(0,tank.getHealth());
        tower.update(ecastle);
        assertEquals(0,tank.getHealth());
        tower.update(ecastle);
        assertEquals(0,tank.getHealth());
    }
}
