package br.com.fiap.park_tech.exception.parkingSlot;

import br.com.fiap.park_tech.exception.NotFoundException;

public class ParkingSlotAlreadyOcuppiedException extends RuntimeException {

    private static final String PARKINGSLOT_WITH_PARAM_D_ALREADY_OCUPPIED = "ParkingSlot with parameter %s not found";

    public ParkingSlotAlreadyOcuppiedException(String id) {
        super(String.format(PARKINGSLOT_WITH_PARAM_D_ALREADY_OCUPPIED, id));
    }
}
