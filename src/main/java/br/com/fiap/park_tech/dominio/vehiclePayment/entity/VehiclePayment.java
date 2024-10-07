package br.com.fiap.park_tech.dominio.vehiclePayment.entity;

import br.com.fiap.park_tech.dominio.parkingSession.entity.ParkingSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class VehiclePayment {
    private Long id;
    private ParkingSession parkingSession;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
