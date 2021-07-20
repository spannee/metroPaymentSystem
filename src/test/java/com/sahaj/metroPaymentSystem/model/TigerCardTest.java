package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import com.sahaj.metroPaymentSystem.fareCalculationStrategy.CapLimitBasedCalculator;
import com.sahaj.metroPaymentSystem.fareCalculationStrategy.FareCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TigerCardTest {

    private TigerCard tigerCard;

    private FareCalculator fareCalculator;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getTwoDayFare() throws TigerCardException {
        fareCalculator = new CapLimitBasedCalculator();

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

        tigerCard = TigerCard.getInstance(journey, fareCalculator);
        assertEquals(tigerCard.getTotalFare(), 240);
    }

    @Test
    void getMultiWeekFare() throws TigerCardException {
        fareCalculator = new CapLimitBasedCalculator();

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

        tigerCard = TigerCard.getInstance(journey, fareCalculator);
        assertEquals(tigerCard.getTotalFare(), 720);
    }

    @AfterEach
    void tearDown() {
        TigerCard.clearInstance();
    }
}