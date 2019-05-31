package io.ifelsecoders.trucks.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Truck {

    @Id
    @GeneratedValue
    private Long id;

    private String plateNumber;
}
