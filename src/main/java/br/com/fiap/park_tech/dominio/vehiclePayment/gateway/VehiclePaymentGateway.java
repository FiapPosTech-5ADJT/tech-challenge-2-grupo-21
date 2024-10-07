package br.com.fiap.park_tech.dominio.vehiclePayment.gateway;

import br.com.fiap.park_tech.dominio.vehiclePayment.entity.VehiclePayment;

public interface VehiclePaymentGateway {
    VehiclePayment createVehiclePayment(VehiclePayment vehiclePayment);
    VehiclePayment getVehiclePaymentById(Long vehiclePaymentId);
    void deleteVehiclePaymentById(Long vehiclePaymentId);
    VehiclePayment makePayment(VehiclePayment vehiclePayment);
}
