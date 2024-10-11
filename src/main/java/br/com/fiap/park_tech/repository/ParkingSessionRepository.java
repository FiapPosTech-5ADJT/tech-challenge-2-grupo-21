package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.ParkingSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ParkingSessionRepository extends MongoRepository<ParkingSession, String> {
    Optional<ParkingSession> findByVehicleId(String vehicleId);
}
