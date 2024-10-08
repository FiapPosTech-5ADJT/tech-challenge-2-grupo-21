package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.exception.ParkingMeterNotFoundException;
import br.com.fiap.park_tech.model.ParkingMeter;
import br.com.fiap.park_tech.service.ParkingMeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-meters")
@RequiredArgsConstructor
public class ParkingMeterController {

    private final ParkingMeterService parkingMeterService;

    @PostMapping
    public ResponseEntity<ParkingMeter> createParkingMeter(@RequestBody ParkingMeter parkingMeter) {
        ParkingMeter createdParkingMeter = parkingMeterService.createParkingMeter(parkingMeter);
        return ResponseEntity.ok(createdParkingMeter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingMeter> getParkingMeterById(@PathVariable Long id) {
        ParkingMeter parkingMeter = parkingMeterService.getParkingMeterById(id)
                .orElseThrow(() -> new ParkingMeterNotFoundException(String.valueOf(id)));
        return ResponseEntity.ok(parkingMeter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingMeterById(@PathVariable Long id) {
        parkingMeterService.deleteParkingMeterById(id);
        return ResponseEntity.noContent().build();
    }
}
