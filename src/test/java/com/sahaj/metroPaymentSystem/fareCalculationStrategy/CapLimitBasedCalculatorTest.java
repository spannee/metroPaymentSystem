package com.sahaj.metroPaymentSystem.fareCalculationStrategy;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import com.sahaj.metroPaymentSystem.model.Trip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CapLimitBasedCalculatorTest {

    private CapLimitBasedCalculator fareCalculator;

    @BeforeEach
    void setUp() {
        fareCalculator = new CapLimitBasedCalculator();
    }

    @Test
    void dailyCapLimitBasedCalculationWithCapping() throws TigerCardException {
        Trip trip = Trip.addTrip("Monday", 10, 20, 2, 1, false);
        int cappedFare = fareCalculator.dailyCapLimitBasedCalculation(trip, 100, false);

        assertEquals(20, cappedFare);
    }

    @Test
    void dailyCapLimitBasedCalculationWithoutCapping() throws TigerCardException {
        Trip trip = Trip.addTrip("Monday", 10, 20, 2, 1, false);
        int cappedFare = fareCalculator.dailyCapLimitBasedCalculation(trip, 30, false);

        assertEquals(35, cappedFare);
    }

    @Test
    void calculateFareForOneDay() throws TigerCardException {
        List<Trip> dayTrips = new ArrayList<>();
        Trip trip = Trip.addTrip("Monday", 10, 20, 2, 1, false);
        dayTrips.add(trip);
        trip = Trip.addTrip("Monday", 10, 45, 1, 1, false);
        dayTrips.add(trip);
        int totalFare = fareCalculator.calculateFareForOneDay(dayTrips, false);

        assertEquals(60, totalFare);
    }

    @Test
    void weeklyCapLimitBasedCalculation() throws TigerCardException {
        List<Trip> trips = new ArrayList<>();
        Trip trip = Trip.addTrip("Saturday", 10, 20, 1, 1, false);
        trips.add(trip);
        trip = Trip.addTrip("Sunday", 10, 45, 1, 1, false);
        trips.add(trip);
        int totalFare = fareCalculator.weeklyCapLimitBasedCalculation(trips, 620, true);

        assertEquals(600, totalFare);
        assertEquals(trips.stream()
                .filter(lastTripOfDay -> lastTripOfDay.getEvent().getDay().getDayId() == 7).findFirst().get().getFare(),
                10);
    }

    @Test
    void calculateFareForOneWeek() throws TigerCardException {
        List<Trip> journey = new ArrayList<>();
        Map<Integer, List<Trip>> weeklyTrip = new LinkedHashMap<>();
        //Monday
        Trip trip = Trip.addTrip("Monday", 10, 20, 2, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 10, 45, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 16, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 18, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 19, 0, 1, 2, false);
        journey.add(trip);
        weeklyTrip.put(1, journey);
        //Tuesday
        journey = new ArrayList<>();
        trip = Trip.addTrip("Tuesday", 10, 20, 2, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Tuesday", 10, 45, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Tuesday", 16, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Tuesday", 18, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Tuesday", 19, 0, 1, 2, false);
        journey.add(trip);
        weeklyTrip.put(2, journey);
        int totalFare = fareCalculator.calculateFareForOneWeek(weeklyTrip);

        assertEquals(240, totalFare);
    }

    @Test
    void calculateFareForMultipleWeeks() throws TigerCardException {
        List<Trip> journey = new ArrayList<>();
        //Monday
        Trip trip = Trip.addTrip("Monday", 10, 20, 2, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 10, 45, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 16, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 18, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 19, 0, 1, 2, false);
        journey.add(trip);
        //Tuesday
        trip = Trip.addTrip("Tuesday", 10, 20, 2, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Tuesday", 10, 45, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Tuesday", 16, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Tuesday", 18, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Tuesday", 19, 0, 1, 2, false);
        journey.add(trip);
        //Wednesday
        trip = Trip.addTrip("Wednesday", 10, 20, 2, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Wednesday", 10, 45, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Wednesday", 16, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Wednesday", 18, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Wednesday", 19, 0, 1, 2, false);
        journey.add(trip);
        //Thursday
        trip = Trip.addTrip("Thursday", 10, 20, 2, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Thursday", 10, 45, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Thursday", 16, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Thursday", 18, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Thursday", 19, 0, 1, 2, false);
        journey.add(trip);
        //Friday
        trip = Trip.addTrip("Friday", 10, 45, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Friday", 16, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Friday", 18, 15, 1, 1, false);
        journey.add(trip);
        //Saturday
        trip = Trip.addTrip("Saturday", 10, 20, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Saturday", 10, 45, 1, 1, false);
        journey.add(trip);
        //Sunday
        trip = Trip.addTrip("Sunday", 10, 20, 1, 1, false);
        journey.add(trip);
        //Sunday
        trip = Trip.addTrip("Sunday", 10, 20, 1, 1, false);
        journey.add(trip);
        //Monday Next Week
        trip = Trip.addTrip("Monday", 10, 20, 2, 1, true);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 10, 45, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 16, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 18, 15, 1, 1, false);
        journey.add(trip);
        trip = Trip.addTrip("Monday", 19, 0, 1, 2, false);
        journey.add(trip);
        int totalFare = fareCalculator.calculateFareForMultipleWeeks(journey);

        assertEquals(720, totalFare);
    }

    @AfterEach
    void tearDown() {
        fareCalculator = null;
    }
}