package br.com.fiap.park_tech.exception;

import br.com.fiap.park_tech.DomainException;

public class ParkingMeterNotFoundException extends DomainException {

    private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String PARKINGMETER_WITH_PARAM_D_NOT_FOUND = "Parkingmeter with parameter %d not found";
    private static final String PARKINGMETER_NOT_FOUND = "Parkingmeter not found";

    public ParkingMeterNotFoundException(String id) {
        super(PARKINGMETER_NOT_FOUND, String.format(PARKINGMETER_WITH_PARAM_D_NOT_FOUND, id), HTTP_STATUS_NOT_FOUND);
    }
}
