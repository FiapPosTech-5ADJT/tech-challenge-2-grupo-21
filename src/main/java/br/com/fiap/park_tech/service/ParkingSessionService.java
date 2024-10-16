package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.ParkingSessionDTO;
import br.com.fiap.park_tech.model.ParkingSession;

public interface ParkingSessionService {
  ParkingSession createParkingSession(ParkingSessionDTO parkingSession);
  ParkingSession getParkingSessionById(String parkingSessionId);
  ParkingSession endParkingSession(String parkingSessionId, String paymentMethod);
}
