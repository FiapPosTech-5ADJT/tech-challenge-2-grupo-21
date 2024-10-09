package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.ParkingSlotControllerDocs;
import br.com.fiap.park_tech.model.ParkingSlot;
import br.com.fiap.park_tech.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-slot")
@RequiredArgsConstructor
public class ParkingSlotController implements ParkingSlotControllerDocs {

    private final ParkingSlotService parkingSlotService;

    @Override
    public ResponseEntity<ParkingSlot> createParkingSlot(@RequestBody ParkingSlot parkingSlot) {
        var createdParkingSlot = parkingSlotService.createParkingSlot(parkingSlot);
        return ResponseEntity.ok(createdParkingSlot);
    }

    @Override
    public ResponseEntity<Void> deleteParkingSlotById(@PathVariable Long parkingSlotId) {
        parkingSlotService.deleteParkingSlotById(parkingSlotId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ParkingSlot> getParkingSlotById(@PathVariable Long parkingSlotId) {
        var parkingSlot = parkingSlotService.getParkingSlotById(parkingSlotId);
        return ResponseEntity.ok(parkingSlot);
    }

    @Override
    public ResponseEntity<ParkingSlot> getParkingSlotByVehicleId(@PathVariable Long vehicleId) {
        var parkingSlot = parkingSlotService.getParkingSlotByVehicleId(vehicleId);
        return ResponseEntity.ok(parkingSlot);
    }

    @Override
    public ResponseEntity<ParkingSlot> removeVehicleFromParkingSlot(@PathVariable Long parkingSlotId) {
        var parkingSlot = parkingSlotService.removeVehicleFromParkingSlot(parkingSlotId);
        return ResponseEntity.ok(parkingSlot);
    }
}
