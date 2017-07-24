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
            down.coordinateDirection = Vector2.Y.cpy().scl(-1);
            left.coordinateDirection = Vector2.X.cpy().scl(-1);
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

        public static Direction getClosestDirection(Vector2 vec) {
            return getClosestDirection(vec, 0);
        }

        public static Direction getClosestDirection(Vector2 vec, float rotation) {
            vec.nor();
            int angle = (int) ((vec.angle() + rotation)/90);
            switch(angle) {
                case 0:
                    return right;
                case 1:
                    return up;
                case 2:
                    return left;
                case 3:
                    return down;
                default:
                    return down;
            }
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
