package com.sahaj.metroPaymentSystem.time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeTest {

    private Time time;

    @BeforeEach
    void setUp() {
    }

    @Test
    void isPeakHourWeekday() {
        time = new Time(LocalTime.of(8, 15));
        assertTrue(time.isPeakHour(false));

        time = new Time(LocalTime.of(18, 15));
        assertTrue(time.isPeakHour(false));

        time = new Time(LocalTime.of(11, 30));
        assertFalse(time.isPeakHour(false));

        time = new Time(LocalTime.of(21, 30));
        assertFalse(time.isPeakHour(false));
    }

    @Test
    void isPeakHourWeekend() {
        time = new Time(LocalTime.of(10, 15));
        assertTrue(time.isPeakHour(true));

        time = new Time(LocalTime.of(19, 15));
        assertTrue(time.isPeakHour(true));

        time = new Time(LocalTime.of(12, 00));
        assertFalse(time.isPeakHour(true));

        time = new Time(LocalTime.of(23, 45));
        assertFalse(time.isPeakHour(true));
    }

    @AfterEach
    void tearDown() {
    }
}