package br.com.fiap.park_tech.exception;

public class VehiclePaymentNotFoundException extends NotFoundException {
    private static final String PARKINGPAYMENT_WITH_PARAM_D_NOT_FOUND = "ParkingPayment with parameter %d not found";

    public VehiclePaymentNotFoundException(String id) {
        super(String.format(PARKINGPAYMENT_WITH_PARAM_D_NOT_FOUND, id));
    }
}
