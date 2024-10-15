package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.ParkingSessionControllerDocs;
import br.com.fiap.park_tech.dto.ParkingSessionDTO;
import br.com.fiap.park_tech.model.ParkingSession;
import br.com.fiap.park_tech.service.ParkingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParkingSessionController implements ParkingSessionControllerDocs {

    private final ParkingSessionService parkingSessionService;

    @Override
    public ResponseEntity<ParkingSession> createParkingSession(ParkingSessionDTO parkingSessionDTO) {
      ParkingSession createdSession = parkingSessionService.createParkingSession(parkingSessionDTO);
      return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }

  @Override
  public ResponseEntity<ParkingSession> endParkingSession(ParkingSessionDTO parkingSessionDTO, String paymentMethod) {
      ParkingSession createdSession = parkingSessionService.endParkingSession(parkingSessionDTO, paymentMethod);
      return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
  }
}
