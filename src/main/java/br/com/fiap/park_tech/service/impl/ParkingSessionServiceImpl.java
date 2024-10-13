package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.ParkingSessionDTO;
import br.com.fiap.park_tech.dto.VehiclePaymentDTO;
import br.com.fiap.park_tech.enums.PaymentMethods;
import br.com.fiap.park_tech.exception.ParkingSessionNotFoundException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotAlreadyOcuppiedException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotNotFoundException;
import br.com.fiap.park_tech.model.ParkingSession;
import br.com.fiap.park_tech.model.VehiclePayment;
import br.com.fiap.park_tech.repository.ParkingSessionRepository;
import br.com.fiap.park_tech.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class ParkingSessionServiceImpl implements ParkingSessionService {
    private final ParkingSessionRepository parkingSessionRepository;
    private final VehicleService vehicleService;
    private final ParkingSlotService parkingSlotService;
    private final ParkingMeterService parkingMeterService;
    private VehiclePaymentService vehiclePaymentService;

    @Autowired
    public ParkingSessionServiceImpl(ParkingSessionRepository parkingSessionRepository, VehicleService vehicleService, ParkingSlotService parkingSlotService, ParkingMeterService parkingMeterService) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.vehicleService = vehicleService;
        this.parkingSlotService = parkingSlotService;
        this.parkingMeterService = parkingMeterService;
    }

    @Autowired
    public void setVehiclePaymentService(@Lazy VehiclePaymentService vehiclePaymentService) {
        this.vehiclePaymentService = vehiclePaymentService;
    }

    @Override
    public ParkingSession createParkingSession(ParkingSessionDTO parkingSessionDTO) {
        var vehicle = vehicleService.getVehicleByLicensePlate(parkingSessionDTO.getVehicleLicensePlate());
        var parkingSlot = parkingSlotService.getParkingSlotById(parkingSessionDTO.getParkingSlotId());
        if (parkingSlot == null) {
            throw new ParkingSlotNotFoundException(parkingSessionDTO.getParkingSlotId());
        }
        if (!parkingSlot.isAvailable()) {
            throw new ParkingSlotAlreadyOcuppiedException(parkingSessionDTO.getParkingSlotId());
        }
        parkingSlotService.addVehicleToParkingSlot(parkingSessionDTO.getParkingSlotId());
        var newParkingSession = ParkingSession.newParkingSession(vehicle, parkingSlot, null);
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
        parkingSlotService.removeVehicleFromParkingSlot(parkingSession.getParkingSlot().getId());
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
