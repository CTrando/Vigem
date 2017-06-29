package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Created by Cameron on 6/27/2017.
 */
public class ShaderManager {
    private ShaderProgram testShader;
    private Texture distortion = new Texture(Gdx.files.internal("distortionMap.jpg"));

    private final FileHandle vertexShader = Gdx.files.internal("vertex.glsl");
    private final FileHandle fragmentShader = Gdx.files.internal("fragment.glsl");

    private float speed = .04f;
    private float moveFactor = 0;

    public void init() {
        ShaderProgram.pedantic = false;
        testShader = new ShaderProgram(vertexShader, fragmentShader);
        distortion.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        if(!testShader.isCompiled()) {
            System.out.println(testShader.getLog());
        }
    }

    public void bindShader(SpriteBatch batch) {
        batch.setShader(testShader);
        moveFactor += speed*Gdx.graphics.getDeltaTime();
        moveFactor %= 1;
        testShader.setUniformf("u_time", moveFactor);
        testShader.setUniformi("u_distortion_map", 2);
        distortion.bind(2);
        Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE0);
    }

    public void unBindShader(SpriteBatch batch) {
        batch.setShader(null);
    }
}
