package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	Music music;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenu(this));
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
	}

	@Override
	public void render () {
		super.render();  // important!
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		music.dispose();
	}
}
