package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.model.ParkingSession;

public interface ParkingSessionService {
  ParkingSession createParkingSession(ParkingSession parkingSession);
  ParkingSession getParkingSessionById(Long parkingSessionId);
  void deleteParkingSessionById(Long parkingSessionId);
  ParkingSession startParkingSession(ParkingSession parkingSession);
  ParkingSession endParkingSession(ParkingSession parkingSession);

}
