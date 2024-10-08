package br.com.fiap.park_tech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class VehiclePayment {
    @Id
    private Long id;
    private ParkingSession parkingSession;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
