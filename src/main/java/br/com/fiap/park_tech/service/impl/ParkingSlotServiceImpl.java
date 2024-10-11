package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.model.ParkingMeter;
import br.com.fiap.park_tech.model.ParkingSlot;
import br.com.fiap.park_tech.repository.ParkingMeterRepository;
import br.com.fiap.park_tech.repository.ParkingSlotRepository;
import br.com.fiap.park_tech.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkingMeterRepository  parkingMeterRepository;

    @Override
    public ParkingSlot createParkingSlot(ParkingSlotDTO parkingSlotDTO) {
      ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingSlotDTO.getParkingMeterId())
        .orElseThrow(() -> new RuntimeException("Parking Meter not found"));
      ParkingSlot parkingSlot = ParkingSlot.newParkingSlot(parkingSlotDTO, parkingMeter);
      return parkingSlotRepository.save(parkingSlot);
    }

    @Override
    public void deleteParkingSlotById(Long parkingSlotId) {
        parkingSlotRepository.deleteById(parkingSlotId);
    }

    @Override
    public ParkingSlot getParkingSlotById(Long parkingSlotId) {
        return parkingSlotRepository.findById(parkingSlotId)
                .orElseThrow(() -> new RuntimeException("Parking slot not found"));
    }

    @Override
    public ParkingSlot getParkingSlotByVehicleId(Long vehicleId) {
        //return parkingSlotRepository.findByVehicleId(vehicleId).orElseThrow(() -> new RuntimeException("Parking slot not found for vehicle"));
      return  null;
    }

    @Override
    public ParkingSlot removeVehicleFromParkingSlot(Long parkingSlotId) {
        ParkingSlot parkingSlot = getParkingSlotById(parkingSlotId);
        return parkingSlotRepository.save(parkingSlot);
    }
}
