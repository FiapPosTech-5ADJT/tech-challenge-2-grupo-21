package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.model.ParkingSlot;

public interface ParkingSlotService {
    ParkingSlot createParkingSlot(ParkingSlotDTO parkingSlotDTO);
    void deleteParkingSlotById(Long parkingSlotId);
    ParkingSlot getParkingSlotById(Long parkingSlotId);
    ParkingSlot getParkingSlotByVehicleId(Long vehicleId);
    ParkingSlot removeVehicleFromParkingSlot(Long parkingSlotId);


}
