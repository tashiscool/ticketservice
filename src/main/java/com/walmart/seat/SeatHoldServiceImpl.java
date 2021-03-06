package com.walmart.seat;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by tash on 2/6/16.
 */
public class SeatHoldServiceImpl implements SeatHoldService {
    long TIMEOUT_ALLOWED = 30000;
    Map<String, SeatHold> reservedSeats = new ConcurrentHashMap<String, SeatHold>();
    Random generator = new Random();



    public SeatHold create(Map<Integer, List<Seat>> seatsFound) {
        String id = String.valueOf(generator.nextInt());
        SeatHold seatHold = new SeatHold(id, seatsFound);
        reservedSeats.put(id,seatHold);
        return seatHold;
    }

    public Optional<String> update(String seatHoldId) {
        SeatHold heldSeat = reservedSeats.get(seatHoldId);
        if(System.currentTimeMillis() - heldSeat.createdTime < TIMEOUT_ALLOWED ) {
            heldSeat.levelsAndSeatsReserved.values().stream().flatMap(l -> l.stream())
                    .collect(Collectors.toList()).stream().forEach(p -> p.withState(State.RESERVED));
            return Optional.of("Confirmation # " + seatHoldId);
        }
        else
            return Optional.empty();
    }
}
