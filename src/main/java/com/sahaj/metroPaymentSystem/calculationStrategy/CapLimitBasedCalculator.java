package com.sahaj.metroPaymentSystem.calculationStrategy;

import com.sahaj.metroPaymentSystem.enums.ZoneType;
import com.sahaj.metroPaymentSystem.model.Trip;
import com.sahaj.metroPaymentSystem.repository.CapLimitRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CapLimitBasedCalculator implements FareCalculator {

    public int fareCalculation(List<Trip> journey) {
        return calculateFareForMultipleWeeks(journey);
    }

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

    public int weeklyCapLimitBasedCalculation(List<Trip> trips, int weeklyFare, boolean hasTravelledFarther) {
        int dayFare = trips.stream().mapToInt(Trip::getFare).sum();
        int weeklyFareBeforeCurrentDay = weeklyFare - dayFare;
        for (Trip trip: trips) {
            int weeklyFixedCapLimit = hasTravelledFarther ?
                    getWeeklyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_TWO) :
                    getWeeklyFixedCapLimit(trip.getFromZone().getZoneType(), trip.getToZone().getZoneType());

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

    public int calculateFareForOneDay(List<Trip> dayTrips, boolean hasTravelledFarther) {
        int oneDayFare = 0;
        for(Trip trip: dayTrips) {
            int tripFare = dailyCapLimitBasedCalculation(trip, oneDayFare, hasTravelledFarther);
            trip.setFare(tripFare);
            oneDayFare += tripFare;
        }
        return oneDayFare;
    }

    public int dailyCapLimitBasedCalculation(Trip trip, int oneDayFare, boolean hasTravelledFarther) {
        int dailyFixedCapLimit = hasTravelledFarther ?
                getDailyFixedCapLimit(ZoneType.ZONE_ONE, ZoneType.ZONE_TWO):
                getDailyFixedCapLimit(trip.getFromZone().getZoneType(), trip.getToZone().getZoneType());
        if (oneDayFare + trip.getFare() >= dailyFixedCapLimit) {
            return dailyFixedCapLimit - oneDayFare;
        }
        return trip.getFare();
    }

    private Map<Integer, List<Trip>> groupByDay(List<Trip> weeklyTripList) {
        return weeklyTripList.stream().collect(
                Collectors.groupingBy(weeklyTrip -> weeklyTrip.getEvent().getDay().getDayId(),
                        LinkedHashMap::new, Collectors.toList()));
    }

    private boolean hasTravelledFurther(Map<Integer, List<Trip>> weeklyTrips) {
        return weeklyTrips.entrySet().stream().anyMatch(integerListEntry -> integerListEntry.getValue().stream().anyMatch(trip -> (trip.getFromZone().getZoneType() == ZoneType.ZONE_ONE &&
                trip.getToZone().getZoneType() == ZoneType.ZONE_TWO) || (trip.getFromZone().getZoneType() == ZoneType.ZONE_TWO &&
                trip.getToZone().getZoneType() == ZoneType.ZONE_ONE)));
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
