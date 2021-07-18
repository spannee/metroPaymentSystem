package com.sahaj.metroPaymentSystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ZoneType {

    ZONE_ONE(1),
    ZONE_TWO(2);

    @Getter
    private final int id;

    public static ZoneType getZone(int zoneId) {
        for (ZoneType zone: ZoneType.values()) {
            if(zone.id == zoneId)
                return zone;
        }
        return null;
    }
}
