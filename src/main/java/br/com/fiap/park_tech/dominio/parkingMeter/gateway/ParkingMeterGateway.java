package br.com.fiap.park_tech.dominio.parkingMeter.gateway;

import br.com.fiap.park_tech.dominio.parkingMeter.entity.ParkingMeter;

import java.util.Optional;

public interface ParkingMeterGateway {

    ParkingMeter createParkingMeter(ParkingMeter parkingMeter);
    Optional<ParkingMeter> getParkingMeterById(Long id);
    Optional<ParkingMeter> getParkingMeterByName(String name);
    void deleteParkingMeterById(Long id);
    ParkingMeter addParkingSlotToParkingMeter(ParkingMeter parkingMeter);
    ParkingMeter removeParkingSlotFromParkingMeter(ParkingMeter parkingMeter);


}
