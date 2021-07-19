package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import com.sahaj.metroPaymentSystem.enums.ZoneType;

public class ZoneTwo extends Zone {
    public ZoneTwo() throws TigerCardException {
        super(ZoneType.ZONE_TWO.getId());
    }
}
