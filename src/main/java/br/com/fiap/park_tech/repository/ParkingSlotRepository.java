package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.ParkingSlot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, Long> {
}
