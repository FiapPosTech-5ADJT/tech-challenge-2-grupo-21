package br.com.fiap.park_tech.service;

import br.com.fiap.park_tech.model.VehiclePayment;

public interface VehiclePaymentService {
    VehiclePayment createVehiclePayment(VehiclePayment vehiclePayment);
    VehiclePayment getVehiclePaymentById(Long vehiclePaymentId);
    void deleteVehiclePaymentById(Long vehiclePaymentId);
    VehiclePayment makePayment(VehiclePayment vehiclePayment);
}
