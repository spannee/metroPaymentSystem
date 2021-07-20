package com.sahaj.metroPaymentSystem.time;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {

    private Day day;

    @BeforeEach
    void setUp() {
    }

    @Test
    void isWeekend() throws TigerCardException {
        day = new Day("Sunday");
        assertTrue(day.isWeekend());

        day = new Day("Saturday");
        assertTrue(day.isWeekend());
    }

    @Test
    void isNotWeekend() throws TigerCardException {
        day = new Day("Monday");
        assertFalse(day.isWeekend());

        day = new Day("Wednesday");
        assertFalse(day.isWeekend());

        day = new Day("Thursday");
        assertFalse(day.isWeekend());
    }

    @Test
    void getDayValue() throws TigerCardException {
        assertEquals(Day.getDayValue("Monday"), Day.MONDAY);
        assertEquals(Day.getDayValue("Saturday"), Day.SATURDAY);
    }

    @Test
    void getInvalidDayValue() {
        Assertions.assertThrows(TigerCardException.class, () -> {
            Day.getDayValue("InvalidDay");
        });
    }

    @AfterEach
    void tearDown() {
    }
}