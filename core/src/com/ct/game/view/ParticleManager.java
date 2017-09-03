package com.ct.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ct.game.model.components.TransformComponent;

public class ParticleManager {
    private static ParticleEffectPool particlePool;
    private static Array<ParticleEffect> effects;
    private static ParticleEffect templateEffect;

    public static void init() {
        templateEffect = new ParticleEffect();
        templateEffect.load(Gdx.files.internal("particles/test.p"), Gdx.files.internal("../../rawassets"));
        templateEffect.scaleEffect(1/GameScreen.PPM);
        particlePool = new ParticleEffectPool(templateEffect, 10, 100);
        effects = new Array<ParticleEffect>();
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

    public static void addParticle(TransformComponent tc) {
        ParticleEffectPool.PooledEffect effect = particlePool.obtain();
        effect.setPosition(tc.getPos().x, tc.getPos().y);
        effect.start();
        effects.add(new MovingParticle(effect, tc));
    }

    public static void render(SpriteBatch batch) {
        for (int i = effects.size - 1; i >= 0; i--) {
            ParticleEffect effect = effects.get(i);
            effect.update(Gdx.graphics.getDeltaTime());
            effect.draw(batch);

            if (effect.isComplete()) {
                if(effect instanceof ParticleEffectPool.PooledEffect) {
                    ((ParticleEffectPool.PooledEffect)(effect)).free();
                }
                effects.removeIndex(i);
            }
        }
    }

    static class MovingParticle extends ParticleEffect {
        ParticleEffectPool.PooledEffect effect;
        TransformComponent target;
        MovingParticle(ParticleEffectPool.PooledEffect effect, TransformComponent target) {
            this.effect = effect;
            this.target = target;
        }

        @Override
        public void update(float dt) {
            effect.update(dt);
            effect.setPosition(target.getPos().x, target.getPos().y);
        }

        @Override
        public void draw(Batch batch) {
            effect.draw(batch);
        }

        //takes care of freeing the effect inside moving particle effect class
        @Override
        public boolean isComplete() {
            if(effect.isComplete()) effect.free();
            return effect.isComplete();
        }
    }
}


