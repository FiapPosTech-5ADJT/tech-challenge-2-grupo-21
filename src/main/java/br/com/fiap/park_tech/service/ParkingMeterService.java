package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.ParkingMeterDTO;
import br.com.fiap.park_tech.model.ParkingMeter;

import java.math.BigDecimal;
import java.util.Optional;

public interface ParkingMeterService {

    ParkingMeter createParkingMeter(ParkingMeterDTO parkingMeterDTO);
    Optional<ParkingMeter> getParkingMeterById(String id);
    Optional<ParkingMeter> getParkingMeterByName(String name);
    void deleteParkingMeterById(String id);
    ParkingMeter addParkingSlotToParkingMeter(ParkingMeter parkingMeter);
    ParkingMeter removeParkingSlotFromParkingMeter(ParkingMeter parkingMeter);
    BigDecimal calculateTotalAmount(int hours);
}
