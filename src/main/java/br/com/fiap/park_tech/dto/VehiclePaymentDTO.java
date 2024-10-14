package br.com.fiap.park_tech.dto;

import br.com.fiap.park_tech.enums.PaymentMethods;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VehiclePaymentDTO {
    private String parkingSessionId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentMethods paymentMethod;

}
