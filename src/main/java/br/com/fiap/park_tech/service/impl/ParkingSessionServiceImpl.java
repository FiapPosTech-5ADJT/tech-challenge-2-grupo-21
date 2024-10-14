package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.ParkingSessionDTO;
import br.com.fiap.park_tech.dto.VehiclePaymentDTO;
import br.com.fiap.park_tech.enums.PaymentMethods;
import br.com.fiap.park_tech.exception.ParkingSessionNotFoundException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotAlreadyOcuppiedException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotNotFoundException;
import br.com.fiap.park_tech.model.ParkingSession;
import br.com.fiap.park_tech.model.ParkingSlot;
import br.com.fiap.park_tech.model.VehiclePayment;
import br.com.fiap.park_tech.repository.ParkingSessionRepository;
import br.com.fiap.park_tech.repository.ParkingSlotRepository;
import br.com.fiap.park_tech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ParkingSlotService parkingSlotService;
    private final ParkingMeterService parkingMeterService;
    private VehiclePaymentService vehiclePaymentService;
    private final ParkingSlotRepository parkingSlotRepository;

    @Autowired
    public ParkingSessionServiceImpl(ParkingSessionRepository parkingSessionRepository, VehicleService vehicleService, ParkingSlotService parkingSlotService, ParkingMeterService parkingMeterService,  ParkingSlotRepository parkingSlotRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.vehicleService = vehicleService;
        this.parkingSlotService = parkingSlotService;
        this.parkingMeterService = parkingMeterService;
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Autowired
    public void setVehiclePaymentService(@Lazy VehiclePaymentService vehiclePaymentService) {
        this.vehiclePaymentService = vehiclePaymentService;
    }

    @Override
    public ParkingSession createParkingSession(ParkingSessionDTO parkingSessionDTO) {
        var vehicle = vehicleService.getVehicleByLicensePlate(parkingSessionDTO.getVehicleLicensePlate());
        Optional<ParkingSlot> parkingSlot = parkingSlotRepository.findById(parkingSessionDTO.getParkingSlotId());
        if (parkingSlot.isEmpty()) {
            throw new ParkingSlotNotFoundException(parkingSessionDTO.getParkingSlotId());
        }
        if (!parkingSlot.get().isAvailable()) {
            throw new ParkingSlotAlreadyOcuppiedException(parkingSessionDTO.getParkingSlotId());
        }
        // marcar como ocupado
        parkingSlot.get().setAvailable(false);
        parkingSlotRepository.save(parkingSlot.get());
        var newParkingSession = ParkingSession.newParkingSession(vehicle, parkingSlot.orElse(null), null);
        return parkingSessionRepository.save(newParkingSession);
    }

    @Override
    public ParkingSession getParkingSessionById(String parkingSessionId) {
        return parkingSessionRepository.findById(parkingSessionId).orElseThrow(() -> new ParkingSessionNotFoundException(parkingSessionId));
    }

    @Override
    public ParkingSession getParkingSessionByVehicleId(String vehicleId) {
        return parkingSessionRepository.findByVehicleId(vehicleId).orElseThrow(() -> new ParkingSessionNotFoundException(vehicleId));
    }

    @Override
    public void deleteParkingSessionById(String parkingSessionId) {
        parkingSessionRepository.deleteById(parkingSessionId);
    }

    @Override
    public ParkingSession endParkingSession(ParkingSessionDTO parkingSessionDTO, String paymentMethod) {
        var parkingSession = getParkingSessionById(parkingSessionDTO.getParkingSlotId());
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
        var vehiclePaymentDto = new VehiclePaymentDTO(parkingSession.getId(), amount, LocalDateTime.now(), PaymentMethods.valueOf(paymentMethod));
        return vehiclePaymentService.createVehiclePayment(vehiclePaymentDto);
    }

    private BigDecimal calculateAmount(ParkingSession parkingSession) {
        var checkIn = parkingSession.getCheckIn();
        var checkOut = parkingSession.getCheckOut();
        var duration = checkOut.getHour() - checkIn.getHour();
        return parkingMeterService.calculateTotalAmount(duration);
    }
}
