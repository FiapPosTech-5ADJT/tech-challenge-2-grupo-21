package br.com.fiap.park_tech.dto;

import br.com.fiap.park_tech.enums.PaymentMethods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class VehiclePaymentResponseDTO {
    private String id;
    private String parkingSessionId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentMethods paymentMethod;
}
