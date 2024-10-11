package br.com.fiap.park_tech.exception;

import br.com.fiap.park_tech.DomainException;

public class ParkingSessionNotFoundException extends DomainException {

    private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String PARKINGSESSION_WITH_PARAM_D_NOT_FOUND = "ParkingSession with parameter %d not found";
    private static final String PARKINGSESSION_NOT_FOUND = "ParkingSession not found";

    public ParkingSessionNotFoundException(String id) {
        super(PARKINGSESSION_NOT_FOUND, String.format(PARKINGSESSION_WITH_PARAM_D_NOT_FOUND, id), HTTP_STATUS_NOT_FOUND);
    }
}
