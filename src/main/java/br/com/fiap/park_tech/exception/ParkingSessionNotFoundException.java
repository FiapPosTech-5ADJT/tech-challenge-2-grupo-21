package br.com.fiap.park_tech.exception;

public class ParkingSessionNotFoundException extends NotFoundException {
    private static final String PARKINGSESSION_WITH_PARAM_D_NOT_FOUND = "ParkingSession with parameter %d not found";
    public ParkingSessionNotFoundException(String id) {
        super(String.format(PARKINGSESSION_WITH_PARAM_D_NOT_FOUND, id));
    }
}
