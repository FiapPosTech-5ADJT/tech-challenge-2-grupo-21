package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.ParkingMeterDTO;
import br.com.fiap.park_tech.dto.ParkingMeterResponseDTO;
import java.util.List;
import java.math.BigDecimal;

public interface ParkingMeterService {

    ParkingMeterResponseDTO createParkingMeter(ParkingMeterDTO parkingMeterDTO);
    ParkingMeterResponseDTO getParkingMeterById(String id);
    ParkingMeterResponseDTO getParkingMeterByName(String name);
    List<ParkingMeterResponseDTO> getAllParkingMeters();
    void deleteParkingMeterById(String id);
    BigDecimal calculateTotalAmount(int hours);
}
