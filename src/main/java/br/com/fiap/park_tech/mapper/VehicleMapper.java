package br.com.fiap.park_tech.mapper;

import br.com.fiap.park_tech.dto.VehicleDTO;
import br.com.fiap.park_tech.dto.VehicleResponseDTO;
import br.com.fiap.park_tech.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle toEntity(VehicleDTO dto);
    VehicleResponseDTO toResponseDTO(Vehicle vehicle);
    Vehicle toEntity(VehicleResponseDTO dto);
}
