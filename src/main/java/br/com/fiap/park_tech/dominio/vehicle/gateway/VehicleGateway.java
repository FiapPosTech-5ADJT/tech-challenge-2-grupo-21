package br.com.fiap.park_tech.dominio.vehicle.gateway;

import br.com.fiap.park_tech.dominio.vehicle.entity.Vehicle;

public interface VehicleGateway {
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long vehicleId);
    Vehicle getVehicleByLicensePlate(String licensePlate);
    void deleteVehicleById(Long vehicleId);

}
