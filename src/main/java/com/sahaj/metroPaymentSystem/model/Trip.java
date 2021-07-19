package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.enums.ZoneType;
import com.sahaj.metroPaymentSystem.repository.FareRepository;
import com.sahaj.metroPaymentSystem.time.Day;
import com.sahaj.metroPaymentSystem.time.Event;
import com.sahaj.metroPaymentSystem.time.Time;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Objects;

@Getter
public class Trip {

    private Event event;
    private Zone fromZone;
    private Zone toZone;
    private boolean isNewWeek;

    @Setter
    private int fare;

    private Trip(Event event, Zone fromZone, Zone toZone, boolean isNewWeek) throws Exception {
        if (Objects.isNull(event) || Objects.isNull(fromZone) || Objects.isNull(toZone))
            throw new Exception("Test");
        this.event = event;
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.fare = getFixedFare(event, fromZone, toZone);
        this.isNewWeek = isNewWeek;
    }

    public static Trip addTrip(String dayStr, int hour, int minute, int fromZoneId, int toZoneId, boolean isNewWeek) {
        Trip trip = null;
        try {
            Day day = new Day(dayStr);
            Time time = new Time(LocalTime.of(hour, minute));
            Event event = new Event(day, time);

            trip = new Trip(event, Zone.getZone(fromZoneId), Zone.getZone(toZoneId), isNewWeek);
        } catch (Exception e) {

        }
        return trip;
    }

    private int getFixedFare(Event event, Zone fromZone, Zone toZone) {
        boolean isPeakHour = event.getTime().isPeakHour(event.getDay().isWeekend());
        if ((fromZone.getZoneType() == ZoneType.ZONE_ONE && toZone.getZoneType() == ZoneType.ZONE_ONE))
            return isPeakHour ? FareRepository.ZONEONE_ZONEONE_PEAK_HOURS : FareRepository.ZONEONE_ZONEONE_OFFPEAK_HOURS;
        if ((fromZone.getZoneType() == ZoneType.ZONE_ONE && toZone.getZoneType() == ZoneType.ZONE_TWO))
            return isPeakHour ? FareRepository.ZONEONE_ZONETWO_PEAK_HOURS : FareRepository.ZONEONE_ZONETWO_OFFPEAK_HOURS;
        if ((fromZone.getZoneType() == ZoneType.ZONE_TWO && toZone.getZoneType() == ZoneType.ZONE_ONE))
            return isPeakHour ?  FareRepository.ZONETWO_ZONEONE_PEAK_HOURS : FareRepository.ZONETWO_ZONEONE_OFFPEAK_HOURS;
        if ((fromZone.getZoneType() == ZoneType.ZONE_TWO && toZone.getZoneType() == ZoneType.ZONE_TWO))
            return isPeakHour ? FareRepository.ZONETWO_ZONETWO_PEAK_HOURS : FareRepository.ZONETWO_ZONETWO_OFFPEAK_HOURS;
        return 0;
    }
}
