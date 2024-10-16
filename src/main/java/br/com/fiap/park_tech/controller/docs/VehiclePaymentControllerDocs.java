package br.com.fiap.park_tech.controller.docs;

import br.com.fiap.park_tech.dto.VehiclePaymentResponseDTO;
import br.com.fiap.park_tech.model.VehiclePayment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Vehicle Payment Controller", description = "Operations related to vehicle payments")
public interface VehiclePaymentControllerDocs {

    @Operation(summary = "Get all vehicle payments")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of all vehicle payments",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = VehiclePaymentResponseDTO.class)) })
    })
    @GetMapping("/vehicle-payments")
    ResponseEntity<List<VehiclePaymentResponseDTO>> getAllVehiclePayments();

    @Operation(summary = "Get vehicle payments by vehicle licensePlate ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of vehicle payments for the specified vehicle",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = VehiclePaymentResponseDTO.class)) }),
        @ApiResponse(responseCode = "404", description = "Vehicle not found",
                content = @Content)
    })
    @GetMapping("/vehicle-payments/by-vehicle")
    ResponseEntity<List<VehiclePaymentResponseDTO>> getVehiclePaymentsByVehicleId(@RequestParam String licensePlate);
}
