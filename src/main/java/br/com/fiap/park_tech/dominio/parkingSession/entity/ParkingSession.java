package br.com.fiap.park_tech.dominio.parkingSession.entity;

import br.com.fiap.park_tech.dominio.vehicle.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ParkingSession {
    private Long id;
    private Vehicle vehicle;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

}
