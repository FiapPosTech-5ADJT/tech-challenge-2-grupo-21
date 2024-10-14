package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.model.Vehicle;
import br.com.fiap.park_tech.repository.VehicleRepository;
import br.com.fiap.park_tech.service.VehicleService;
import br.com.fiap.park_tech.exception.VehiclerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    @CachePut(value = "vehicles", key = "#result.id")
    public Vehicle createVehicle(VehicleDTO vehicle) {
        var newVehicle = Vehicle.newVehicle(vehicle.getLicensePlate());
        newVehicle.setCreatedAt(Instant.now());
        return vehicleRepository.save(newVehicle);
    }

    @Override
    @Cacheable(value = "vehicles", key = "#vehicleId")
    public Vehicle getVehicleById(String vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehiclerNotFoundException(vehicleId));
    }

    @Override
    @Cacheable(value = "vehicles", key = "#licensePlate")
    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate).orElseThrow(() -> new VehiclerNotFoundException(licensePlate));
    }

    @Override
    @CacheEvict(value = "vehicles", key = "#vehicleId")
    public void deleteVehicleById(String vehicleId) {
        var vehicle = getVehicleById(vehicleId);
        if (vehicle.getDeletedAt() == null) {
            vehicle.setDeletedAt(Instant.now());
            vehicleRepository.save(vehicle);
        }
    }

    @Override
    @CacheEvict(value = "vehicles", key = "#licensePlate")
    public void deleteVehicleByLicensePlate(String licensePlate) {
        var vehicle = getVehicleByLicensePlate(licensePlate);
        if (vehicle.getDeletedAt() == null) {
            vehicle.setDeletedAt(Instant.now());
            vehicleRepository.save(vehicle);
        }
    }
}
