package br.com.fiap.park_tech.dominio.parkingMeter.entity;

import br.com.fiap.park_tech.dominio.parkingSlot.entity.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ParkingMeter {
    private Long id;
    private String name;
    private List<ParkingSlot> parkingSlots;
}
