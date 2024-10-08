package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.VehiclePayment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiclePaymentRepository extends MongoRepository<VehiclePayment, Long> {
}
