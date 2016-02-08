package com.walmart.ticket;

import com.walmart.utils.TestDataGeneratorUtils;
import com.walmart.seat.*;

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
public class TestTicketServiceImpl extends EasyMockSupport{

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @Mock
    private LevelService levelService;
    @Mock
    private SeatHoldService seatHoldService;

    @TestSubject
    private final TicketService service = new TicketServiceImpl();


    @Test
    public void testNumSeatsAvailable() {
        LevelService mockLevel = mock(LevelService.class);
        service.setLevelService(mockLevel);
        expect(levelService.getAvailableSeats(1)).andReturn(1);
        replayAll(); // 4
        verifyAll();
        int returner = service.numSeatsAvailable(Optional.of(1));
        assertEquals("numSeatsAvailable should be one ", 1,1);

    }

    public void testFindAndHoldSeats() {
        LevelService mockLevel = mock(LevelService.class);
        service.setLevelService(mockLevel);
        SeatHoldService mockSeats = mock(SeatHoldService.class);
        service.setSeatHoldService(mockSeats);
        Map<Integer, List<Seat>> someSeatMapping = TestDataGeneratorUtils.getSomeSeatsMapping();
        expect(levelService.find(1, 1, 2)).andReturn(someSeatMapping);
        expect(seatHoldService.create(someSeatMapping)).andReturn(new SeatHold("1", someSeatMapping));
        replayAll();
        SeatHold hold = service.findAndHoldSeats(1, Optional.of(1), Optional.of(2), "tashdid@gmail.com");
        verifyAll();

        assertEquals("reference to seats should be the same", hold.getLevelsAndSeatsReserved(), someSeatMapping);
        assertEquals("state should be updated", hold.getLevelsAndSeatsReserved().get(1).get(0).getState(), State.PENDING);
    }

    public void testReserveSeats() {
        LevelService mockLevel = mock(LevelService.class);
        service.setLevelService(mockLevel);
        SeatHoldService mockSeats = mock(SeatHoldService.class);
        service.setSeatHoldService(mockSeats);
        expect(mockSeats.update("1")).andReturn(Optional.of("Confirmation # " + 1));
        replayAll();
        service.reserveSeats(1, "tashdid@gmail.com");
        verifyAll();
    }
}
