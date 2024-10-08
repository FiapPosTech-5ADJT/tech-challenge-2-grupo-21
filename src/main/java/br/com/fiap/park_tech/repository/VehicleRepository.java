package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, Long> {
}
