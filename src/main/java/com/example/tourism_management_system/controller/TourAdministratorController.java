package com.example.tourism_management_system.controller;

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
    private final TourService tourService;
    private final ValidationForTour validationForTour;
    private final JwtService jwtService;
    
    @Autowired
    public TourAdministratorController(TourAdministratorService tourAdministratorService, TourService tourService, ValidationForTour validationForTour, JwtService jwtService) {
        this.tourAdministratorService = tourAdministratorService;
        this.tourService = tourService;
        this.validationForTour = validationForTour;
        this.jwtService = jwtService;
    }
    
    @GetMapping("/logout")
    public String logout (@RequestHeader(value = "Authorization") String authorizationToken) {
        jwtService.invalidateToken(authorizationToken.substring(7));
        return authorizationToken;
    }
    
    @PostMapping("/addTour")
    public String addTour(@RequestBody Tour tour) {
        if (validationForTour.isValidTour(tour)) {
            return tourAdministratorService.addTour(tour);
        }
        return "Invalid Tour";
    }
    
    @PutMapping("/editTour")
    public String editTour(@RequestBody Tour tour) {
        if (validationForTour.isValidTour(tour)) {
            return tourAdministratorService.editTour(tour);
        }
        return "Invalid Tour";
    }
    
    @PutMapping("/removeTour")
    public String removeTour(@RequestBody Tour tour) {
        if (validationForTour.isValidTour(tour)) {
            return tourAdministratorService.removeTour(tour);
        }
        return "Invalid Tour";
    }
    
    @GetMapping("/getAllTours")
    public List<Tour>  getAllTours(){
        return tourService.getAll();
    }
    
    //TODO
    @GetMapping("/getAllUserInToursOfTour")
    public List<UserInTour>  getAllUserInToursOfTour(@RequestBody Tour tour){
        return tourAdministratorService.getAllUserInToursOfTour(tour);
    }
}