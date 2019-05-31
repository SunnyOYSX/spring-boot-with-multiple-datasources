package io.ifelsecoders.trucks.controller;

import io.ifelsecoders.trucks.entity.Truck;
import io.ifelsecoders.trucks.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckRepository truckRepository;

    @GetMapping
    public List<Truck> getTrucks() {
        return truckRepository.findAll();
    }

}
