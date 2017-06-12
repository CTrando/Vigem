package com.ct.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ct.game.model.components.*;
import com.ct.game.utils.Mappers;
import com.ct.game.view.ViewHandler;

/**
 * Created by Cameron on 6/6/2017.
 */
public class CameraFocusSystem extends IteratingSystem {
    private ViewHandler viewHandler;

    public CameraFocusSystem(ViewHandler viewHandler) {
        super(Family.all(CameraFocusComponent.class, TransformComponent.class).get());
        this.viewHandler = viewHandler;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent tc = Mappers.tm.get(entity);
        viewHandler.setCameraPos(tc.getPos());
    }
}
