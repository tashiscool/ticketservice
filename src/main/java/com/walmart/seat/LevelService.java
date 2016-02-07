package com.walmart.seat;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by tash on 2/6/16.
 */
public interface LevelService {
    /**
     * finds the next best a seat assignement
     * @param numSeats request number of steats
     * @param statingLevel starting level
     * @param endingLevel ending level
     * @return seat assignment
     */
    Map<Integer,List<Seat>> find(int numSeats, int statingLevel, int endingLevel);

    /**
     *get Available Seats for that level
     * @param p level
     * @return seat count at that level
     */
    Integer getAvailableSeats(Integer p);

    /**
     * gets ALL Available Seats
     * @return all seats count
     */
    Integer getAllAvailableSeats();

    /**
     *
     * @return highest level
     */
    Integer maxLevel();

    /**
     * conveience method for data load
     * @param someSeatsMapping
     */
    void load(Map<Integer, List<Seat>> someSeatsMapping);
}
