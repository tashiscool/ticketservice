package com.walmart.seat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by tash on 2/6/16.
 */
public class SeatHold {
    String id;
    Map<Integer,List<Seat>> levelsAndSeatsReserved = new ConcurrentHashMap<Integer, List<Seat>>();
    double createdTime = System.currentTimeMillis();

    public SeatHold(String id, Map<Integer, List<Seat>> levelsAndSeatsReserved) {
        this.id = id;
        levelsAndSeatsReserved.values().stream().flatMap(l -> l.stream())
                .collect(Collectors.toList()).stream().map(p->p.withState(State.PENDING));
        this.levelsAndSeatsReserved = levelsAndSeatsReserved;
    }

    public Map<Integer, List<Seat>> getLevelsAndSeatsReserved() {
        return levelsAndSeatsReserved;
    }
}
