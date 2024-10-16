package br.com.fiap.park_tech.exception;

public class ParkingMeterNotFoundException extends NotFoundException {
    public ParkingMeterNotFoundException(String param) {
        super(String.format("Parking meter not found to param %s", param)); // Corrigido para %s
    }
}
