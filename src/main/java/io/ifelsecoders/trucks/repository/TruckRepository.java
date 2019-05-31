package io.ifelsecoders.trucks.repository;

import io.ifelsecoders.trucks.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Long> {
}
