package br.com.fiap.park_tech.exception.parkingSlot;

import br.com.fiap.park_tech.DomainException;

public class ParkingSlotNotFoundException extends RuntimeException {

  public ParkingSlotNotFoundException(String param) {
    super(String.format("Parking slot not found to param %s", param)); // Corrigido para %s
  }
}
