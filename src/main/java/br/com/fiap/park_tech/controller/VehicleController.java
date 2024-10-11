package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.VehicleControllerDocs;
import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.model.ParkingSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController implements VehicleControllerDocs {
    @Override
    public ResponseEntity<ParkingSlot> createVehicle(VehicleDTO vehicleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteVehicleById(String vehicleId) {
        return null;
    }

    @Override
    public ResponseEntity<ParkingSlot> getVehicleById(String VehicleId) {
        return null;
    }
}
