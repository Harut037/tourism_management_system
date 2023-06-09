package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.impl.TourServiceImpl;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tours")
public class TourController {


    ValidationForTour vft = new ValidationForTour();

    private final TourServiceImpl tourServiceImpl;

    @Autowired
    public TourController(TourServiceImpl tourServiceImpl) {
        this.tourServiceImpl = tourServiceImpl;
    }

    @PostMapping("/create")
    public String save(@RequestBody Tour tour) {
        if (vft.isValidTour(tour)){
           return tourServiceImpl.save(tour);
        }else
            return "Error";
    }

    @GetMapping("/getAll")
    public List<TourEntity> getAll() {
        return tourServiceImpl.getAll();
    }

    @GetMapping("/get/{tourId}")
    public Optional<TourEntity> getById(@PathVariable("tourId") Long id) {
        return tourServiceImpl.getById(id);
    }

    @DeleteMapping("/delete/{tourId}")
    public String deleteById(@PathVariable("tourId") Long id) {
        return tourServiceImpl.deleteById(id);
    }

    @GetMapping("/get/sort/cost")
    public List<TourEntity> sortByCost() {
        return tourServiceImpl.sortByCost();
    }

    @GetMapping("/get/sort/date")
    public List<TourEntity> sortByDate() {
        return tourServiceImpl.sortByDate();
    }

    @GetMapping("/get/sort/quantity")
    public List<TourEntity> sortByQuantity() {
        return tourServiceImpl.sortByQuantity();
    }

}
