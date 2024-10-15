package br.com.fiap.park_tech.mapper;

import br.com.fiap.park_tech.dto.ParkingMeterDTO;
import br.com.fiap.park_tech.dto.ParkingMeterResponseDTO;
import br.com.fiap.park_tech.model.ParkingMeter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingMeterMapper {
    ParkingMeter toEntity(ParkingMeterDTO dto);
    ParkingMeterResponseDTO toResponseDTO(ParkingMeter parkingMeter);
}
