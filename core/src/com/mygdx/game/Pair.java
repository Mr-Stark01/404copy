package com.mygdx.game;

import java.io.Serializable;

public class Pair implements Serializable,Cloneable {
    protected float x,y;

    public Pair(float x,float y){
        this.x=x;
        this.y=y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public Pair clone() {
        try {
            return (Pair) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
