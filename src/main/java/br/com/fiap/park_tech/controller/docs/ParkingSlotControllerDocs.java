package br.com.fiap.park_tech.controller.docs;

import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.dto.ParkingSlotResponseDTO;
import br.com.fiap.park_tech.model.ParkingSlot;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Parking Slot Controller", description = "Operations related to parking slots")
public interface ParkingSlotControllerDocs {

    @Operation(summary = "Create a new parking slot")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Parking slot created successfully",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = ParkingSlot.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                content = @Content)
    })
    @PostMapping
    ResponseEntity<ParkingSlotResponseDTO> createParkingSlot(@RequestBody ParkingSlotDTO parkingSlotDTO);

    @Operation(summary = "Delete a parking slot by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Parking slot deleted successfully",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Parking slot not found",
                content = @Content)
    })
    @DeleteMapping("/{parkingSlotId}")
    ResponseEntity<Void> deleteParkingSlotById(@PathVariable String parkingSlotId);

    @Operation(summary = "Get a parking slot by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the parking slot",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = ParkingSlotResponseDTO.class)) }),
        @ApiResponse(responseCode = "404", description = "Parking slot not found",
                content = @Content)
    })
    @GetMapping("/{parkingSlotId}")
    ResponseEntity<ParkingSlotResponseDTO> getParkingSlotById(@PathVariable String parkingSlotId);

    @Operation(summary = "List all parking slots")
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of all parking slots",
        content = { @Content(mediaType = "application/json",
          schema = @Schema(implementation = ParkingSlotResponseDTO.class)) })
    })
    @GetMapping
    ResponseEntity<List<ParkingSlotResponseDTO>> listAllParkingSlots();

  @Operation(summary = "Get parking slots by parking meter ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "List of parking slots for the given parking meter ID",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = ParkingSlotResponseDTO.class)) }),
    @ApiResponse(responseCode = "404", description = "Parking meter not found",
      content = @Content)
  })
  @GetMapping("/by-parking-meter/{parkingMeterId}")
  ResponseEntity<List<ParkingSlotResponseDTO>> getParkingSlotsByParkingMeterId(@PathVariable String parkingMeterId);
}
