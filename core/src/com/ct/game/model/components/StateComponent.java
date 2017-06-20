package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Cameron on 6/18/2017.
 */
public class StateComponent implements Component {

    public enum State {
        dashingUp, dashingDown, dashingLeft, dashingRight,
        dashing(
                dashingUp,
                dashingDown,
                dashingLeft,
                dashingRight
        ),
        runningUp, runningDown, runningLeft, runningRight,
        running(
                runningUp,
                runningDown,
                runningLeft,
                runningRight
        ),
        slowingUp, slowingDown, slowingLeft, slowingRight,
        slowing(
                slowingUp,
                slowingDown,
                slowingLeft,
                slowingRight
        ), idleUp, idleDown, idleLeft, idleRight,
        idle(
                idleUp,
                idleDown,
                idleLeft,
                idleRight
        );

        private State up, down, left, right;

        State(){
        }

        State(State up, State down, State left, State right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

        public State getUp() {
            return up;
        }

        public State getDown() {
            return down;
        }

        public State getLeft() {
            return left;
        }

        public State getRight() {
            return right;
        }
    }

    private State state;

    public StateComponent(State state){
        this.state = state;
    }

    public StateComponent() {
        this.state = State.idle;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setStateDirection(DirectionComponent.Direction direction) {
        switch(direction) {
            case up:
                setState(getState().getUp());
                break;
            case down:
                setState(getState().getDown());
                break;
            case left:
                setState(getState().getLeft());
                break;
            case right:
                setState(getState().getRight());
                break;
        }
    }
}
