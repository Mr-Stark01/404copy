package gameTest.tests;

import java.util.ArrayList;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.towers.ArcherTower;
import com.mygdx.game.towers.FireTower;
import com.mygdx.game.towers.Tower;
import gameTest.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(GdxTestRunner.class)
public class Castletest {
    boolean buildRun;
    @Before
    public void init(){
        buildRun=true;
        Castle castle= new Castle("P1",buildRun);
    }

    @Test
    public void enoughMoneyKnight(){
        PathFinder pathFinder=mock(PathFinder.class);
        Castle castle= new Castle("P1",buildRun);
        castle.setSpawn(10,10);
        int goldStart=Math.round(castle.getGold());
        for(int i=0;i<goldStart/castle.getArcherPrice();i++){
            castle.buyArcher(pathFinder);
        }
        assertEquals(castle.getGold(),0f,0);
        assertEquals(castle.getUnits().size(),goldStart/Math.round(castle.getArcherPrice()));
        castle.buyArcher(pathFinder);
        assertEquals(castle.getGold(),0f,0);
        assertEquals(castle.getUnits().size(),goldStart/Math.round(castle.getArcherPrice()));
    }
    @Test
    public void enoughMoneyTower(){
        Castle castle= new Castle("P1",buildRun);
        FireTower tower=new FireTower(0,0);

        int goldStart=Math.round(castle.getGold());
        for(int i=0;i<goldStart/tower.getPrice();i++){
            castle.buyTower(tower);
        }

        assertEquals(castle.getGold(),0f,0);
        assertEquals(castle.getTowers().size(),goldStart/Math.round(tower.getPrice()));
        castle.buyTower(tower);
        assertEquals(castle.getGold(),0f,0);
        assertEquals(castle.getTowers().size(),goldStart/Math.round(tower.getPrice()));
    }
}
