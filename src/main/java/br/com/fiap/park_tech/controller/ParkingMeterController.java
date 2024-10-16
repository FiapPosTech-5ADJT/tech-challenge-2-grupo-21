package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.ParkingMeterControllerDocs;
import br.com.fiap.park_tech.dto.ParkingMeterDTO;
import br.com.fiap.park_tech.dto.ParkingMeterResponseDTO;
import br.com.fiap.park_tech.exception.ParkingMeterNotFoundException;
import br.com.fiap.park_tech.service.ParkingMeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-meters")
@RequiredArgsConstructor
public class ParkingMeterController implements ParkingMeterControllerDocs {

    private final ParkingMeterService parkingMeterService;

    @Override
    public ResponseEntity<ParkingMeterResponseDTO> createParkingMeter(@RequestBody ParkingMeterDTO parkingMeterDTO) {
        ParkingMeterResponseDTO createdParkingMeter = parkingMeterService.createParkingMeter(parkingMeterDTO);
        return ResponseEntity.ok(createdParkingMeter);
    }

    @Override
    public ResponseEntity<List<ParkingMeterResponseDTO>> listAllParkingMeters() {
        List<ParkingMeterResponseDTO> parkingMeters = parkingMeterService.getAllParkingMeters();
        return ResponseEntity.ok(parkingMeters);
    }

    @Override
    public ResponseEntity<ParkingMeterResponseDTO> getParkingMeterById(@PathVariable String id) {
        ParkingMeterResponseDTO parkingMeter = parkingMeterService.getParkingMeterById(id);
        return ResponseEntity.ok(parkingMeter);
    }

    @Override
    public ResponseEntity<ParkingMeterResponseDTO> getParkingMeterByName(@RequestParam String name) {
        ParkingMeterResponseDTO parkingMeter = parkingMeterService.getParkingMeterByName(name);
        return ResponseEntity.ok(parkingMeter);
    }

    @Override
    public ResponseEntity<Void> deleteParkingMeterById(@PathVariable String id) {
        parkingMeterService.deleteParkingMeterById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ParkingMeterNotFoundException.class)
    public ResponseEntity<String> handleParkingMeterNotFoundException(ParkingMeterNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
