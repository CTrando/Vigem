package com.ct.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.Socket;
import com.ct.game.utils.Server;
import com.ct.game.view.GameScreen;

import java.net.URISyntaxException;

public class Vigem extends Game {
	private SpriteBatch batch;
	private GameScreen gameScreen;
	private Server server;
	private Socket client;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
		this.server = new Server();
		this.client = Gdx.net.newClientSocket(Net.Protocol.TCP, "localhost", 9021, null);
	}

	@Override
	public void render () {
		screen.render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
