package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.ParkingSessionDTO;
import br.com.fiap.park_tech.model.ParkingSession;

public interface ParkingSessionService {
  ParkingSession createParkingSession(ParkingSessionDTO parkingSession);
  ParkingSession getParkingSessionById(String parkingSessionId);
  ParkingSession getParkingSessionByVehicleId(String parkingSessionId);
  void deleteParkingSessionById(String parkingSessionId);
  ParkingSession endParkingSession(ParkingSessionDTO parkingSession, String paymentMethod);
}
