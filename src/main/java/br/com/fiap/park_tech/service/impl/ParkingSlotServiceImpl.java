package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.model.ParkingSlot;
import br.com.fiap.park_tech.repository.ParkingSlotRepository;
import br.com.fiap.park_tech.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    @Override
    public ParkingSlot createParkingSlot(ParkingSlot parkingSlot) {
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
        parkingSlot.setVehicle(null);
        return parkingSlotRepository.save(parkingSlot);
    }
}
