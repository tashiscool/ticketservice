package com.walmart.seat;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tash on 2/6/16.
 */
public class LevelServiceImpl implements LevelService {
    Map<Integer,List<Seat>> seatRowMap = new ConcurrentHashMap<Integer,List<Seat>>();//TODO: load this
    public int getTotalSeatsInLevel(Integer level){
        return seatRowMap.get(level).size();
    }

    public Map<Integer, List<Seat>> find(int numSeats, int statingLevel, int maxLevel) {
        List<Integer> availableRows =  seatRowMap.keySet().stream().filter(p -> p > statingLevel && p < maxLevel).collect(Collectors.toList());
        int seatsReserved = 0;
        Map<Integer, List<Seat>> returnedMap = new ConcurrentHashMap<Integer, List<Seat>>();
        for(Integer row : availableRows){
            if(seatsReserved < numSeats){
                int numSeatsRemaing = numSeats - seatsReserved;
                List<Seat> availableSeats = seatRowMap.get(row).stream().filter(seat -> seat.state.equals(State.OPEN)).collect(Collectors.toList());
                List<Seat> reservedSeats = availableSeats.stream().limit(numSeatsRemaing).collect(Collectors.toList());
                reservedSeats.stream().forEach(seat -> seat.state = State.PENDING);
                seatsReserved += reservedSeats.size();
                returnedMap.put(row,reservedSeats);
            }
        }
        return returnedMap;
    }

    public Integer getAvailableSeats(Integer p) {
        return seatRowMap.get(p).stream().filter(seat ->  seat.state.equals(State.OPEN)).collect(Collectors.toList()).size();
    }

    public Integer getAllAvailableSeats(){
        return seatRowMap.values().stream().flatMap(l -> l.stream())
                .collect(Collectors.toList()).stream().filter(seat ->  seat.state.equals(State.OPEN)).collect(Collectors.toList()).size();
    }

    public Integer maxLevel() {
        return seatRowMap.keySet().stream().max(Comparator.<Integer>naturalOrder()).orElse(0);
    }

    @Override
    public void load(Map<Integer, List<Seat>> someSeatsMapping) {
        seatRowMap = someSeatsMapping;
    }


}
