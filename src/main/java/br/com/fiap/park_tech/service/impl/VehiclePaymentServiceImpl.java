package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.VehiclePaymentDTO;
import br.com.fiap.park_tech.exception.EntityAlreadyDeletedException;
import br.com.fiap.park_tech.exception.VehiclePaymentNotFoundException;
import br.com.fiap.park_tech.model.ParkingSession;
import br.com.fiap.park_tech.model.VehiclePayment;
import br.com.fiap.park_tech.repository.VehiclePaymentRepository;
import br.com.fiap.park_tech.service.ParkingSessionService;
import br.com.fiap.park_tech.service.VehiclePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class VehiclePaymentServiceImpl implements VehiclePaymentService {

    private final VehiclePaymentRepository vehiclePaymentRepository;
    private ParkingSessionService parkingSessionService;

    @Autowired
    public VehiclePaymentServiceImpl(VehiclePaymentRepository vehiclePaymentRepository) {
        this.vehiclePaymentRepository = vehiclePaymentRepository;
    }

    @Autowired
    public void setParkingSessionService(@Lazy ParkingSessionService parkingSessionService) {
        this.parkingSessionService = parkingSessionService;
    }


    @Override
    @CachePut(value = "vehiclePayments", key = "#result.id")
    public VehiclePayment createVehiclePayment(VehiclePaymentDTO vehiclePayment) {
        ParkingSession parkingSession = this.parkingSessionService.getParkingSessionById(vehiclePayment.getParkingSessionId());
        VehiclePayment newVehiclePayment = VehiclePayment.newVehiclePayment(parkingSession, vehiclePayment.getAmount(), vehiclePayment.getPaymentMethod());
        return vehiclePaymentRepository.save(newVehiclePayment);
    }

    @Override
    @Cacheable(value = "vehiclePayments", key = "#vehiclePaymentId")
    public VehiclePayment getVehiclePaymentById(String vehiclePaymentId) {
        VehiclePayment vehiclePayment = vehiclePaymentRepository.findById(vehiclePaymentId).orElseThrow(() -> new VehiclePaymentNotFoundException(vehiclePaymentId));
        if (vehiclePayment.getDeletedAt() != null) {
            throw new EntityAlreadyDeletedException(vehiclePaymentId);
        }
        return vehiclePayment;
    }

    @Override
    @CacheEvict(value = "vehiclePayments", key = "#vehiclePaymentId")
    public void deleteVehiclePaymentById(String vehiclePaymentId) {
        var vehiclePayment = getVehiclePaymentById(vehiclePaymentId);
        if (vehiclePayment.getDeletedAt() == null) {
            vehiclePayment.setDeletedAt(Instant.now());
            vehiclePayment.setUpdatedAt(Instant.now());
            vehiclePaymentRepository.save(vehiclePayment);
        }
    }

}
