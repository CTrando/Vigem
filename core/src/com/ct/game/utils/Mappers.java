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
    public static ComponentMapper<LightComponent> lm = ComponentMapper.getFor(LightComponent.class);
    public static ComponentMapper<BounceComponent> bm = ComponentMapper.getFor(BounceComponent.class);
    public static ComponentMapper<AttackComponent> aTm = ComponentMapper.getFor(AttackComponent.class);
    public static ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
}
