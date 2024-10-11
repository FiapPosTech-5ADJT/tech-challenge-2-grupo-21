package br.com.fiap.park_tech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ParkingSession {
    @Id
    private String id;
    private Vehicle vehicle;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

}
