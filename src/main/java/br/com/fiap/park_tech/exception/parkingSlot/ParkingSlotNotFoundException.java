package br.com.fiap.park_tech.exception.parkingSlot;

import br.com.fiap.park_tech.exception.NotFoundException;

public class ParkingSlotNotFoundException extends NotFoundException {

  public ParkingSlotNotFoundException(String param) {
    super(String.format("Parking slot not found to param %s", param));
  }
}
