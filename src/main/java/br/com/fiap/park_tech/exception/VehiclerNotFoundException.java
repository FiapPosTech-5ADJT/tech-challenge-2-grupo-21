package br.com.fiap.park_tech.exception;

public class VehiclerNotFoundException extends NotFoundException {
    public VehiclerNotFoundException(String identifier) {
        super("Vehicle not found with identifier: " + identifier);
    }
}
