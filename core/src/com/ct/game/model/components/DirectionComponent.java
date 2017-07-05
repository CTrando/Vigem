package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.*;

/**
 * Created by Cameron on 6/9/2017.
 */
public class DirectionComponent implements Component {

    public enum Direction {
        up, down, left, right;

        private Direction reverse;
        private Vector2 coordinateDirection;
        private float angle;

        static {
            up.reverse = down;
            down.reverse = up;
            left.reverse = right;
            right.reverse = left;

            up.coordinateDirection = Vector2.Y;
            down.coordinateDirection = Vector2.Y.scl(-1);
            left.coordinateDirection = Vector2.X.scl(-1);
            right.coordinateDirection = Vector2.X;

            up.angle = MathUtils.PI/2;
            down.angle = MathUtils.PI*3/2;
            left.angle = MathUtils.PI;
            right.angle =  0;
        }

        public Direction getReverseDirection(){
            return reverse;
        }

        public Vector2 getCoordinateDirection() {
            return coordinateDirection;
        }

        public float getAngle() {
            return angle;
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
