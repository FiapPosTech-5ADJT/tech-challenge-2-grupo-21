package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.ParkingSessionDTO;
import br.com.fiap.park_tech.dto.VehiclePaymentDTO;
import br.com.fiap.park_tech.dto.VehicleResponseDTO;
import br.com.fiap.park_tech.enums.PaymentMethods;
import br.com.fiap.park_tech.exception.EntityAlreadyDeletedException;
import br.com.fiap.park_tech.exception.ParkingSessionNotFoundException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotAlreadyOcuppiedException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotNotFoundException;
import br.com.fiap.park_tech.mapper.VehicleMapper;
import br.com.fiap.park_tech.model.ParkingSession;
import br.com.fiap.park_tech.model.ParkingSlot;
import br.com.fiap.park_tech.model.Vehicle;
import br.com.fiap.park_tech.model.VehiclePayment;
import br.com.fiap.park_tech.repository.ParkingSessionRepository;
import br.com.fiap.park_tech.repository.ParkingSlotRepository;
import br.com.fiap.park_tech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParkingSessionServiceImpl implements ParkingSessionService {
    private final ParkingSessionRepository parkingSessionRepository;
    private final VehicleService vehicleService;
    private final ParkingMeterService parkingMeterService;
    private VehiclePaymentService vehiclePaymentService;
    private final ParkingSlotRepository parkingSlotRepository;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public ParkingSessionServiceImpl(ParkingSessionRepository parkingSessionRepository, VehicleService vehicleService, ParkingSlotService parkingSlotService, ParkingMeterService parkingMeterService, ParkingSlotRepository parkingSlotRepository, VehicleMapper vehicleMapper) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.vehicleService = vehicleService;
        this.parkingMeterService = parkingMeterService;
        this.parkingSlotRepository = parkingSlotRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Autowired
    public void setVehiclePaymentService(@Lazy VehiclePaymentService vehiclePaymentService) {
        this.vehiclePaymentService = vehiclePaymentService;
    }

    @Override
    @CachePut(value = "parkingSessions", key = "#result.id")
    public ParkingSession createParkingSession(ParkingSessionDTO parkingSessionDTO) {
        VehicleResponseDTO vehicle = vehicleService.getVehicleByLicensePlate(parkingSessionDTO.getVehicleLicensePlate());
        Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(parkingSessionDTO.getParkingSlotId());
        if (parkingSlot.isEmpty()) {
            throw new ParkingSlotNotFoundException(parkingSessionDTO.getParkingSlotId());
        }
        if (!parkingSlot.get().isAvailable()) {
            throw new ParkingSlotAlreadyOcuppiedException(parkingSessionDTO.getParkingSlotId());
        }
        parkingSlot.get().setAvailable(false);
        parkingSlotRepository.save(parkingSlot.get());
        Vehicle vehicle1 = vehicleMapper.toEntity(vehicle);
        var newParkingSession = ParkingSession.newParkingSession(vehicleMapper.toEntity(vehicle), parkingSlot.orElse(null), null);
        return parkingSessionRepository.save(newParkingSession);
    }

    @Override
    @Cacheable(value = "parkingSessions", key = "#parkingSessionId")
    public ParkingSession getParkingSessionById(String parkingSessionId) {
        ParkingSession parkingSession = parkingSessionRepository.findById(parkingSessionId).orElseThrow(() -> new ParkingSessionNotFoundException(parkingSessionId));
        if (parkingSession.getDeletedAt() != null) {
            throw new EntityAlreadyDeletedException(parkingSession.getId());
        }
        return parkingSession;
    }

    @Override
    @Cacheable(value = "parkingSessions", key = "#vehicleId")
    public ParkingSession getParkingSessionByVehicleId(String vehicleId) {
        ParkingSession parkingSession =parkingSessionRepository.findByVehicleId(vehicleId).orElseThrow(() -> new ParkingSessionNotFoundException(vehicleId));
        if (parkingSession.getDeletedAt() != null) {
            throw new EntityAlreadyDeletedException(parkingSession.getId());
        }
        return parkingSession;
    }

    @Override
    @CacheEvict(value = "parkingSessions", key = "#parkingSessionId")
    public void deleteParkingSessionById(String parkingSessionId) {
        parkingSessionRepository.deleteById(parkingSessionId);
    }

    @Override
    @CacheEvict(value = "parkingSessions", key = "#parkingSessionId")
    public ParkingSession endParkingSession(String parkingSessionId, String paymentMethod) {
        var parkingSession = getParkingSessionById(parkingSessionId);
        parkingSession.setCheckOut(LocalDateTime.now());
        parkingSession.setUpdatedAt(Instant.now());
        parkingSession.setVehiclePayment(getVehiclePayment(parkingSession, paymentMethod));

        Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(parkingSession.getParkingSlot().getId());
        // marcar como disponível
        parkingSlot.get().setAvailable(false);
        parkingSlotRepository.save(parkingSlot.get());
        return parkingSessionRepository.save(parkingSession);
    }

    private VehiclePayment getVehiclePayment(ParkingSession parkingSession, String paymentMethod) {
        var amount = calculateAmount(parkingSession);
        var vehiclePaymentDto = new VehiclePaymentDTO(parkingSession.getId(), amount, LocalDateTime.now(), PaymentMethods.fromDescription(paymentMethod));
        return vehiclePaymentService.createVehiclePayment(vehiclePaymentDto);
    }

    private BigDecimal calculateAmount(ParkingSession parkingSession) {
        var checkIn = parkingSession.getCheckIn();
        var checkOut = parkingSession.getCheckOut();
        var duration = checkOut.getHour() - checkIn.getHour();
        return parkingMeterService.calculateTotalAmount(duration);
    }
}
