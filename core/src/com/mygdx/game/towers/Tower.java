package com.mygdx.game.towers;

import com.mygdx.game.Castle;
import com.mygdx.game.units.Knight;

import java.util.ArrayList;

public abstract class Tower {
    protected int damage;
    protected int price;
    protected int health;
    protected int range;
    protected int[][] ranges;
    protected int posX;
    protected int posY;
    protected Castle owner;

    // CONSTRUCTORS
    public Tower(int damage, int price, int health, int range, Castle owner) {
        this.damage = damage;
        this.price = price;
        this.health = health;
        this.range = range;
        this.owner = owner;
    }

    // FUNCTIONS
    public void buildTower(int posX, int posY){
        this.posX=posX;
        this.posY=posY;
    }

    public void selectTarget(){

    }

    public void attack(Knight target){
        //target.recieveDamage(this.damage);
    }

    // GETTERS & SETTERS
    public int getPrice() {
        return price;
    }

    public void setOwner(Castle owner) {
        this.owner = owner;
    }
}
