package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.*;
import com.ct.game.model.components.*;
import com.ct.game.model.entities.*;
import com.ct.game.model.utils.TileMap;
import com.ct.game.utils.Mappers;

public class WolfSpawnSystem extends EntitySystem {
    private float accum = 0;
    private float timeStep = .5f;

    @Override
    public void update(float deltaTime) {
        accum += deltaTime;
        if (accum > timeStep) {
            accum = 0;
            processSpawning(PlayerControlledComponent.class);
        }
    }

    /**
     * @param type - Class of the object centered around
     */
    //TODO implement general despawn system
    private <T extends Component> void processSpawning(Class<T> type) {
        //98% failure rate
        if (MathUtils.randomBoolean(.2f)) {
            return;
        }
        Entity entity = getEngine().getEntitiesFor(Family.all(type).get()).get(0);
        TransformComponent tc = Mappers.tm.get(entity);
        Vector2 pos = tc.getPos();
        int numPack = MathUtils.random(1, 4);
        float mag;
        float angle;
        for (int i = 0; i < numPack; i++) {
            mag = MathUtils.random(20,40);
            angle = MathUtils.random(0, 360);

            //using sin and cos to spawn mobs in a circle around target
            Wolf wolf = new Wolf();
            wolf.add(new TransformComponent(pos.x + mag * MathUtils.cosDeg(angle),
                                            pos.y + mag * MathUtils.sinDeg(angle),
                                            0f));
            wolf.init();
            getEngine().addEntity(wolf);
        }
        System.out.println("A pack of wolves has spawned near you!");
    }
}
