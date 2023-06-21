package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.AdminService;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserService;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( value = "/Admin" )
public class AdminController {
    
    private final AdminService adminService;
    ValidationForTour validationForTour = new ValidationForTour();
    
    @Autowired
    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }
    
    //TODO logout
    
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