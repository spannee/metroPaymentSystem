package com.sahaj.metroPaymentSystem.time;

import com.sahaj.metroPaymentSystem.repository.TimeRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor(access=AccessLevel.PUBLIC)
public class Time {

    @Getter
    private final LocalTime time;

    public boolean isPeakHour(boolean isWeekend) {
        if (isWeekend) {
            return (time.isAfter(TimeRepository.weekendMorningPeakStart) &&
                    time.isBefore(TimeRepository.weekendMorningPeakEnd)) ||
                    ((time.isAfter(TimeRepository.weekendEveningPeakStart) &&
                            time.isBefore(TimeRepository.weekendEveningPeakEnd)));
        } else {
            return (time.isAfter(TimeRepository.weekdayMorningPeakStart) &&
                    time.isBefore(TimeRepository.weekdayMorningPeakEnd)) ||
                    ((time.isAfter(TimeRepository.weekdayEveningPeakStart) &&
                            time.isBefore(TimeRepository.weekdayEveningPeakEnd)));

        }
    }

}
