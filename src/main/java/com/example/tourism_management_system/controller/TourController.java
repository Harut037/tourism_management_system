package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.impl.TourServiceImpl;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (vft.isValidTour(tour)) {
            return tourServiceImpl.save(tour);
        } else
            return "Error";
    }
}