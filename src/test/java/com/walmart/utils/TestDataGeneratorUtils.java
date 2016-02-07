package com.walmart.utils;

import com.walmart.seat.Seat;
import com.walmart.seat.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tash on 2/7/16.
 */
public class TestDataGeneratorUtils {

    public static Map<Integer,List<Seat>> getSomeSeatsMapping(){
        Map<Integer,List<Seat>> someSeatMapping = new HashMap<Integer,List<Seat>>();
        List<Seat> arraysOfLists = new ArrayList<Seat>();
        arraysOfLists.add(new Seat(1, 5.01, "100", State.OPEN));
        someSeatMapping.put(1,arraysOfLists);
        return someSeatMapping;
    }
}
