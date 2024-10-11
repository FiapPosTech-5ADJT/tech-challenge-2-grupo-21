package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.model.Vehicle;

public interface VehicleService {
    Vehicle createVehicle(VehicleDTO vehicle);
    Vehicle getVehicleById(String vehicleId);
    Vehicle getVehicleByLicensePlate(String licensePlate);
    void deleteVehicleById(String vehicleId);
    void deleteVehicleByLicensePlate(String licensePlate);

}
