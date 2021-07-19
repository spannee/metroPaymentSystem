package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.enums.ZoneType;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class Zone {

    private final int id;
    private final ZoneType zoneType;

    public Zone(int id) throws Exception {
        ZoneType zoneType = ZoneType.getZone(id);
        if (Objects.isNull(zoneType))
            throw new Exception("Test");
        this.zoneType = zoneType;
        this.id = id;
    }

    public static Zone getZone(int zoneId) throws Exception {
        switch (zoneId) {
            case 1:
                return new ZoneOne();
            case 2:
                return new ZoneTwo();
            default:
                throw new Exception("Test");
        }
    }
}
