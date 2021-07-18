package com.sahaj.metroPaymentSystem.calculationStrategy;

import com.sahaj.metroPaymentSystem.enums.ZoneType;
import com.sahaj.metroPaymentSystem.model.Trip;
import com.sahaj.metroPaymentSystem.repository.CapLimitRepository;
import com.sahaj.metroPaymentSystem.time.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CapLimitBasedCalculator implements FareCalculator {

    public int fareCalculation(List<Trip> journey) {
        return calculateFareForMultipleWeeks(journey);
    }

    private int calculateFareForMultipleWeeks(List<Trip> journey) {
        int totalFare = 0;
        List<Trip> weeklyTripList = new ArrayList<>();
        for (Trip trip: journey) {
            if (trip.isNewWeek()) {
                Map<Day, List<Trip>> weeklyTrips = weeklyTripList.stream().collect(
                        Collectors.groupingBy(weeklyTrip -> weeklyTrip.getEvent().getDay()));
                totalFare += calculateFareForOneWeek(weeklyTrips);
                weeklyTripList = new ArrayList<>();
            }
            weeklyTripList.add(trip);
        }
        return totalFare;
    }

    private int calculateFareForOneWeek(Map<Day, List<Trip>> weeklyTrips) {
        int weeklyFare = 0;
        for (Map.Entry<Day, List<Trip>> dayTrips : weeklyTrips.entrySet()) {
            List<Trip> oneDayTrips = dayTrips.getValue();
            boolean hasTravelledFarther = hasTravelledFurther(oneDayTrips);
            calculateFareForOneDay(oneDayTrips, hasTravelledFarther);
            weeklyFare += weeklyCapLimitBasedCalculation(oneDayTrips, weeklyFare, hasTravelledFarther);

        }
        return weeklyFare;
    }

    private int weeklyCapLimitBasedCalculation(List<Trip> trips, int weeklyFare, boolean hasTravelledFarther) {
        for (Trip trip: trips) {
            int weeklyFixedCapLimit = hasTravelledFarther ?
                    getWeeklyFixedCapLimit(trip.getFromZone().getZoneType(), trip.getToZone().getZoneType()) :
                    getWeeklyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_TWO);
            if (weeklyFare + trip.getFare() < weeklyFixedCapLimit) {
                weeklyFare += trip.getFare();
            } else {
                trip.setFare(weeklyFixedCapLimit - weeklyFare);
            }
        }
        return weeklyFare;
    }

    private void calculateFareForOneDay(List<Trip> dayTrips, boolean hasTravelledFarther) {
        int oneDayFare = 0;
        for(Trip trip: dayTrips) {
            int tripFare = dailyCapLimitBasedCalculation(trip, oneDayFare, hasTravelledFarther);
            trip.setFare(tripFare);
            oneDayFare += tripFare;
        }
    }

    private int dailyCapLimitBasedCalculation(Trip trip, int oneDayFare, boolean hasTravelledFarther) {
        int dailyFixedCapLimit = hasTravelledFarther ?
                getDailyFixedCapLimit(trip.getFromZone().getZoneType(), trip.getToZone().getZoneType()) :
                getDailyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_TWO);
        if (oneDayFare + trip.getFare() >= dailyFixedCapLimit) {
            return dailyFixedCapLimit - oneDayFare;
        }
        return trip.getFare();
    }

    private boolean hasTravelledFurther(List<Trip> trips) {
        return trips.stream().anyMatch(trip -> (trip.getFromZone().getZoneType() == ZoneType.ZONE_ONE &&
                trip.getToZone().getZoneType() == ZoneType.ZONE_TWO) || (trip.getFromZone().getZoneType() == ZoneType.ZONE_TWO &&
                trip.getToZone().getZoneType() == ZoneType.ZONE_ONE));
    }

    private int getDailyFixedCapLimit(ZoneType fromZoneType, ZoneType toZoneType) {
        if ((fromZoneType == ZoneType.ZONE_ONE && toZoneType == ZoneType.ZONE_ONE))
            return CapLimitRepository.ZONEONE_ZONEONE_DAILY_CAP;
        if ((fromZoneType == ZoneType.ZONE_ONE && toZoneType == ZoneType.ZONE_TWO))
            return CapLimitRepository.ZONEONE_ZONETWO_DAILY_CAP;
        if ((fromZoneType == ZoneType.ZONE_TWO && toZoneType == ZoneType.ZONE_ONE))
            return CapLimitRepository.ZONETWO_ZONEONE_DAILY_CAP;
        if ((fromZoneType == ZoneType.ZONE_TWO && toZoneType == ZoneType.ZONE_TWO))
            return CapLimitRepository.ZONETWO_ZONETWO_DAILY_CAP;
        return 0;
    }

    private int getWeeklyFixedCapLimit(ZoneType fromZoneType, ZoneType toZoneType) {
        if ((fromZoneType == ZoneType.ZONE_ONE && toZoneType == ZoneType.ZONE_ONE))
            return CapLimitRepository.ZONEONE_ZONEONE_WEEKLY_CAP;
        if ((fromZoneType == ZoneType.ZONE_ONE && toZoneType == ZoneType.ZONE_TWO))
            return CapLimitRepository.ZONEONE_ZONETWO_WEEKLY_CAP;
        if ((fromZoneType == ZoneType.ZONE_TWO && toZoneType == ZoneType.ZONE_ONE))
            return CapLimitRepository.ZONETWO_ZONEONE_WEEKLY_CAP;
        if ((fromZoneType == ZoneType.ZONE_TWO && toZoneType == ZoneType.ZONE_TWO))
            return CapLimitRepository.ZONETWO_ZONETWO_WEEKLY_CAP;
        return 0;
    }
}
