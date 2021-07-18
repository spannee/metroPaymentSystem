package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.time.Day;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class TigerCard {

    @Setter
    @Getter
    private Map<Day, Trip> journey;

    private int totalFare;

    public TigerCard(List<Trip> journey) {
    }

    public int getTotalFare() {
        return totalFare;
    }

}
