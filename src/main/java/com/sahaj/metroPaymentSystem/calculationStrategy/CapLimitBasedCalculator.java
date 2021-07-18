package com.sahaj.metroPaymentSystem.calculationStrategy;

import com.sahaj.metroPaymentSystem.enums.ZoneType;
import com.sahaj.metroPaymentSystem.model.Trip;
import com.sahaj.metroPaymentSystem.model.Zone;
import com.sahaj.metroPaymentSystem.repository.CapLimitRepository;

import java.util.List;

public class CapLimitBasedCalculator implements FareCalculator {

    public int fareCalculation(List<Trip> journey) {
        int totalFare = 0;
        boolean isDailyJourney = true;
        Trip firstTrip = journey.get(0);
        journey.forEach(trip -> {
            if (isDailyJourney && trip.getEvent().getDay() == firstTrip.getEvent().getDay()) {
            }

        });
        return 0;
    }

    private int getDailyFixedCapLimit(Zone fromZone, Zone toZone) {
        if ((fromZone.getZoneType() == ZoneType.ZONE_ONE && toZone.getZoneType() == ZoneType.ZONE_ONE))
            return CapLimitRepository.ZONEONE_ZONEONE_DAILY_CAP;
        if ((fromZone.getZoneType() == ZoneType.ZONE_ONE && toZone.getZoneType() == ZoneType.ZONE_TWO))
            return CapLimitRepository.ZONEONE_ZONETWO_DAILY_CAP;
        if ((fromZone.getZoneType() == ZoneType.ZONE_TWO && toZone.getZoneType() == ZoneType.ZONE_ONE))
            return CapLimitRepository.ZONETWO_ZONEONE_DAILY_CAP;
        if ((fromZone.getZoneType() == ZoneType.ZONE_TWO && toZone.getZoneType() == ZoneType.ZONE_TWO))
            return CapLimitRepository.ZONETWO_ZONETWO_DAILY_CAP;
        return 0;
    }

    private int getWeeklyFixedCapLimit(Zone fromZone, Zone toZone) {
        if ((fromZone.getZoneType() == ZoneType.ZONE_ONE && toZone.getZoneType() == ZoneType.ZONE_ONE))
            return CapLimitRepository.ZONEONE_ZONEONE_WEEKLY_CAP;
        if ((fromZone.getZoneType() == ZoneType.ZONE_ONE && toZone.getZoneType() == ZoneType.ZONE_TWO))
            return CapLimitRepository.ZONEONE_ZONETWO_WEEKLY_CAP;
        if ((fromZone.getZoneType() == ZoneType.ZONE_TWO && toZone.getZoneType() == ZoneType.ZONE_ONE))
            return CapLimitRepository.ZONETWO_ZONEONE_WEEKLY_CAP;
        if ((fromZone.getZoneType() == ZoneType.ZONE_TWO && toZone.getZoneType() == ZoneType.ZONE_TWO))
            return CapLimitRepository.ZONETWO_ZONETWO_WEEKLY_CAP;
        return 0;
    }
}
