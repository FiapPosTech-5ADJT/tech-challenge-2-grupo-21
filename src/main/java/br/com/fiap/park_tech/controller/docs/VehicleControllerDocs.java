package br.com.fiap.park_tech.controller.docs;

import br.com.fiap.park_tech.dto.ParkingSlotDTO;
import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.dto.VehicleResponseDTO;
import br.com.fiap.park_tech.model.ParkingSlot;
import br.com.fiap.park_tech.model.Vehicle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Vehicle Controller", description = "Operations related to vehicles")
public interface VehicleControllerDocs {

    @Operation(summary = "Create a new vehicle")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vehicle created successfully",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Vehicle.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                content = @Content)
    })
    @PostMapping
    ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO);

    @Operation(summary = "Delete a Vehicle by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Vehicle deleted successfully",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Vehicle not found",
                content = @Content)
    })
    @DeleteMapping("/{vehicleId}")
    ResponseEntity<Void> deleteVehicleById(@PathVariable String vehicleId);

    @Operation(summary = "Get a vehicle ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the vehicle",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Vehicle.class)) }),
        @ApiResponse(responseCode = "404", description = "Vehicle not found",
                content = @Content)
    })
    @GetMapping("/{VehicleId}")
    ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable String VehicleId);

    @Operation(summary = "Get a vehicle License Plate")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the vehicle",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Vehicle.class)) }),
        @ApiResponse(responseCode = "404", description = "Vehicle not found",
                content = @Content)
    })
    @GetMapping("/licensePlate/{licensePlate}")
    ResponseEntity<VehicleResponseDTO> getVehicleByLicensePlate(String licensePlate);

    @Operation(summary = "Delete a Vehicle by License Plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehicle deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Vehicle not found",
                    content = @Content)
    })
    @DeleteMapping("/licensePlate/{licensePlate}")
    ResponseEntity<Void> deleteVehicleByLicensePlate(@PathVariable String licensePlate);
}
