package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.ParkingMeterDTO;
import br.com.fiap.park_tech.dto.ParkingMeterResponseDTO;
import br.com.fiap.park_tech.mapper.ParkingMeterMapper;
import br.com.fiap.park_tech.model.ParkingMeter;
import br.com.fiap.park_tech.repository.ParkingMeterRepository;
import br.com.fiap.park_tech.exception.ParkingMeterNotFoundException;
import br.com.fiap.park_tech.service.ParkingMeterService;
import br.com.fiap.park_tech.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingMeterServiceImpl implements ParkingMeterService {

    private final ParkingMeterRepository parkingMeterRepository;

    private final ParkingSlotService parkingSlotService;
    private final ParkingMeterMapper parkingMeterMapper;

    @Override
    public ParkingMeterResponseDTO createParkingMeter(final ParkingMeterDTO parkingMeterDTO) {
        ParkingMeter parkingMeter = ParkingMeter.newParkingMeter(parkingMeterDTO.getName());
        ParkingMeter parkingMeterSaved  = parkingMeterRepository.save(parkingMeter);
        return parkingMeterMapper.toResponseDTO(parkingMeter);
    }

    @Override
    public ParkingMeterResponseDTO getParkingMeterById(final String id) {
          ParkingMeter parkingMeter = parkingMeterRepository.findById(id).orElseThrow(() -> new ParkingMeterNotFoundException(String.valueOf(id)));
          return parkingMeterMapper.toResponseDTO(parkingMeter);

    }

  @Override
  public ParkingMeterResponseDTO getParkingMeterByName(String name) {
      ParkingMeter parkingMeter = parkingMeterRepository.findByName(name);
      if (parkingMeter != null) {
          return parkingMeterMapper.toResponseDTO(parkingMeter);
      }
      throw new ParkingMeterNotFoundException(name);
  }

  @Override
  public List<ParkingMeterResponseDTO> getAllParkingMeters() {
    return parkingMeterRepository.findAll().stream()
      .map(parkingMeterMapper::toResponseDTO)
      .collect(Collectors.toList());
  }

    @Override
    public void deleteParkingMeterById(final String id) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(id).orElseThrow(() -> new ParkingMeterNotFoundException(String.valueOf(id)));
        if (parkingMeter.getDeletedAt() == null) {
            parkingMeter.setDeletedAt(LocalDateTime.now());
            parkingMeterRepository.deleteById(parkingMeter.getId());
        }
    }
}
