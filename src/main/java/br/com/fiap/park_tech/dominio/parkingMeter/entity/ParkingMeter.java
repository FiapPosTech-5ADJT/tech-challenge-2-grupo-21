package br.com.fiap.park_tech.dominio.parkingMeter.entity;

import br.com.fiap.park_tech.dominio.parkingSlot.entity.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ParkingMeter {
    private Long id;
    private String name;
    private List<ParkingSlot> parkingSlots;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
