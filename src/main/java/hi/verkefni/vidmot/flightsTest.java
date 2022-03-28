package main.java.hi.verkefni.vidmot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Calendar.AM;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class flightsTest {

    private Flights flights;

    @Before
    public void setUp() {
        int price = 220000;
        String depLoc = "KEF";
        String destLoc = "JPN";
        LocalTime depTime = LocalTime.of(4,20);
        LocalDate depDate = LocalDate.of(2022, 3, 29);
        int tripDuration = 19;

        flights = new Flights(price, depLoc, destLoc, depTime, depDate, tripDuration);
    }

    @After
    public void tearDown() {
        flights = null;
    }

    @Test
    public void testThisFlight() {
        assertEquals(220000, flights.getFlightsPrice());
        assertEquals("KEF", flights.getDepartureLocation());
        assertEquals("JPN", flights.getDestinationLocation());
        assertEquals(LocalTime.of(4, 20), flights.getDepartureTime());
        assertEquals(LocalDate.of(2022, 3, 29), flights.getDepartureDate());
        assertEquals(19, flights.getTripDuration());
    }

}
