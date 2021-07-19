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
    private final int dayId;

    public Day(String day) throws Exception {
        int dayId = getDayValue(day);
        if (dayId == -1)
            throw new Exception("Test");
        this.dayId = dayId;
    }

    public boolean isWeekend() {
        return this.dayId == 6 || this.dayId == 7;
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
