package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.VehicleControllerDocs;
import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.dto.VehicleResponseDTO;
import br.com.fiap.park_tech.model.ParkingSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController implements VehicleControllerDocs {
    @Override
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteVehicleById(String vehicleId) {
        return null;
    }

    @Override
    public ResponseEntity<VehicleResponseDTO> getVehicleById(String VehicleId) {
        return null;
    }
}
