package com.sahaj.metroPaymentSystem.time;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import com.sahaj.metroPaymentSystem.enums.ErrorMessages;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
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

    @NonNull
    public Day(String day) throws TigerCardException {
        int dayId = getDayValue(day);
        this.dayId = dayId;
    }

    public boolean isWeekend() {
        return this.dayId == 6 || this.dayId == 7;
    }

    private int getDayValue(String day) throws TigerCardException {
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
                throw new TigerCardException(ErrorMessages.INVALID_DAY.getErrorMessage());
        }
    }

}
