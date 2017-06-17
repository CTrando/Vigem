package com.ct.game.utils;

import com.badlogic.ashley.core.*;
import com.ct.game.model.components.*;
import com.ct.game.view.Assets;

/**
 * Created by Cameron on 6/5/2017.
 */
public class Mappers {
    public static ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);
    public static ComponentMapper<MoveComponent> mcm = ComponentMapper.getFor(MoveComponent.class);
    public static ComponentMapper<DirectionComponent> dm = ComponentMapper.getFor(DirectionComponent.class);
    public static ComponentMapper<PhysicsComponent> pHm = ComponentMapper.getFor(PhysicsComponent.class);
    public static ComponentMapper<AnimationComponent> am = ComponentMapper.getFor(AnimationComponent.class);
}
