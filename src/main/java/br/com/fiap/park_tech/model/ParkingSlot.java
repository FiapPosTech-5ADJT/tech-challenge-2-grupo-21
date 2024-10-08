package br.com.fiap.park_tech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "parking_slots")
public class ParkingSlot {
    @Id
    private Long id;
    private boolean available;
    private Vehicle vehicle;
    private ParkingMeter parkingMeter;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
