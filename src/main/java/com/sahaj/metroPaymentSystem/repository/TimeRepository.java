package com.sahaj.metroPaymentSystem.repository;

import java.time.LocalTime;

public class TimeRepository {

    public static final LocalTime weekdayMorningPeakStart = LocalTime.of(7, 00);

    public static final LocalTime weekdayMorningPeakEnd = LocalTime.of(10, 30);

    public static final LocalTime weekdayEveningPeakStart = LocalTime.of(17, 00);

    public static final LocalTime weekdayEveningPeakEnd = LocalTime.of(20, 00);

    public static final LocalTime weekendMorningPeakStart = LocalTime.of(9, 00);

    public static final LocalTime weekendMorningPeakEnd = LocalTime.of(11, 00);

    public static final LocalTime weekendEveningPeakStart = LocalTime.of(18, 00);

    public static final LocalTime weekendEveningPeakEnd = LocalTime.of(22, 00);

}
