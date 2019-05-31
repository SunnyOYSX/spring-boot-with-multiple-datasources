package io.ifelsecoders.cars.repository;

import io.ifelsecoders.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
