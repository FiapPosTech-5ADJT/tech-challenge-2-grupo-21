package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.ParkingMeterDTO;
import br.com.fiap.park_tech.dto.ParkingMeterResponseDTO;
import br.com.fiap.park_tech.exception.ParkingMeterNotFoundException;
import br.com.fiap.park_tech.mapper.ParkingMeterMapper;
import br.com.fiap.park_tech.model.ParkingMeter;
import br.com.fiap.park_tech.repository.ParkingMeterRepository;
import br.com.fiap.park_tech.service.ParkingMeterService;
import br.com.fiap.park_tech.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingMeterServiceImpl implements ParkingMeterService {

    private static final double PARKING_METER_COST_FIST_HOUR = 7.0;
    private static final double PARKING_METER_COST_ADDITIONAL_HOUR = 3.0;

    private final ParkingMeterRepository parkingMeterRepository;
    private final ParkingMeterMapper parkingMeterMapper;

    @Override
    @CachePut(value = "parkingMeters", key = "#result.id")
    public ParkingMeterResponseDTO createParkingMeter(final ParkingMeterDTO parkingMeterDTO) {
        ParkingMeter parkingMeter = ParkingMeter.newParkingMeter(parkingMeterDTO.getName());
        ParkingMeter parkingMeterSaved = parkingMeterRepository.save(parkingMeter);
        return parkingMeterMapper.toResponseDTO(parkingMeterSaved);
    }

    @Override
    @Cacheable(value = "parkingMeters", key = "#id")
    public ParkingMeterResponseDTO getParkingMeterById(final String id) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(id).orElseThrow(() -> new ParkingMeterNotFoundException(String.valueOf(id)));
        return parkingMeterMapper.toResponseDTO(parkingMeter);

    }

    @Override
    @Cacheable(value = "parkingMeters", key = "#name")
    public ParkingMeterResponseDTO getParkingMeterByName(String name) {
        ParkingMeter parkingMeter = parkingMeterRepository.findByName(name);
        if (parkingMeter != null) {
            return parkingMeterMapper.toResponseDTO(parkingMeter);
        }
        throw new ParkingMeterNotFoundException(name);
    }

    @Override
    @Cacheable(value = "parkingMeters")
    public List<ParkingMeterResponseDTO> getAllParkingMeters() {
        return parkingMeterRepository.findAll().stream()
                .map(parkingMeterMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "parkingMeters", key = "#id")
    public void deleteParkingMeterById(final String id) {
        ParkingMeter parkingMeter = parkingMeterRepository.findById(id).orElseThrow(() -> new ParkingMeterNotFoundException(String.valueOf(id)));
        if (parkingMeter.getDeletedAt() == null) {
            parkingMeter.setDeletedAt(LocalDateTime.now());
            parkingMeterRepository.deleteById(parkingMeter.getId());
        }
    }

    @Override
    public BigDecimal calculateTotalAmount(int hours) {
        if (hours == 1 || hours == 0) {
            return BigDecimal.valueOf(PARKING_METER_COST_FIST_HOUR);
        }
        return BigDecimal.valueOf(PARKING_METER_COST_FIST_HOUR + (PARKING_METER_COST_ADDITIONAL_HOUR * (hours - 1)));
    }

}
