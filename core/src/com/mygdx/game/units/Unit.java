package com.mygdx.game.units;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.mygdx.game.Castle;
import com.mygdx.game.pathFinding.GridPoint;

import java.io.Serializable;

public abstract class Unit extends Sprite implements Serializable {
    protected int damage;
    protected int price;
    protected int health;
    protected int range;
    protected Castle owner;

    float speed = 1/32f;
    private float deltaX=0;
    private float deltaY=0;

    GridPoint previousPoint;
    private float spawnPointX,spawnPointY;

    GraphPath<GridPoint> path;
    Queue<GridPoint> pathQueue = new Queue<>();

    protected boolean spawned=false;

    public Unit(int damage, int price, int health, int range, Castle owner,float spawnPointX,float spawnPointY) {
        super(new Sprite(new Texture("textures/placeholder.png")));
        this.spawnPointX=spawnPointX;
        this.spawnPointY=spawnPointY;
        setX(spawnPointX);
        setY(spawnPointY);
        setSize(1,1);

        this.damage=damage;
        this.price=price;
        this.health=health;
        this.range=range;
        this.owner=owner;

    }

    public float getX(){
        return super.getX();
    }
    public float getY(){
        return super.getY();
    }

    public void setPath(GraphPath<GridPoint> path,GridPoint start){

        previousPoint=start;
        setX(start.getX());
        setY(start.getY());
        this.path=path;
        for (int i = 1; i < path.getCount(); i++) {
            pathQueue.addLast(path.get(i));
        }
    }

    private void setSpeedToNextPoint() {
        GridPoint nextPoint = pathQueue.first();
        float angle = MathUtils.atan2(nextPoint.getY() - previousPoint.getX(), nextPoint.getX() - previousPoint.getY());
        deltaX = MathUtils.cos(angle) * speed;
        deltaY = MathUtils.sin(angle) * speed;

    }

    private void checkCollision() {
        if (pathQueue.size > 0) {
            GridPoint targetCity = pathQueue.first();
            if (Vector2.dst(getX(), getY(), targetCity.getX(), targetCity.getY()) < 5) {
                reachNextPoint();
            }
        }
    }
    public void step() {
        setX(getX()+deltaX);
        setY(getY()+deltaY);
        checkCollision();
    }


    private void reachNextPoint() {

        GridPoint nextPoint = pathQueue.first();


        setX(nextPoint.getX());
        setY(nextPoint.getY());

        this.previousPoint = nextPoint;
        pathQueue.removeFirst();

        if (pathQueue.size == 0) {
            reachDestination();
        } else {
            setSpeedToNextPoint();
        }
    }
    private void reachDestination() {
        deltaX = 0;
        deltaY = 0;
    }


    //bs currently
    public void update() {
        step();
    }

    //Get Set
    public int getPrice() {
        return price;
    }
    public Castle getOwner() {
        return owner;
    }
    public int getHealth() {
        return health;
    }
    public int getRange() {
        return range;
    }
    public int getDamage() {
        return damage;
    }
}
