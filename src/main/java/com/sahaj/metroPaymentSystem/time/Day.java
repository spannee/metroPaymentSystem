package com.sahaj.metroPaymentSystem.time;

import lombok.Getter;
import lombok.NonNull;

public class Day {

    public final static int MONDAY = 1;

    public final static int TUESDAY = 2;

    public final static int WEDNESDAY = 3;

    public final static int THURSDAY = 4;

    public final static int FRIDAY = 5;

    public final static int SATURDAY = 6;

    public final static int SUNDAY = 7;

    @Getter
    private final int day;

    @NonNull
    public Day(String day) throws Exception {
        int dayVal = getDayValue(day);
        if (dayVal == -1)
            throw new Exception("Test");
        this.day = dayVal;
    }

    public boolean isWeekend() {
        return this.day == 1 || this.day == 2;
    }

    @NonNull
    private int getDayValue(String day) {
        switch(day) {
            case "Monday":
                return MONDAY;
            case "Tuesday":
                return TUESDAY;
            case "Wednesday":
                return WEDNESDAY;
            case "Thursday":
                return THURSDAY;
            case "Friday":
                return FRIDAY;
            case "Saturday":
                return SATURDAY;
            case "Sunday":
                return SUNDAY;
            default:
                return -1;
        }
    }

}
