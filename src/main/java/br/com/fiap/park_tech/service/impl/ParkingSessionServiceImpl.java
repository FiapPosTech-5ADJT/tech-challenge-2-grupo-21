package br.com.fiap.park_tech.service.impl;

import br.com.fiap.park_tech.dto.ParkingSessionDTO;
import br.com.fiap.park_tech.exception.ParkingSessionNotFoundException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotAlreadyOcuppiedException;
import br.com.fiap.park_tech.exception.parkingSlot.ParkingSlotNotFoundException;
import br.com.fiap.park_tech.model.ParkingSession;
import br.com.fiap.park_tech.model.VehiclePayment;
import br.com.fiap.park_tech.repository.ParkingSessionRepository;
import br.com.fiap.park_tech.service.ParkingSessionService;
import br.com.fiap.park_tech.service.ParkingSlotService;
import br.com.fiap.park_tech.service.VehicleService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
public class ParkingSessionServiceImpl implements ParkingSessionService {
    private final ParkingSessionRepository parkingSessionRepository;
    private final VehicleService vehicleService;
    private final ParkingSlotService parkingSlotService;

    @Override
    public ParkingSession createParkingSession(ParkingSessionDTO parkingSessionDTO) {
        var vehicle = vehicleService.getVehicleByLicensePlate(parkingSessionDTO.getVehicleLicensePlate());
        var parkingSlot = parkingSlotService.getParkingSlotById(parkingSessionDTO.getParkingSlotId());
        if (parkingSlot == null) {
            throw new ParkingSlotNotFoundException(parkingSessionDTO.getParkingSlotId());
        }
        if(!parkingSlot.isAvailable()) {
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
    public ParkingSession endParkingSession(ParkingSessionDTO parkingSessionDTO) {
        var parkingSession = getParkingSessionById(parkingSessionDTO.getParkingSlotId());
        parkingSession.setCheckOut(LocalDateTime.now());
        parkingSession.setUpdatedAt(Instant.now());
        parkingSession.setVehiclePayment(getVehiclePayment(parkingSession));
        parkingSlotService.removeVehicleFromParkingSlot(parkingSession.getParkingSlot().getId());
        return parkingSessionRepository.save(parkingSession);
    }

    private VehiclePayment getVehiclePayment(ParkingSession parkingSession) {
        var checkIn = parkingSession.getCheckIn();
        var checkOut = parkingSession.getCheckOut();
        var duration = checkOut.getHour() - checkIn.getHour();
        //TODO: calculate amount based on duration and parking slot price
        //var amount = parkingSession.getParkingSlot()
        var amount = BigDecimal.valueOf(10);
        return VehiclePayment.newVehiclePayment(parkingSession, amount, LocalDateTime.now());
    }
}
