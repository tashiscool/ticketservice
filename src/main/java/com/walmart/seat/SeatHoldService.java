package com.walmart.seat;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by tash on 2/6/16.
 */
public interface SeatHoldService {
    /**
     * create a seat hold based on seat assignment
     * @param seatsFound
     * @return
     */
    public SeatHold create(Map<Integer, List<Seat>> seatsFound);

    /**
     * update seats to reserved
     * @param seatHoldId
     * @return
     */
    public Optional<String> update(String seatHoldId);
}
