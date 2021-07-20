package com.sahaj.metroPaymentSystem.calculationStrategy;

import com.sahaj.metroPaymentSystem.model.Trip;

import java.util.List;

public interface FareCalculator {

    /**
     * Fare calculator
     * @param journey
     * @return
     */
    int fareCalculation(List<Trip> journey) ;
}
