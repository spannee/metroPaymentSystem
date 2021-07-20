package com.sahaj.metroPaymentSystem.calculationStrategy;

import com.sahaj.metroPaymentSystem.enums.ZoneType;
import com.sahaj.metroPaymentSystem.model.Trip;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CapLimitBasedCalculator implements FareCalculator {

    /**
     * Cap Limit Based Fare Calculator
     * @param journey
     * @return
     */
    public int fareCalculation(List<Trip> journey) {
        return calculateFareForMultipleWeeks(journey);
    }

    /**
     * Calculates the cost for the entire trip
     * @param journey
     * @return
     */
    public int calculateFareForMultipleWeeks(List<Trip> journey) {
        int totalFare = 0;
        List<Trip> weeklyTripList = new ArrayList<>();
        for (Trip trip: journey) {
            if (trip.isNewWeek()) {
                Map<Integer, List<Trip>> weeklyTrips = groupByDay(weeklyTripList);
                totalFare += calculateFareForOneWeek(weeklyTrips);
                weeklyTripList = new ArrayList<>();
            }
            weeklyTripList.add(trip);
        }
        if (!weeklyTripList.isEmpty()) {
            Map<Integer, List<Trip>> weeklyTrips = groupByDay(weeklyTripList);
            totalFare += calculateFareForOneWeek(weeklyTrips);
        }
        return totalFare;
    }

    /**
     * Calculates the cost for one particular week
     * @param weeklyTrips
     * @return
     */
    public int calculateFareForOneWeek(Map<Integer, List<Trip>> weeklyTrips) {
        int weeklyFare = 0;
        int weeklyFareAfterCapping = 0;
        boolean hasTravelledFarther = hasTravelledFurther(weeklyTrips);
        for (Map.Entry<Integer, List<Trip>> dayTrips : weeklyTrips.entrySet()) {
            List<Trip> oneDayTrips = dayTrips.getValue();
            weeklyFare += calculateFareForOneDay(oneDayTrips, hasTravelledFarther);
            weeklyFareAfterCapping = weeklyCapLimitBasedCalculation(oneDayTrips, weeklyFare, hasTravelledFarther);
        }
        return weeklyFareAfterCapping;
    }

    /**
     * Applies cap to the overall fare for the given week and adjusts the fare of each trip with the discounted price
     * @param trips
     * @param weeklyFare
     * @param hasTravelledFarther
     * @return
     */
    public int weeklyCapLimitBasedCalculation(List<Trip> trips, int weeklyFare, boolean hasTravelledFarther) {
        int dayFare = trips.stream().mapToInt(Trip::getFare).sum();
        int weeklyFareBeforeCurrentDay = weeklyFare - dayFare;
        for (Trip trip: trips) {
            int weeklyFixedCapLimit = hasTravelledFarther ?
                    trip.getWeeklyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_TWO) :
                    trip.getWeeklyFixedCapLimit(trip.getFromZone().getZoneType(), trip.getToZone().getZoneType());

            if (weeklyFareBeforeCurrentDay + trip.getFare() >= weeklyFixedCapLimit) {
                int reducedFare = weeklyFixedCapLimit - weeklyFareBeforeCurrentDay;
                trip.setFare(reducedFare);
                weeklyFareBeforeCurrentDay += reducedFare;
            } else {
                weeklyFareBeforeCurrentDay += trip.getFare();
            }
        }
        return weeklyFareBeforeCurrentDay;
    }

    /**
     * Calculates the cost for one particular day
     * @param dayTrips
     * @param hasTravelledFarther
     * @return
     */
    public int calculateFareForOneDay(List<Trip> dayTrips, boolean hasTravelledFarther) {
        int oneDayFare = 0;
        for(Trip trip: dayTrips) {
            int tripFare = dailyCapLimitBasedCalculation(trip, oneDayFare, hasTravelledFarther);
            trip.setFare(tripFare);
            oneDayFare += tripFare;
        }
        return oneDayFare;
    }

    /**
     * Applies cap to the overall fare for the given day and adjusts the fare of each trip with the discounted price
     * @param trip
     * @param oneDayFare
     * @param hasTravelledFarther
     * @return
     */
    public int dailyCapLimitBasedCalculation(Trip trip, int oneDayFare, boolean hasTravelledFarther) {
        int dailyFixedCapLimit = hasTravelledFarther ?
                trip.getDailyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_TWO):
                trip.getDailyFixedCapLimit(trip.getFromZone().getZoneType(), trip.getToZone().getZoneType());
        if (oneDayFare + trip.getFare() >= dailyFixedCapLimit) {
            return dailyFixedCapLimit - oneDayFare;
        }
        return trip.getFare();
    }

    /**
     *
     * @param weeklyTripList
     * @return
     */
    private Map<Integer, List<Trip>> groupByDay(List<Trip> weeklyTripList) {
        return weeklyTripList.stream().collect(
                Collectors.groupingBy(weeklyTrip -> weeklyTrip.getEvent().getDay().getDayId(),
                        LinkedHashMap::new, Collectors.toList()));
    }

    /**
     *
     * @param weeklyTrips
     * @return
     */
    private boolean hasTravelledFurther(Map<Integer, List<Trip>> weeklyTrips) {
        return weeklyTrips.entrySet().stream().anyMatch(integerListEntry -> integerListEntry.getValue().stream().anyMatch(trip -> (trip.getFromZone().getZoneType() == ZoneType.ZONE_ONE &&
                trip.getToZone().getZoneType() == ZoneType.ZONE_TWO) || (trip.getFromZone().getZoneType() == ZoneType.ZONE_TWO &&
                trip.getToZone().getZoneType() == ZoneType.ZONE_ONE)));
    }

}
