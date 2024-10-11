package br.com.fiap.park_tech.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingSessionDTO {
    private String vehicleLicensePlate;
    private String parkingSlotId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
