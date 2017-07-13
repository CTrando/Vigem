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
    private ShaderProgram waterShader;
    private Texture distortion = new Texture(Gdx.files.internal("distortionMap.jpg"));
    private Texture normal = new Texture(Gdx.files.internal("normalMap.png"));

    private final FileHandle vertexShader = Gdx.files.internal("vertex.glsl");
    private final FileHandle fragmentShader = Gdx.files.internal("fragment.glsl");

    private float speed = .04f;
    private float moveFactor = 0;

    public void init() {
        ShaderProgram.pedantic = false;
        waterShader = new ShaderProgram(vertexShader, fragmentShader);
        distortion.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        if(!waterShader.isCompiled()) {
            System.out.println(waterShader.getLog());
        }
    }

    public void bindShader(SpriteBatch batch) {
        batch.setShader(waterShader);
        moveFactor += speed*Gdx.graphics.getDeltaTime();
        moveFactor %= 1;
        waterShader.setUniformf("u_time", moveFactor);
        waterShader.setUniformi("u_distortion_map", 2);
        waterShader.setUniformi("u_normal_map", 3);
        distortion.bind(2);
        normal.bind(3);
        Gdx.gl20.glActiveTexture(GL20.GL_TEXTURE0);
    }

    public void unBindShader(SpriteBatch batch) {
        batch.setShader(null);
    }
}
