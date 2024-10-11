package br.com.fiap.park_tech.controller.docs;

import br.com.fiap.park_tech.dto.ParkingMeterDTO;
import br.com.fiap.park_tech.model.ParkingMeter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Parking Meter Controller", description = "Operations related to parking meters")
public interface ParkingMeterControllerDocs {

    @Operation(summary = "Create a new parking meter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Parking meter created successfully",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = ParkingMeter.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                content = @Content)
    })
    @PostMapping
    ResponseEntity<ParkingMeter> createParkingMeter(@RequestBody ParkingMeterDTO parkingMeterDTO);

    @Operation(summary = "Get a parking meter by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the parking meter",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = ParkingMeter.class)) }),
        @ApiResponse(responseCode = "404", description = "Parking meter not found",
                content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<ParkingMeter> getParkingMeterById(@PathVariable String id);

    @Operation(summary = "Delete a parking meter by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Parking meter deleted successfully",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Parking meter not found",
                content = @Content)
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteParkingMeterById(@PathVariable String id);
}
