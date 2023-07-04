package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.CreateTour;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.service.TourAdministratorService;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.impl.JwtService;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( value = "/TourAdministrator" )
public class TourAdministratorController {
    
    private final TourAdministratorService tourAdministratorService;
    private final ValidationForTour validationForTour;
    private final JwtService jwtService;
    private final TourService tourService;
    
    @Autowired
    public TourAdministratorController(TourAdministratorService tourAdministratorService, ValidationForTour validationForTour, JwtService jwtService, TourService tourService) {
        this.tourAdministratorService = tourAdministratorService;
        this.validationForTour = validationForTour;
        this.jwtService = jwtService;
        this.tourService = tourService;
    }
    
    @GetMapping("/logout")
    public String logout (@RequestHeader(value = "Authorization") String authorizationToken) {
        jwtService.invalidateToken(authorizationToken.substring(7));
        return authorizationToken;
    }
    
    @PostMapping("/addTour")
    public String addTour(@RequestBody CreateTour createTour) {
        if (validationForTour.isValidTour(new Tour(createTour))) {
            return tourAdministratorService.addTour(new Tour(createTour));
        }
        return "Invalid Tour";
    }
    
    @PutMapping("/editTour")
    public String editTour(@RequestBody CreateTour createTour) {
        if (validationForTour.isValidTour(new Tour(createTour))) {
            return tourAdministratorService.editTour(new Tour(createTour));
        }
        return "Invalid Tour";
    }
    
    @PutMapping("/removeTour")
    public String removeTour(@RequestBody CreateTour createTour) {
        if (validationForTour.isValidTour(new Tour(createTour))) {
            return tourAdministratorService.removeTour(new Tour(createTour));
        }
        return "Invalid Tour";
    }
    
    @GetMapping("/getAllTours")
    public List<Tour>  getAllTours(){
        return tourService.getAll();
    }
    
    @GetMapping("/getAllUserInToursOfTour")
    public List<UserInTour>  getAllUserInToursOfTour(@RequestBody Tour tour){
        return tourAdministratorService.getAllUserInToursOfTour(tour);
    }
}