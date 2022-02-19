package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//Currently set up to do both polling and eventhandling which is stupid
public class CameraHandler implements InputProcessor {
    OrthographicCamera camera;
    Map<Integer,Boolean> flags;
    public CameraHandler(OrthographicCamera camera){
        this.camera=camera;
        flags=new HashMap<>();
        flags.put(Input.Keys.LEFT,false);
        flags.put(Input.Keys.UP,false);
        flags.put(Input.Keys.RIGHT,false);
        flags.put(Input.Keys.DOWN,false);
        flags.put(Input.Keys.P,false);
        flags.put(Input.Keys.M,false);


    }

    public void update(){
        /*
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-50,0);
            System.out.println("LEFT")
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0,50);
            System.out.println("UP");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0,-50);
            System.out.println("DOWN");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(50,0);
            System.out.println("RIGHT");
        }

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            if(camera.zoom>0.1) {
                camera.zoom = camera.zoom + (-0.02f);
                System.out.println(camera.zoom);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            if(camera.zoom<3) {
                camera.zoom = camera.zoom + 0.02f;
                System.out.println(camera.zoom);
            }
        }
        */
        if(flags.get(Input.Keys.RIGHT)){
            camera.translate(50, 0);
        }
        if(flags.get(Input.Keys.LEFT)){
            camera.translate(-50,0);
        }
        if(flags.get(Input.Keys.UP)){
            camera.translate(0, 50);
        }
        if(flags.get(Input.Keys.DOWN)){
            camera.translate(0, -50);
        }
        if(flags.get(Input.Keys.P)){
            if(camera.zoom>0.1) {
                camera.zoom = camera.zoom + (-0.02f);
                System.out.println(camera.zoom);
            }
        }
        if(flags.get(Input.Keys.M)){
            if(camera.zoom<3) {
                camera.zoom = camera.zoom + 0.02f;
                System.out.println(camera.zoom);
            }
        }

    }


    //cool so this is better but I still hate it
    @Override
    public boolean keyDown(int keycode) {
        flags.put(keycode,true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
            flags.put(keycode, false);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();
        x=x* camera.zoom;
        y=y* camera.zoom;
        camera.translate(-x,y);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        amountY=amountY/10;
        System.out.println(amountY);
        if( amountY > 0 && camera.zoom + amountY < 3) {
            camera.zoom = camera.zoom + amountY;

        }
        if(amountY < 0 && camera.zoom+amountY >0.1) {
            camera.zoom = camera.zoom + amountY;

        }
        return true;
    }
}
