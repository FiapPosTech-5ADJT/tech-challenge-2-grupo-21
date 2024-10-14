package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.dto.ParkingSlotResponseDTO;
import br.com.fiap.park_tech.model.ParkingSlot;

import java.util.List;

public interface ParkingSlotService {
    ParkingSlotResponseDTO createParkingSlot(ParkingSlotDTO parkingSlotDTO);
    void deleteParkingSlotById(String parkingSlotId);
    ParkingSlotResponseDTO getParkingSlotById(String parkingSlotId);
    ParkingSlot getParkingSlotByVehicleId(String vehicleId);
    List<ParkingSlotResponseDTO> listAllParkingSlots();
    ParkingSlot removeVehicleFromParkingSlot(String parkingSlotId);
    ParkingSlot addVehicleToParkingSlot(String parkingSlotId);
    List<ParkingSlotResponseDTO> getParkingSlotsByParkingMeterId(String parkingMeterId);

}
