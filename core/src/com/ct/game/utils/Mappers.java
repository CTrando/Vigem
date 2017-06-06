package com.ct.game.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.ct.game.model.components.*;

/**
 * Created by Cameron on 6/5/2017.
 */
public class Mappers {
    public static ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    public static ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);
}
