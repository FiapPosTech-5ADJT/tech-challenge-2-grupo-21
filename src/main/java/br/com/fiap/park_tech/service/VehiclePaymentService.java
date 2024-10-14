package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.dto.VehiclePaymentDTO;
import br.com.fiap.park_tech.model.VehiclePayment;

public interface VehiclePaymentService {
    VehiclePayment createVehiclePayment(VehiclePaymentDTO vehiclePayment);
    VehiclePayment getVehiclePaymentById(String vehiclePaymentId);
    void deleteVehiclePaymentById(String vehiclePaymentId);
}
