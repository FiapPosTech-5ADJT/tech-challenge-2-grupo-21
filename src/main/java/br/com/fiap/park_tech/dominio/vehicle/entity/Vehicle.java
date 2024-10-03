package br.com.fiap.park_tech.dominio.vehicle.entity;

import br.com.fiap.park_tech.dominio.parkingSession.entity.ParkingSession;

import java.util.List;

public class Vehicle {
    private Long id;
    private String licensePlate;
    private List<ParkingSession> parkingSessions;

}
