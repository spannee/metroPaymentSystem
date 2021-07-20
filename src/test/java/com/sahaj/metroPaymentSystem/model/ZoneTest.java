package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZoneTest {

    private Zone zone;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getZone() throws TigerCardException {
        zone = Zone.getZone(1);
        assertEquals(zone.getId(), 1);

        zone = Zone.getZone(2);
        assertEquals(zone.getId(), 2);
    }

    @Test
    void getZoneInvalidInput() {
        Assertions.assertThrows(TigerCardException.class, () -> {
            Zone zone = Zone.getZone(3);
        });
    }

    @AfterEach
    void tearDown() {
        zone = null;
    }

}