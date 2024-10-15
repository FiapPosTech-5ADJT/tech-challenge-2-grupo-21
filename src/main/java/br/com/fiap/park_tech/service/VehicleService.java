package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.dto.VehicleResponseDTO;
import br.com.fiap.park_tech.model.Vehicle;
import org.springframework.cache.annotation.CacheEvict;

public interface VehicleService {
    VehicleResponseDTO createVehicle(VehicleDTO vehicle);
    VehicleResponseDTO getVehicleById(String vehicleId);
    VehicleResponseDTO getVehicleByLicensePlate(String licensePlate);
    void deleteVehicleById(String vehicleId);
    void deleteVehicleByLicensePlate(String licensePlate);
}
