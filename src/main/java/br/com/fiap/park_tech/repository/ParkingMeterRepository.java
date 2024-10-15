package br.com.fiap.park_tech.repository;

import br.com.fiap.park_tech.model.ParkingMeter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParkingMeterRepository extends MongoRepository<ParkingMeter, String> {

    List<ParkingMeter> findAll();

    ParkingMeter findByName(String name);
}
