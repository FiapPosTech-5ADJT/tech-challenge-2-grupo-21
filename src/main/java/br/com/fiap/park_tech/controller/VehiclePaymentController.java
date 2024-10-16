package br.com.fiap.park_tech.controller;

import br.com.fiap.park_tech.controller.docs.VehiclePaymentControllerDocs;
import br.com.fiap.park_tech.dto.VehiclePaymentResponseDTO;
import br.com.fiap.park_tech.model.VehiclePayment;
import br.com.fiap.park_tech.service.VehiclePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VehiclePaymentController implements VehiclePaymentControllerDocs {

    private final VehiclePaymentService vehiclePaymentService;

    @Override
    public ResponseEntity<List<VehiclePaymentResponseDTO>> getAllVehiclePayments() {
        List<VehiclePaymentResponseDTO> payments = vehiclePaymentService.getAllVehiclePayments();
        return ResponseEntity.ok(payments);
    }

    @Override
    public ResponseEntity<List<VehiclePaymentResponseDTO>> getVehiclePaymentsByVehicleId(String vehicleId) {
        List<VehiclePaymentResponseDTO> payments = vehiclePaymentService.getVehiclePaymentsByVehicleId(vehicleId);
        return ResponseEntity.ok(payments);
    }
}
