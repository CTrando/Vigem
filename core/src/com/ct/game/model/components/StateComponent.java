package com.ct.game.model.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Cameron on 6/18/2017.
 */
public class StateComponent implements Component {

    public enum State {
        dashing, running, slowing, idle;
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
}
