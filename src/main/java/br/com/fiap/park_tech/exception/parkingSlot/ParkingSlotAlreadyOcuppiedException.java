package br.com.fiap.park_tech.exception.parkingSlot;

import br.com.fiap.park_tech.exception.NotFoundException;

public class ParkingSlotAlreadyOcuppiedException extends RuntimeException {

    private static final int HTTP_STATUS_CONFLICT = 409;
    private static final String PARKINGSLOT_WITH_PARAM_D_ALREADY_OCUPPIED = "ParkingSlot with parameter %d not found";
    private static final String PARKINGSLOT_ALREADY_OCUPPIED = "ParkingSlot not found";

    public ParkingSlotAlreadyOcuppiedException(String id) {
        super(String.format(PARKINGSLOT_WITH_PARAM_D_ALREADY_OCUPPIED, id));
    }
}
