package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.AdminService;
import com.example.tourism_management_system.service.impl.JwtService;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( value = "/TourAdministrator" )
public class TourAdministratorController {
    
    private final AdminService adminService;
    ValidationForTour validationForTour = new ValidationForTour();
    private final JwtService jwtService;
    
    @Autowired
    public TourAdministratorController(final AdminService adminService, JwtService jwtService) {
        this.adminService = adminService;
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
            return adminService.addTour(tour);
        }
        return "Invalid Tour";
    }
    
    @PutMapping("/editTour")
    public String editTour(@RequestBody Tour tour) {
        if (validationForTour.isValidTour(tour)) {
            return adminService.editTour(tour);
        }
        return "Invalid Tour";
    }
    
    @PutMapping("/removeTour")
    public String removeTour(@RequestBody Tour tour) {
        if (validationForTour.isValidTour(tour)) {
            return adminService.removeTour(tour);
        }
        return "Invalid Tour";
    }
}