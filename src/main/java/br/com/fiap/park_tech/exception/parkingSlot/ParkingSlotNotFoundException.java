package br.com.fiap.park_tech.exception.parkingSlot;

import br.com.fiap.park_tech.DomainException;

public class ParkingSlotNotFoundException extends DomainException {

    private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String PARKINGSLOT_WITH_PARAM_D_NOT_FOUND = "ParkingSlot with parameter %d already occupied";
    private static final String PARKINGSLOT_NOT_FOUND = "ParkingSlot already occupied";

    public ParkingSlotNotFoundException(String id) {
        super(PARKINGSLOT_NOT_FOUND, String.format(PARKINGSLOT_WITH_PARAM_D_NOT_FOUND, id), HTTP_STATUS_NOT_FOUND);
    }

}
