package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( value = "/Home" )
public class GuestController {

    private TourService tourService;

    public GuestController() {}

    @Autowired
    public GuestController(TourService tourService){
        this.tourService = tourService;
    }

    @GetMapping( value = "/" )
    public @ResponseBody ResponseEntity<List<Tour>> home(){
        return new ResponseEntity<>(tourService.getAll(), HttpStatus.FOUND);
    }

    @GetMapping("/tours/{tourId}")
    public @ResponseBody ResponseEntity<Tour> getTourById(@PathVariable Long tourId){
        Tour tour = tourService.getById(tourId);
        if (tour != null){
            return new ResponseEntity<>(tour, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
