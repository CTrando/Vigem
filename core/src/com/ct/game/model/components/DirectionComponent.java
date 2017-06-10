package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.ct.game.utils.Direction;

/**
 * Created by Cameron on 6/9/2017.
 */
public class DirectionComponent implements Component {

    private Direction direction;
    public DirectionComponent(Direction direction){
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
