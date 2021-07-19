package com.sahaj.metroPaymentSystem.time;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
public final class Event {
    private final Day day;
    private final Time time;
}
