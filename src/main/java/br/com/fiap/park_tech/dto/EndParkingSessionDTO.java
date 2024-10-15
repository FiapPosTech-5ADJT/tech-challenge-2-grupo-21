package br.com.fiap.park_tech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EndParkingSessionDTO {
    @Schema(description = "ID of the parking session", example = "12345")
    private String parkingSessionId;

    @Schema(description = "Payment method used to end the parking session. Allowed values: Crédito, Débito, PIX, Dinheiro", example = "Crédito")
    private String paymentMethod;
}
