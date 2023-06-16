package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping( value = "/Home" )
public class GuestController {

    private final TourService tourService;
    private final UserService userService;
    
    @Autowired
    public GuestController(TourService tourService, UserService userService){
        this.tourService = tourService;
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody List<Tour> home(){
        return tourService.getAll();
    }

    @GetMapping("/tours/{tourId}")
    public @ResponseBody Tour getTourById(@PathVariable Long tourId){
        return tourService.getById(tourId);
    }
    
    @GetMapping("/CouldNotSignUp")
    public String couldNotSignUp(){
        return "Couldn't sign up";
    }
    
    @PostMapping ("/signUp" )
    public RedirectView signUp (@Valid @RequestBody @NonNull User user) {
        if (userService.signUp(user).equals("Success")) {
            return new RedirectView("/User/profile");
        }
        return new RedirectView("/Home/CouldNotSignUp");
    }
}