package gameTest.tests;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.PathFinder;
import com.mygdx.game.units.Knight;
import com.mygdx.game.units.Unit;
import gameTest.GdxTestRunner;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
//TODO : Why in the everloving fuck is there two of these Fucking merge
// Anyway more tests yey
@RunWith(GdxTestRunner.class)
public class Uni_test {
    @Test
    public void unitTest(){ // I can't see this name causeing any problems ever good job me.
        Castle castle = new Castle("p1");
        Castle eCastle= mock(Castle.class);
        TmxMapLoader loader = new TmxMapLoader();
        TiledMap map = loader.load("maps/map_01.tmx");
        PathFinder pathFinder = new PathFinder(map,"Client");
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        castle.buyArcher(pathFinder);
        castle.spawnUnits();
        castle.setSpawn(pathFinder.getStart().getX(),pathFinder.getStart().getY());
        Unit unit = castle.getUnits().get(0);
        SpriteBatch sb=mock(SpriteBatch.class);
        float ogX=castle.getUnits().get(0).getX();
        float ogY=castle.getUnits().get(0).getY();
        for(int i=0;i<999;i++) {
            castle.draw(sb,eCastle);
        }
        assertNotEquals(ogX,castle.getUnits().get(0).getX());
        assertNotEquals(ogY,castle.getUnits().get(0).getY());
    }
}
