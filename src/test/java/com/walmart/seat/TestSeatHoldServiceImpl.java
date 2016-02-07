package com.walmart.seat;

import com.walmart.utils.TestDataGeneratorUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import java.util.*;

import static org.easymock.EasyMock.*;

import org.easymock.TestSubject;
import org.easymock.Mock;
import org.junit.Test;

import static org.junit.Assert.*;

import org.easymock.*;
import org.junit.Rule;

/**
 * Created by tash on 2/6/16.
 */
public class TestSeatHoldServiceImpl{

    @Test
    public void testCreate() {
        SeatHoldServiceImpl service = new SeatHoldServiceImpl();
        Map<Integer, List<Seat>> seats = TestDataGeneratorUtils.getSomeSeatsMapping();
        SeatHold seatHold = service.create(seats);
        seatHold.getLevelsAndSeatsReserved().values().stream().forEach(p -> p.stream().forEach( q -> assertTrue("State needs to update to Pending", q.state == State.PENDING)));
    }

    @Test
    public void testUpdate() {
        SeatHoldServiceImpl service = new SeatHoldServiceImpl();
        Map<Integer, List<Seat>> seats = TestDataGeneratorUtils.getSomeSeatsMapping();
        SeatHold seatHold = service.create(seats);
        service.update(seatHold.id);
        seatHold.getLevelsAndSeatsReserved().values().stream().forEach(p -> p.stream().forEach( q -> assertTrue("State needs to update to Reserved", q.state == State.RESERVED)));

    }
}
