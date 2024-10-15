package br.com.fiap.park_tech.exception;

import br.com.fiap.park_tech.DomainException;

public class VehiclePaymentNotFoundException extends DomainException {

    private static final int HTTP_STATUS_NOT_FOUND = 404;
    private static final String PARKINGPAYMENT_WITH_PARAM_D_NOT_FOUND = "ParkingPayment with parameter %d not found";
    private static final String PARKINGPAYMENT_NOT_FOUND = "ParkingPayment not found";

    public VehiclePaymentNotFoundException(String id) {
        super(PARKINGPAYMENT_NOT_FOUND, String.format(PARKINGPAYMENT_WITH_PARAM_D_NOT_FOUND, id), HTTP_STATUS_NOT_FOUND);
    }
}
