package br.com.fiap.park_tech.model;

import br.com.fiap.park_tech.enums.PaymentMethods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "vehicle_payments")
public class VehiclePayment {
    @Id
    private String id;
    private ParkingSession parkingSession;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentMethods paymentMethod;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    public static VehiclePayment newVehiclePayment(ParkingSession parkingSession, BigDecimal amount, PaymentMethods paymentMethod) {
        return new VehiclePayment(
                null, // id will be generated by MongoDB
                parkingSession,
                amount,
                LocalDateTime.now(), // paymentDate
                PaymentMethods.CREDIT_CARD, // paymentMethod padrão
                Instant.now(), // createdAt
                Instant.now(), // updatedAt
                null // deletedAt
        );
    }
}
