package com.ct.game.utils;

/**
 * Created by Cameron on 6/9/2017.
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    private Direction reverse;

    static {
        UP.reverse = DOWN;
        DOWN.reverse = UP;
        LEFT.reverse = RIGHT;
        RIGHT.reverse = LEFT;
    }

    public Direction getReverseDirection(){
        return reverse;
    }
}
