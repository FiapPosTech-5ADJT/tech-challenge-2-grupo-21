package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.ParkingSession;
import br.com.fiap.park_tech.model.VehiclePayment;
import org.springframework.beans.PropertyValues;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehiclePaymentRepository extends MongoRepository<VehiclePayment, String> {
  List<VehiclePayment> findByParkingSession(ParkingSession parkingSession);
}
