package com.sahaj.metroPaymentSystem;

import com.sahaj.metroPaymentSystem.calculationStrategy.CapLimitBasedCalculator;
import com.sahaj.metroPaymentSystem.calculationStrategy.FareCalculator;
import com.sahaj.metroPaymentSystem.model.TigerCard;
import com.sahaj.metroPaymentSystem.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class MetroPaymentApp {

    public static void main(String[] args) {
        List<Trip> journey = weeklyTrip();
        FareCalculator fareCalculator = new CapLimitBasedCalculator();
        TigerCard tigerCard = TigerCard.getInstance(journey, fareCalculator);

        System.out.println(tigerCard.getTotalFare());
    }

    private static List<Trip> dailyTrip() {
        List<Trip> journey = new ArrayList<>();
        try {
            Trip trip = Trip.addTrip("Saturday", 10, 20, 1, 1, false);
            journey.add(trip);

            trip = Trip.addTrip("Saturday", 10, 45, 1, 1, false);
            journey.add(trip);
        } catch (Exception e) {

        }
        return journey;
    }

    private static List<Trip> weeklyTrip() {
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

        return journey;
    }


}
