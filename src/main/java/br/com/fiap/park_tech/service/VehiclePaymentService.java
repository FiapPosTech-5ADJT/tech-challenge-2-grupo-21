package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.VehiclePaymentDTO;
import br.com.fiap.park_tech.dto.VehiclePaymentResponseDTO;
import br.com.fiap.park_tech.model.VehiclePayment;

import java.util.List;

public interface VehiclePaymentService {
    VehiclePayment createVehiclePayment(VehiclePaymentDTO vehiclePayment);
    VehiclePayment getVehiclePaymentById(String vehiclePaymentId);
    void deleteVehiclePaymentById(String vehiclePaymentId);
    List<VehiclePaymentResponseDTO> getAllVehiclePayments();
    List<VehiclePaymentResponseDTO> getVehiclePaymentsByVehicleId(String vehicleId);
}
