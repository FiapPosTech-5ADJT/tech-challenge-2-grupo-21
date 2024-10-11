package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.ParkingMeter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingMeterRepository extends MongoRepository<ParkingMeter, String> {
}
