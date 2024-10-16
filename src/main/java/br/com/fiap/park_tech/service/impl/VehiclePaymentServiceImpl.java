package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.VehiclePaymentDTO;
import br.com.fiap.park_tech.dto.VehiclePaymentResponseDTO;
import br.com.fiap.park_tech.exception.VehiclePaymentNotFoundException;
import br.com.fiap.park_tech.mapper.VehiclePaymentMapper;
import br.com.fiap.park_tech.model.ParkingSession;
import br.com.fiap.park_tech.model.Vehicle;
import br.com.fiap.park_tech.model.VehiclePayment;
import br.com.fiap.park_tech.repository.ParkingSessionRepository;
import br.com.fiap.park_tech.repository.VehiclePaymentRepository;
import br.com.fiap.park_tech.repository.VehicleRepository;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiclePaymentServiceImpl implements VehiclePaymentService {

    private final VehiclePaymentRepository vehiclePaymentRepository;
    private final ParkingSessionRepository parkingSessionRepository;
    private final VehicleRepository vehicleRepository;
    private ParkingSessionService parkingSessionService;
    private final VehiclePaymentMapper vehiclePaymentMapper;

    @Autowired
    public VehiclePaymentServiceImpl(VehiclePaymentRepository vehiclePaymentRepository, ParkingSessionRepository parkingSessionRepository, VehicleRepository vehicleRepository, VehiclePaymentMapper vehiclePaymentMapper) {
        this.vehiclePaymentRepository = vehiclePaymentRepository;
      this.parkingSessionRepository = parkingSessionRepository;
      this.vehicleRepository = vehicleRepository;
      this.vehiclePaymentMapper = vehiclePaymentMapper;
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
        return vehiclePaymentRepository.findById(vehiclePaymentId).orElseThrow(() -> new VehiclePaymentNotFoundException(vehiclePaymentId));
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

  @Override
  public List<VehiclePaymentResponseDTO> getAllVehiclePayments() {
    return vehiclePaymentRepository.findAll().stream()
      .map(vehiclePaymentMapper::toResponseDTO)
      .collect(Collectors.toList());
  }

  @Override
  public List<VehiclePaymentResponseDTO> getVehiclePaymentsByVehicleId(String licensePlate) {
       Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate).orElseThrow(() -> new VehiclePaymentNotFoundException(licensePlate));
        // Buscar todas as sessões de estacionamento relacionadas ao veículo
        List<ParkingSession> parkingSessions = parkingSessionRepository.findByVehicle(vehicle);
      // Buscar todos os pagamentos relacionados a essas sessões de estacionamento
      return parkingSessions.stream()
        .flatMap(parkingSession -> vehiclePaymentRepository.findByParkingSession(parkingSession).stream())
        .map(vehiclePaymentMapper::toResponseDTO)
        .collect(Collectors.toList());
  }

}
