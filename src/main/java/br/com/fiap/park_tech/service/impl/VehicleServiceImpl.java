package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.dto.VehicleResponseDTO;
import br.com.fiap.park_tech.mapper.VehicleMapper;
import br.com.fiap.park_tech.model.Vehicle;
import br.com.fiap.park_tech.repository.VehicleRepository;
import br.com.fiap.park_tech.service.VehicleService;
import br.com.fiap.park_tech.exception.VehiclerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    @CachePut(value = "vehicles", key = "#result.id")
    public VehicleResponseDTO createVehicle(VehicleDTO vehicle) {
        Vehicle newVehicle = Vehicle.newVehicle(vehicle.getLicensePlate());
        vehicleRepository.save(newVehicle);
        return vehicleMapper.toResponseDTO(newVehicle);
    }

    @Override
    @Cacheable(value = "vehicles", key = "#vehicleId")
    public VehicleResponseDTO getVehicleById(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehiclerNotFoundException(vehicleId));
        return vehicleMapper.toResponseDTO(vehicle);
    }

    @Override
    @Cacheable(value = "vehicles", key = "#licensePlate")
    public VehicleResponseDTO getVehicleByLicensePlate(String licensePlate) {
        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate).orElseThrow(() -> new VehiclerNotFoundException(licensePlate));
        return vehicleMapper.toResponseDTO(vehicle);
    }

    @Override
    @CacheEvict(value = "vehicles", key = "#vehicleId")
    public void deleteVehicleById(String vehicleId) {
        var vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehiclerNotFoundException(vehicleId));
        if (vehicle.getDeletedAt() == null) {
            vehicle.setDeletedAt(LocalDateTime.now());
            vehicleRepository.save(vehicle);
        }
    }

    @CacheEvict(value = "vehicles", key = "#licensePlate")
    @Override
    public void deleteVehicleByLicensePlate(String licensePlate) {
        var vehicle = vehicleRepository.findByLicensePlate(licensePlate).orElseThrow(() -> new VehiclerNotFoundException(licensePlate));
        if (vehicle.getDeletedAt() == null) {
            vehicle.setDeletedAt(LocalDateTime.now());
            vehicleRepository.save(vehicle);
        }
    }
}
