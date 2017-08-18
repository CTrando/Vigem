package com.ct.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ct.game.model.utils.QuadTree;
import com.ct.game.utils.*;
import com.ct.game.view.GameScreen;

import java.io.*;
import java.net.Socket;


public class Vigem extends Game {
    private SpriteBatch batch;
    private GameScreen gameScreen;
    private Server server;
    private Client client;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);

        //this.server = new Server();
        //createClient();
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        batch.dispose();
        //client.end();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    private void createClient() {
        client = new Client();
        client.start();
    }
}
