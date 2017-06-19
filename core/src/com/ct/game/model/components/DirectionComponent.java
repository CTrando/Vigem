package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Cameron on 6/9/2017.
 */
public class DirectionComponent implements Component {

    public enum Direction {
        up, down, left, right;

        private Direction reverse;

        static {
            up.reverse = down;
            down.reverse = up;
            left.reverse = right;
            right.reverse = left;
        }

        public Direction getReverseDirection(){
            return reverse;
        }
    }

    private Direction direction;
    public DirectionComponent(Direction direction){
        this.direction = direction;
    }

    public DirectionComponent(){
        this.direction = Direction.down;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
