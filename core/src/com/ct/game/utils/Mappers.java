package com.ct.game.utils;

import com.badlogic.ashley.core.*;
import com.ct.game.model.components.*;

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
    public static ComponentMapper<StateComponent> sm = ComponentMapper.getFor(StateComponent.class);
    public static ComponentMapper<CreateBodyComponent> bCm = ComponentMapper.getFor(CreateBodyComponent.class);
    public static ComponentMapper<CreateLightComponent> cLm = ComponentMapper.getFor(CreateLightComponent.class);
    public static ComponentMapper<LightComponent> lm = ComponentMapper.getFor(LightComponent.class);
}
