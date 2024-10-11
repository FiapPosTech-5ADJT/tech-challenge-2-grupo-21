package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.model.ParkingSlot;

public interface ParkingSlotService {
    ParkingSlot createParkingSlot(ParkingSlotDTO parkingSlotDTO);
    void deleteParkingSlotById(String parkingSlotId);
    ParkingSlot getParkingSlotById(String parkingSlotId);
    ParkingSlot getParkingSlotByVehicleId(String vehicleId);
    ParkingSlot removeVehicleFromParkingSlot(String parkingSlotId);
    ParkingSlot addVehicleToParkingSlot(String parkingSlotId);
}
