package com.ct.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ct.game.view.GameScreen;
import io.socket.client.*;
import io.socket.emitter.Emitter;
import org.json.*;

import java.net.URISyntaxException;

public class Vigem extends Game {
	private SpriteBatch batch;
	private GameScreen gameScreen;
	private Socket socket;
	private String uuid;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
		createConnection();
		getSocketInfo();
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

	private void createConnection() {
		try{
			socket = IO.socket("http://localhost:3000");
			socket.connect();
		} catch (URISyntaxException e) {
			System.out.println(e);
		}
	}

	private void getSocketInfo() {
		socket.on("socketID", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					uuid = data.getString("id");
					System.out.println("Your unique user id is " + uuid);
				} catch (JSONException e) {
					System.out.println(e);
				}
			}
		});
	}
}
