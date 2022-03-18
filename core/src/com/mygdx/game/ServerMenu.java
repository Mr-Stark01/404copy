package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MainMenu;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.Input.TextInputListener;
import com.mygdx.game.network.Client;
import com.mygdx.game.network.ClientHandler;
import com.mygdx.game.network.Server;
import com.mygdx.game.network.ServerHandler;
import java.util.regex.Pattern;

public class ServerMenu implements Screen,TextInputListener {

    final MyGdxGame game;
    OrthographicCamera camera;

    Texture bg;

    int backButtonX;
    int backbuttonY;
    int backButtonWid;
    int backButtonHei;

    int joinButtonY;

    int createButtonY;
    int createButtonWid;

    int buttonX;
    int buttonWid;
    int buttonHei;

    String text;


    public ServerMenu(final MyGdxGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

    }

    public boolean correctIPCheck(String text){
        String IPV4_REGEX =
                "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";

        Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

        if(text == null){
            return false;
        }

        if(!IPv4_PATTERN.matcher(text).matches()){
            return false;
        }

        String[] parts = text.split("\\.");

        // verify that each of the four subgroups of IPv4 addresses is legal
        try{
            for(String segment: parts)
            {
                // x.0.x.x is accepted but x.01.x.x is not
                if (Integer.parseInt(segment) > 255 ||
                        (segment.length() > 1 && segment.startsWith("0"))) {
                    return false;
                }
            }
        }catch(NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public void show() {
        //ScreenUtils.clear(255, 98, 0, 1);
        bg = new Texture("menu/server_menu.png");

        backButtonWid = 250;
        backButtonHei = 100;
        backButtonX = 1920-backButtonWid-50;
        backbuttonY = 50;

        buttonWid = 210;
        buttonX = 1920/2-buttonWid/2;
        buttonHei = 90;

        joinButtonY = 600;

        createButtonY = 480;
        createButtonWid = 345;
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(bg, 0 , 0, 1920, 1080);
        game.font.setColor(0,0,0,1);
        game.font.draw(game.batch, "Game by 404", 1700, 40);

        //joinButton
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX  - ((buttonWid - buttonWid)/2) + buttonWid && vec.x > buttonX - ((buttonWid - buttonWid)/2) && vec.y > joinButtonY  && vec.y < joinButtonY + buttonHei){
                //join to game
                text = "";
                while(!correctIPCheck(text)){
                    Gdx.input.getTextInput(this,"Enter IP address","","");
                }
                ClientHandler clh=new ClientHandler(new Client(),text);
                game.setScreen(new GameScreen(game,clh));
                dispose();
            }
        }

        //createButton
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < buttonX  - ((createButtonWid - buttonWid)/2) + createButtonWid && vec.x > buttonX - ((createButtonWid - buttonWid)/2) && vec.y > createButtonY  && vec.y < createButtonY + buttonHei){
                //create game
                Gdx.input.getTextInput(this,"Enter New Game Name","","");
                ServerHandler serverHandler=new ServerHandler(new Server());
                game.setScreen(new GameScreen(game,serverHandler));
                dispose();
            }
        }



        //backButton
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            Vector3 vec=new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(vec);
            if(vec.x < backButtonX + backButtonWid && vec.x > backButtonX && vec.y > backbuttonY  && vec.y < backbuttonY + backButtonHei){
                game.setScreen(new MainMenu(game));
                dispose();
            }
        }

        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void input(String text) {
        this.text = text;
        MyGdxGame.setGameName(text);
    }

    @Override
    public void canceled() {
        text = "Cancelled";
    }
}

