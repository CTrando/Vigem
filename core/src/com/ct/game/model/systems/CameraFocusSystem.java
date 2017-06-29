package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.ViewportManager;

/**
 * Created by Cameron on 6/6/2017.
 */
public class CameraFocusSystem extends IteratingSystem {
    private ViewportManager viewportManager;

    public CameraFocusSystem(ViewportManager viewportManager) {
        super(Family.all(CameraFocusComponent.class, TransformComponent.class).get());
        this.viewportManager = viewportManager;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent tc = Mappers.tm.get(entity);
        viewportManager.setCameraPos(tc.getPos());
    }
}
