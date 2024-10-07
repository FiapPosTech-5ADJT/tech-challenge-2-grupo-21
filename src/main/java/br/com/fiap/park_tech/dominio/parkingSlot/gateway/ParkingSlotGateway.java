package br.com.fiap.park_tech.dominio.parkingSlot.gateway;

import br.com.fiap.park_tech.dominio.parkingSlot.entity.ParkingSlot;

public interface ParkingSlotGateway {
    ParkingSlot createParkingSlot(ParkingSlot parkingSlot);
    void deleteParkingSlotById(Long parkingSlotId);
    ParkingSlot getParkingSlotById(Long parkingSlotId);
    ParkingSlot getParkingSlotByVehicleId(Long vehicleId);
    ParkingSlot removeVehicleFromParkingSlot(Long parkingSlotId);


}
