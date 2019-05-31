package io.ifelsecoders.cars.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private String plateNumber;
}
