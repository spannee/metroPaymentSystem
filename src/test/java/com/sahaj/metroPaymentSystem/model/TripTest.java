package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import com.sahaj.metroPaymentSystem.enums.ErrorMessages;
import com.sahaj.metroPaymentSystem.enums.ZoneType;
import com.sahaj.metroPaymentSystem.time.Day;
import com.sahaj.metroPaymentSystem.time.Event;
import com.sahaj.metroPaymentSystem.time.Time;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    private Trip trip;

    @BeforeEach
    void setUp() throws TigerCardException {
        trip = Trip.addTrip("Saturday", 10, 20, 1, 1, false);
    }

    @Test
    void addTrip() {
        assertEquals(trip.getEvent().getDay().getDayId(), 6);
        assertEquals(30, trip.getFare());
    }

    @Test
    void addTripInvalidInput() {
        Assertions.assertThrows(TigerCardException.class, () -> {
            trip = Trip.addTrip("Random", 10, 20, 1, 1, false);
        });
    }

    @Test
    void getFixedFarePeakHour() throws TigerCardException {
        Day day = new Day("Monday");
        Time time = new Time(LocalTime.of(10, 15));
        Event event = new Event(day, time);
        int fixedFare = trip.getFixedFare(event, Zone.getZone(1), Zone.getZone(2));

        assertEquals(35, fixedFare);
    }

    @Test
    void getFixedFareOffPeakHour() throws TigerCardException {
        Day day = new Day("Monday");
        Time time = new Time(LocalTime.of(11, 20));
        Event event = new Event(day, time);
        int fixedFare = trip.getFixedFare(event, Zone.getZone(1), Zone.getZone(2));
        assertEquals(30, fixedFare);

        day = new Day("Friday");
        time = new Time(LocalTime.of(9, 20));
        event = new Event(day, time);
        fixedFare = trip.getFixedFare(event, Zone.getZone(2), Zone.getZone(2));
        assertEquals(25, fixedFare);

        day = new Day("Saturday");
        time = new Time(LocalTime.of(10, 20));
        event = new Event(day, time);
        fixedFare = trip.getFixedFare(event, Zone.getZone(1), Zone.getZone(2));
        assertEquals(35, fixedFare);

        day = new Day("Saturday");
        time = new Time(LocalTime.of(12, 20));
        event = new Event(day, time);
        fixedFare = trip.getFixedFare(event, Zone.getZone(1), Zone.getZone(2));
        assertEquals(30, fixedFare);

        day = new Day("Sunday");
        time = new Time(LocalTime.of(12, 20));
        event = new Event(day, time);
        fixedFare = trip.getFixedFare(event, Zone.getZone(1), Zone.getZone(1));
        assertEquals(25, fixedFare);
    }

    @Test
    void getDailyFixedCapLimit() {
        int dailyFixedCapLimit = trip.getDailyFixedCapLimit(ZoneType.ZONE_TWO, ZoneType.ZONE_ONE);
        assertEquals(120, dailyFixedCapLimit);

        dailyFixedCapLimit = trip.getDailyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_ONE);
        assertEquals(100, dailyFixedCapLimit);

        dailyFixedCapLimit = trip.getDailyFixedCapLimit(ZoneType.ZONE_TWO, ZoneType.ZONE_TWO);
        assertEquals(80, dailyFixedCapLimit);
    }

    @Test
    void getWeeklyFixedCapLimit() {
        int weeklyFixedCapLimit = trip.getWeeklyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_ONE);
        assertEquals(500, weeklyFixedCapLimit);

        weeklyFixedCapLimit = trip.getWeeklyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_TWO);
        assertEquals(600, weeklyFixedCapLimit);

        weeklyFixedCapLimit = trip.getWeeklyFixedCapLimit(ZoneType.ZONE_TWO, ZoneType.ZONE_TWO);
        assertEquals(400, weeklyFixedCapLimit);
    }

    @AfterEach
    void tearDown() {
        trip = null;
    }
}