package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.ParkingSlot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParkingSlotRepository extends MongoRepository<ParkingSlot, String> {
  List<ParkingSlot> findByParkingMeterId(String parkingMeterId);
}
