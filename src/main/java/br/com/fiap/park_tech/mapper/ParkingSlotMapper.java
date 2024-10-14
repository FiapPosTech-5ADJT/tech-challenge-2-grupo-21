package br.com.fiap.park_tech.mapper;

import br.com.fiap.park_tech.dto.ParkingSlotResponseDTO;
import br.com.fiap.park_tech.model.ParkingSlot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkingSlotMapper {
    ParkingSlotResponseDTO toResponseDTO(ParkingSlot parkingSlot);
}
