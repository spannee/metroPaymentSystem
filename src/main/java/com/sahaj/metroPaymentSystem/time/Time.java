package com.sahaj.metroPaymentSystem.time;

import com.sahaj.metroPaymentSystem.repository.TimeRepository;
import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class Time {

    @Getter
    @NonNull
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
