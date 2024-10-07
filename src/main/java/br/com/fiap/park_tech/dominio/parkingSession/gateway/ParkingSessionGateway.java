package br.com.fiap.park_tech.dominio.parkingSession.gateway;

import br.com.fiap.park_tech.dominio.parkingSession.entity.ParkingSession;

public interface ParkingSessionGateway {
    ParkingSession createParkingSession(ParkingSession parkingSession);
    ParkingSession getParkingSessionById(Long parkingSessionId);
    void deleteParkingSessionById(Long parkingSessionId);
    ParkingSession startParkingSession(ParkingSession parkingSession);
    ParkingSession endParkingSession(ParkingSession parkingSession);

}
