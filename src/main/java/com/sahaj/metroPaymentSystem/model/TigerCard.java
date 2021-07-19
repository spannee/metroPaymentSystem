package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.calculationStrategy.FareCalculator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TigerCard {

    private static TigerCard instance;

    private List<Trip> journey;

    private FareCalculator fareCalculator;

    private TigerCard(List<Trip> journey, FareCalculator fareCalculator) {
        this.journey = journey;
        this.fareCalculator = fareCalculator;
    }

    public int getTotalFare() {
        int totalFare = fareCalculator.fareCalculation(journey);
        log.info("The total far this trip is {}", totalFare);
        return totalFare;
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

    public static void destroy() {
        instance = null;
    }

}
