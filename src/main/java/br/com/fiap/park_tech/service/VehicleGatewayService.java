package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.model.Vehicle;

public interface VehicleGatewayService {
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long vehicleId);
    Vehicle getVehicleByLicensePlate(String licensePlate);
    void deleteVehicleById(Long vehicleId);

}
