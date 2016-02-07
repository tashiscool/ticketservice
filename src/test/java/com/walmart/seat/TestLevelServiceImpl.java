package com.walmart.seat;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.easymock.EasyMock.*;

import com.walmart.utils.TestDataGeneratorUtils;
import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;

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
public class TestLevelServiceImpl {



    @Test
    public void testFind() {
        LevelService levelService = new LevelServiceImpl();
        levelService.load(TestDataGeneratorUtils.getSomeSeatsMapping());
        Map<Integer, List<Seat>> seats = levelService.find(1, 1, 2);
        seats.keySet().stream().forEach(key -> assertTrue(key >0) );
    }

    @Test
    public void testGetAvailableSeats() {
        LevelService levelService = new LevelServiceImpl();
        levelService.load(TestDataGeneratorUtils.getSomeSeatsMapping());
        int returner = levelService.getAvailableSeats(1);
        assertEquals("there should be one seat at level 1", returner, 1);
    }

    @Test
    public void testGetAllAvailableSeats(){
        LevelService levelService = new LevelServiceImpl();
        levelService.load(TestDataGeneratorUtils.getSomeSeatsMapping());
        int returner = levelService.getAllAvailableSeats();
        assertEquals("there should be one seat", returner, 1);

    }

    @Test
    public void testMaxLevel() {
        LevelService levelService = new LevelServiceImpl();
        levelService.load(TestDataGeneratorUtils.getSomeSeatsMapping());
        int returner = levelService.maxLevel();
        assertEquals("there should be one seat", returner, 1);
    }


}
