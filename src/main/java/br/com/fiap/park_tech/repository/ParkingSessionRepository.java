package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.ParkingSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingSessionRepository extends MongoRepository<ParkingSession, Long> {
}
