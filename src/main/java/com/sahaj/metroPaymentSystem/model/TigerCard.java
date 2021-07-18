package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.calculationStrategy.FareCalculator;

import java.util.List;

public class TigerCard {

    private static TigerCard instance;

    private List<Trip> journey;

    private FareCalculator fareCalculator;

    private TigerCard(List<Trip> journey, FareCalculator fareCalculator) {
        this.journey = journey;
        this.fareCalculator = fareCalculator;
    }

    public int getTotalFare() {
        return fareCalculator.fareCalculation(journey);
    }

    public static TigerCard getInstance(List<Trip> journey, FareCalculator fareCalculator) {
        if (instance == null) {
            synchronized (TigerCard.class) {
                if (instance == null) {
                    instance = new TigerCard(journey, fareCalculator);
                }
            }
        }
        return instance;
    }

}
