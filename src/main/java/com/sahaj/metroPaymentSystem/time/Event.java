package com.sahaj.metroPaymentSystem.time;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public final class Event {
    private final Day day;
    private final Time time;
}
