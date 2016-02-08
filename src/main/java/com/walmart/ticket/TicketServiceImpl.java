package com.walmart.ticket;

import com.walmart.seat.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by tash on 2/6/16.
 */
public class TicketServiceImpl implements TicketService {

    LevelService levelService;
    SeatHoldService seatHoldService;

    public int numSeatsAvailable(Optional<Integer> venueLevel) {
        //what's the short hand for this?
        Supplier<Integer> supplier = new Supplier<Integer>() {
            public Integer get() {
                return levelService.getAllAvailableSeats();
            }
        };
        return venueLevel.map(p -> levelService.getAvailableSeats(p)).orElseGet(supplier);
    }

    public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {

        int statingLevel = minLevel.orElse(0);
        int endingLevel = maxLevel.orElse(levelService.maxLevel());
        Map<Integer, List<Seat>> seatsFound = levelService.find(numSeats, statingLevel, endingLevel);
        return seatHoldService.create(seatsFound);
    }

    public String reserveSeats(int seatHoldId, String customerEmail) {
        return seatHoldService.update(String.valueOf(seatHoldId)).orElse("");
    }

    @Override
    public void setLevelService(LevelService mockLevel) {
        levelService = mockLevel;
    }

    @Override
    public void setSeatHoldService(SeatHoldService mockSeats) {
        seatHoldService = mockSeats;
    }
}
