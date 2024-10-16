package br.com.fiap.park_tech.mapper;

import br.com.fiap.park_tech.dto.VehiclePaymentResponseDTO;
import br.com.fiap.park_tech.model.VehiclePayment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehiclePaymentMapper {
    VehiclePaymentResponseDTO toResponseDTO(VehiclePayment vehiclePayment);
}
