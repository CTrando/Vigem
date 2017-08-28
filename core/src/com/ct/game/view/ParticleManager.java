package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ParticleManager {
    private static ParticleEffectPool particlePool;
    private static Array<ParticleEffectPool.PooledEffect> effects;
    private static ParticleEffect templateEffect;

    public static void init() {
        templateEffect = new ParticleEffect();
        templateEffect.load(Gdx.files.internal("particles/fire.p"), Gdx.files.internal("particles"));
        templateEffect.scaleEffect(1/GameScreen.PPM);
        particlePool = new ParticleEffectPool(templateEffect, 10, 100);
        effects = new Array<ParticleEffectPool.PooledEffect>();
    }

    public static void addParticle(float x, float y) {
        ParticleEffectPool.PooledEffect effect = particlePool.obtain();
        effect.setPosition(x, y);
        effect.start();
        effects.add(effect);
    }

    public static void addParticle(Vector2 pos) {
        addParticle(pos.x, pos.y);
    }

    public static void render(SpriteBatch batch) {
        for (int i = effects.size - 1; i >= 0; i--) {
            ParticleEffectPool.PooledEffect effect = effects.get(i);
            effect.update(Gdx.graphics.getDeltaTime());
            effect.draw(batch);

            if (effect.isComplete()) {
                effect.free();
                effects.removeIndex(i);
            }
        }
    }
}
