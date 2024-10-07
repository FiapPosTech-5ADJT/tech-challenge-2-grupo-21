package br.com.fiap.park_tech.dominio.parkingMeter.service;

import br.com.fiap.park_tech.dominio.parkingMeter.entity.ParkingMeter;
import br.com.fiap.park_tech.dominio.parkingMeter.exception.ParkingMeterNotFoundException;
import br.com.fiap.park_tech.dominio.parkingMeter.gateway.ParkingMeterGateway;
import br.com.fiap.park_tech.dominio.parkingSlot.gateway.ParkingSlotGateway;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class ParkingMeterService {

    private final ParkingMeterGateway parkingMeterGateway;
    private final ParkingSlotGateway parkingSlotGateway;


    public ParkingMeter createParkingMeter(final ParkingMeter parkingMeter) {
        parkingMeter.setCreatedAt(Instant.now());
        var createdParkingMeter = parkingMeterGateway.createParkingMeter(parkingMeter);
        return createdParkingMeter;
    }

    public ParkingMeter getParkingMeterById(final Long id) {
        final var parkingMeter = parkingMeterGateway.getParkingMeterById(id);
        return parkingMeter.orElseThrow(() -> new ParkingMeterNotFoundException(String.valueOf(id)));
    }

    public ParkingMeter getParkingMeterByName(final String name) {
        final var parkingMeter = parkingMeterGateway.getParkingMeterByName(name);
        return parkingMeter.orElseThrow(() -> new ParkingMeterNotFoundException(name));
    }

    public void deleteParkingMeterById(final Long id) {
        var parkingMeter = getParkingMeterById(id);
        if (parkingMeter.getDeletedAt() == null) {
            parkingMeter.setDeletedAt(Instant.now());
            parkingMeterGateway.deleteParkingMeterById(id);
        }
    }

    public ParkingMeter addParkingSlotToParkingMeter(final Long parkingMeterId, final Long parkingSlotId) {
        var parkingMeter = getParkingMeterById(parkingMeterId);
        var parkingSlot = parkingSlotGateway.getParkingSlotById(parkingSlotId);
        parkingMeter.setUpdatedAt(Instant.now());
        parkingSlot.setUpdatedAt(Instant.now());
        parkingSlot.setParkingMeter(parkingMeter);
        parkingMeter.getParkingSlots().add(parkingSlot);
        return parkingMeterGateway.addParkingSlotToParkingMeter(parkingMeter);
    }

    public ParkingMeter removeParkingSlotFromParkingMeter(final Long parkingMeterId, final Long parkingSlotId) {
        var parkingMeter = getParkingMeterById(parkingMeterId);
        var parkingSlot = parkingSlotGateway.getParkingSlotById(parkingSlotId);
        parkingMeter.setUpdatedAt(Instant.now());
        parkingSlot.setUpdatedAt(Instant.now());
        parkingSlot.setParkingMeter(null);
        parkingMeter.getParkingSlots().remove(parkingSlot);
        return parkingMeterGateway.removeParkingSlotFromParkingMeter(parkingMeter);
    }



}
