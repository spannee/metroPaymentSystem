package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import com.sahaj.metroPaymentSystem.enums.ZoneType;

public class ZoneOne extends Zone {
    public ZoneOne() throws TigerCardException {
        super(ZoneType.ZONE_ONE.getId());
    }
}
