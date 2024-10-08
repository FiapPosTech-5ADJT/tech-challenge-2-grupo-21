package br.com.fiap.park_tech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
    private Long id;
    private String licensePlate;
    private List<ParkingSession> parkingSessions;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

}
