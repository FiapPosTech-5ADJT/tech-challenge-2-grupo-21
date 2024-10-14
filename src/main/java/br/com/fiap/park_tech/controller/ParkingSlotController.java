package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.ParkingSlotControllerDocs;
import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.dto.ParkingSlotResponseDTO;
import br.com.fiap.park_tech.model.ParkingSlot;
import br.com.fiap.park_tech.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-slot")
@RequiredArgsConstructor
public class ParkingSlotController implements ParkingSlotControllerDocs {

    private final ParkingSlotService parkingSlotService;

    @Override
    public ResponseEntity<ParkingSlotResponseDTO> createParkingSlot(@RequestBody ParkingSlotDTO parkingSlotDTO) {
        var createdParkingSlot = parkingSlotService.createParkingSlot(parkingSlotDTO);
        return ResponseEntity.ok(createdParkingSlot);
    }

    @Override
    public ResponseEntity<Void> deleteParkingSlotById(@PathVariable String parkingSlotId) {
        parkingSlotService.deleteParkingSlotById(parkingSlotId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ParkingSlotResponseDTO> getParkingSlotById(@PathVariable String parkingSlotId) {
        var parkingSlot = parkingSlotService.getParkingSlotById(parkingSlotId);
        return ResponseEntity.ok(parkingSlot);
    }

  @Override
  public ResponseEntity<List<ParkingSlotResponseDTO>> listAllParkingSlots() {
      List<ParkingSlotResponseDTO> parkingSlots = parkingSlotService.listAllParkingSlots();
      return ResponseEntity.ok(parkingSlots);
  }

  @Override
  public ResponseEntity<List<ParkingSlotResponseDTO>> getParkingSlotsByParkingMeterId(String parkingMeterId) {
      List<ParkingSlotResponseDTO> parkingSlots = parkingSlotService.getParkingSlotsByParkingMeterId(parkingMeterId);
      return ResponseEntity.ok(parkingSlots);
  }

}
