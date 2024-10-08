package br.com.fiap.park_tech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    private Long id;
    private String licensePlate;
    private List<ParkingSession> parkingSessions;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

}
