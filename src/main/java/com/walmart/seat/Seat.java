package com.walmart.seat;

/**
 * Created by tash on 2/6/16.
 */
public class Seat {
    Integer id;
    double price;
    String number;
    State state;

    public Seat withState(State pending) {
        state = pending;
        return this;
    }

    public Seat(Integer id, double price, String number, State state) {
        this.id = id;
        this.price = price;
        this.number = number;
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
