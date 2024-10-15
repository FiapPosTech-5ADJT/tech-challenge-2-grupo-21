package br.com.fiap.park_tech.controller.docs;

import br.com.fiap.park_tech.dto.EndParkingSessionDTO;
import br.com.fiap.park_tech.dto.ParkingSessionDTO;
import br.com.fiap.park_tech.model.ParkingSession;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Parking Session Controller", description = "Operations related to parking sessions")
public interface ParkingSessionControllerDocs {

    @Operation(summary = "Create a new parking session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parking session created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParkingSessionDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Parking slot not found",
                    content = @Content)
    })
    @PostMapping("/start")
    ResponseEntity<ParkingSession> createParkingSession(@RequestBody ParkingSessionDTO parkingSessionDTO);

    @Operation(summary = "End an existing parking session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking session ended",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParkingSession.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Parking session not found",
                    content = @Content)
    })
    @PostMapping("/end")
    ResponseEntity<ParkingSession> endParkingSession(@RequestParam EndParkingSessionDTO endParkingSessionDTO);
}
