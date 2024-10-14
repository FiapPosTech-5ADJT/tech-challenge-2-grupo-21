package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.dto.ParkingSlotResponseDTO;
import br.com.fiap.park_tech.exception.ParkingMeterNotFoundException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotNotFoundException;
import br.com.fiap.park_tech.mapper.ParkingMeterMapper;
import br.com.fiap.park_tech.mapper.ParkingSlotMapper;
import br.com.fiap.park_tech.model.ParkingMeter;
import br.com.fiap.park_tech.model.ParkingSlot;
import br.com.fiap.park_tech.repository.ParkingMeterRepository;
import br.com.fiap.park_tech.repository.ParkingSlotRepository;
import br.com.fiap.park_tech.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkingMeterRepository  parkingMeterRepository;
    private final ParkingSlotMapper parkingSlotMapper;

    @Override
    public ParkingSlotResponseDTO createParkingSlot(ParkingSlotDTO parkingSlotDTO) {
      ParkingMeter parkingMeter = parkingMeterRepository.findById(parkingSlotDTO.getParkingMeterId())
        .orElseThrow(() -> new RuntimeException("Parking Meter not found"));
      ParkingSlot parkingSlot = ParkingSlot.newParkingSlot(parkingSlotDTO, parkingMeter);
      ParkingSlot savedParkingSlot = parkingSlotRepository.save(parkingSlot);
      return parkingSlotMapper.toResponseDTO(savedParkingSlot);
  }
    @Override
    public void deleteParkingSlotById(String parkingSlotId) {
      ParkingSlot parkingSlot = parkingSlotRepository.findById(parkingSlotId).orElseThrow(() -> new ParkingSlotNotFoundException(String.valueOf(parkingSlotId)));
      if (parkingSlot.getDeletedAt() == null) {
        parkingSlot.setDeletedAt(LocalDateTime.now());
        parkingSlotRepository.deleteById(parkingSlot.getId());
      }
    }

    @Override
    public ParkingSlotResponseDTO getParkingSlotById(String parkingSlotId) {
        ParkingSlot parkingSlot = parkingSlotRepository.findById(parkingSlotId)
                .orElseThrow(() -> new RuntimeException("Parking slot not found"));
        return parkingSlotMapper.toResponseDTO(parkingSlot);
    }

    @Override
    public ParkingSlot getParkingSlotByVehicleId(String vehicleId) {
        //return parkingSlotRepository.findByVehicleId(vehicleId).orElseThrow(() -> new RuntimeException("Parking slot not found for vehicle"));
      return  null;
    }

  @Override
  public List<ParkingSlotResponseDTO> listAllParkingSlots() {
    return parkingSlotRepository.findAll().stream()
      .map(parkingSlotMapper::toResponseDTO)
      .collect(Collectors.toList());
  }

  @Override
    public ParkingSlot removeVehicleFromParkingSlot(String parkingSlotId) {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setAvailable(true);
        return parkingSlotRepository.save(parkingSlot);
    }

    @Override
    public ParkingSlot addVehicleToParkingSlot(String parkingSlotId) {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setAvailable(false);
        return parkingSlotRepository.save(parkingSlot);
    }

  @Override
  public List<ParkingSlotResponseDTO> getParkingSlotsByParkingMeterId(String parkingMeterId) {
    return parkingSlotRepository.findByParkingMeterId(parkingMeterId).stream()
      .map(parkingSlotMapper::toResponseDTO)
      .collect(Collectors.toList());
  }

}
