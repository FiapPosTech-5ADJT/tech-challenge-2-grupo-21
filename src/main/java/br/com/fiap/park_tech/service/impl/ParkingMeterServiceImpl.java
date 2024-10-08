package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.model.ParkingMeter;
import br.com.fiap.park_tech.repository.ParkingMeterRepository;
import br.com.fiap.park_tech.exception.ParkingMeterNotFoundException;
import br.com.fiap.park_tech.service.ParkingMeterService;
import br.com.fiap.park_tech.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingMeterServiceImpl implements ParkingMeterService {

    private final ParkingMeterRepository parkingMeterRepository;

    private final ParkingSlotService parkingSlotService;

    @Override
    public ParkingMeter createParkingMeter(final ParkingMeter parkingMeter) {
        parkingMeter.setCreatedAt(Instant.now());
        return parkingMeterRepository.save(parkingMeter);
    }

    @Override
    public Optional<ParkingMeter> getParkingMeterById(final Long id) {
        return parkingMeterRepository.findById(id);
    }

    @Override
    public Optional<ParkingMeter> getParkingMeterByName(final String name) {
        // Implement the logic to find by name if needed
        return Optional.empty();
    }

    @Override
    public void deleteParkingMeterById(final Long id) {
        var parkingMeter = getParkingMeterById(id).orElseThrow(() -> new ParkingMeterNotFoundException(String.valueOf(id)));
        if (parkingMeter.getDeletedAt() == null) {
            parkingMeter.setDeletedAt(Instant.now());
            parkingMeterRepository.save(parkingMeter);
        }
    }

    @Override
    public ParkingMeter addParkingSlotToParkingMeter(final ParkingMeter parkingMeter) {
        var parkingSlot = parkingSlotService.getParkingSlotById(parkingMeter.getId());
        parkingMeter.setUpdatedAt(Instant.now());
        parkingSlot.setUpdatedAt(Instant.now());
        parkingSlot.setParkingMeter(parkingMeter);
        parkingMeter.getParkingSlots().add(parkingSlot);
        return parkingMeterRepository.save(parkingMeter);
    }

    @Override
    public ParkingMeter removeParkingSlotFromParkingMeter(final ParkingMeter parkingMeter) {
        var parkingSlot = parkingSlotService.getParkingSlotById(parkingMeter.getId());
        parkingMeter.setUpdatedAt(Instant.now());
        parkingSlot.setUpdatedAt(Instant.now());
        parkingSlot.setParkingMeter(null);
        parkingMeter.getParkingSlots().remove(parkingSlot);
        return parkingMeterRepository.save(parkingMeter);
    }
}
