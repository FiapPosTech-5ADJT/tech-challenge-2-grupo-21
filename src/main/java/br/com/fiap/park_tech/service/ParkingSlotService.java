package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.dto.ParkingSlotResponseDTO;
import br.com.fiap.park_tech.model.ParkingSlot;

import java.util.List;

public interface ParkingSlotService {
    ParkingSlotResponseDTO createParkingSlot(ParkingSlotDTO parkingSlotDTO);
    void deleteParkingSlotById(String parkingSlotId);
    ParkingSlotResponseDTO getParkingSlotById(String parkingSlotId);
    List<ParkingSlotResponseDTO> listAllParkingSlots();
    List<ParkingSlotResponseDTO> getParkingSlotsByParkingMeterId(String parkingMeterId);

}
