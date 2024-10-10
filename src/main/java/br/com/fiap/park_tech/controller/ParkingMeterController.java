package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.ParkingMeterControllerDocs;
import br.com.fiap.park_tech.dto.ParkingMeterDTO;
import br.com.fiap.park_tech.exception.ParkingMeterNotFoundException;
import br.com.fiap.park_tech.model.ParkingMeter;
import br.com.fiap.park_tech.service.ParkingMeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-meters")
@RequiredArgsConstructor
public class ParkingMeterController implements ParkingMeterControllerDocs {

    private final ParkingMeterService parkingMeterService;

    @Override
    public ResponseEntity<ParkingMeter> createParkingMeter(@RequestBody ParkingMeterDTO parkingMeterDTO) {
        ParkingMeter createdParkingMeter = parkingMeterService.createParkingMeter(parkingMeterDTO);
        return ResponseEntity.ok(createdParkingMeter);
    }

    @Override
    public ResponseEntity<ParkingMeter> getParkingMeterById(@PathVariable Long id) {
        ParkingMeter parkingMeter = parkingMeterService.getParkingMeterById(id)
                .orElseThrow(() -> new ParkingMeterNotFoundException(String.valueOf(id)));
        return ResponseEntity.ok(parkingMeter);
    }

    @Override
    public ResponseEntity<Void> deleteParkingMeterById(@PathVariable Long id) {
        parkingMeterService.deleteParkingMeterById(id);
        return ResponseEntity.noContent().build();
    }
}
