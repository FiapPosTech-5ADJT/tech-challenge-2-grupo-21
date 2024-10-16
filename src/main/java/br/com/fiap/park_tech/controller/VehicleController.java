package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.VehicleControllerDocs;
import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.dto.VehicleResponseDTO;
import br.com.fiap.park_tech.model.Vehicle;
import br.com.fiap.park_tech.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController implements VehicleControllerDocs {

    private final VehicleService vehicleService;

  @Override
  public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles() {
    List<VehicleResponseDTO> vehicles = vehicleService.getAllVehicles();
    return ResponseEntity.ok(vehicles);
  }

  @Override
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleResponseDTO createdVehicle = vehicleService.createVehicle(vehicleDTO);
        return ResponseEntity.ok(createdVehicle);
    }

    @Override
    public ResponseEntity<Void> deleteVehicleById(String vehicleId) {
        vehicleService.deleteVehicleById(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<VehicleResponseDTO> getVehicleById(String VehicleId) {
        VehicleResponseDTO vehicle = vehicleService.getVehicleById(VehicleId);
        return ResponseEntity.ok(vehicle);
    }

    @Override
    public ResponseEntity<VehicleResponseDTO> getVehicleByLicensePlate(String licensePlate) {
        VehicleResponseDTO vehicle = vehicleService.getVehicleByLicensePlate(licensePlate);
        return ResponseEntity.ok(vehicle);
    }

    @Override
    public ResponseEntity<Void> deleteVehicleByLicensePlate(String licensePlate) {
        vehicleService.deleteVehicleByLicensePlate(licensePlate);
        return ResponseEntity.noContent().build();
    }


}
