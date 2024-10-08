package br.com.fiap.park_tech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "parking_meters")
public class ParkingMeter {
    private Long id;
    private String name;
    private List<ParkingSlot> parkingSlots;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
