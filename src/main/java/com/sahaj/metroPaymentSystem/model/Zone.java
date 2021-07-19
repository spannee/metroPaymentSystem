package com.sahaj.metroPaymentSystem.model;

import com.sahaj.metroPaymentSystem.Exceptions.TigerCardException;
import com.sahaj.metroPaymentSystem.enums.ErrorMessages;
import com.sahaj.metroPaymentSystem.enums.ZoneType;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public abstract class Zone {

    private final int id;
    private final ZoneType zoneType;

    public Zone(int id) throws TigerCardException {
        ZoneType zoneType = ZoneType.getZone(id);
        if (Objects.isNull(zoneType))
            throw new TigerCardException(ErrorMessages.INVALID_ZONE_ID.getErrorMessage());
        this.zoneType = zoneType;
        this.id = id;
    }

    public static Zone getZone(int zoneId) throws TigerCardException {
        switch (zoneId) {
            case 1:
                return new ZoneOne();
            case 2:
                return new ZoneTwo();
            default:
                throw new TigerCardException(ErrorMessages.INVALID_ZONE_ID.getErrorMessage());
        }
    }
}
