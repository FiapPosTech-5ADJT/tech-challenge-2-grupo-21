package br.com.fiap.park_tech.exception;

import br.com.fiap.park_tech.DomainException;

public class VehiclerNotFoundException extends DomainException {
    private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String VEHICLE_WITH_PARAM_D_NOT_FOUND = "Vehicle with parameter %d not found";
    private static final String VEHICLE_NOT_FOUND = "Vehicle not found";

    public VehiclerNotFoundException(String id) {
        super(VEHICLE_NOT_FOUND, String.format(VEHICLE_WITH_PARAM_D_NOT_FOUND, id), HTTP_STATUS_NOT_FOUND);
    }
}
