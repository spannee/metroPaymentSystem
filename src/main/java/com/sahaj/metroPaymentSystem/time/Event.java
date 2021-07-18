package com.sahaj.metroPaymentSystem.time;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class Event {
    private final Day day;
    private final Time time;
}
