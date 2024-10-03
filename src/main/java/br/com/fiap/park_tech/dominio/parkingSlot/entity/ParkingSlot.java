package br.com.fiap.park_tech.dominio.parkingSlot.entity;

import br.com.fiap.park_tech.dominio.parkingMeter.entity.ParkingMeter;
import br.com.fiap.park_tech.dominio.vehicle.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ParkingSlot {
    private Long id;
    private boolean available;
    private Vehicle vehicle;
    private ParkingMeter parkingMeter;
}
