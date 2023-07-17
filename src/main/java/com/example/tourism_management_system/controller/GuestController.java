package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.AuthRequest;
import com.example.tourism_management_system.model.pojos.SignUpUser;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserService;
import com.example.tourism_management_system.service.impl.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( value = "/Guest" )
public class GuestController {
    
    final String EMAIL_REGEXP = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";
    private final TourService           tourService;
    private final UserService           userService;
    private final JwtService            jwtService;
    private final AuthenticationManager authenticationManager;
    
    @Autowired
    public GuestController (TourService tourService, UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.tourService = tourService;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    
    @GetMapping ( "/getAllActiveTours" )
    public @ResponseBody List <Tour> getAllActiveTours () {
        return tourService.getAllActiveTours();
    }
    
    @GetMapping ( "/tours/{tourId}" )
    public @ResponseBody Tour getTourById (@PathVariable Long tourId) {
        return tourService.getById(tourId);
    }
    
    @PostMapping ( "/signUp" )
    public String signUp (@Valid @RequestBody @NonNull SignUpUser signUpUser) {
        return userService.signUp(signUpUser);
    }
    
    @GetMapping ( "/signIn" )
    public String signIn (@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    
    @GetMapping ( "/forgotPassword" )
    public String forgotPassword (@RequestBody @NonNull String email) {
        if (!email.matches(EMAIL_REGEXP))
            throw new IllegalArgumentException("Invalid email");
        return userService.forgotPassword(email);
    }
    
    @GetMapping("/sortByDate")
    public List<Tour> sortByDate() {
        return tourService.sortByDate();
    }
    
    @GetMapping("/sortByCost")
    public List<Tour> sortByCost() {
        return tourService.sortByCost();
    }
    
    @GetMapping("/sortByDistance")
    public List<Tour> sortByDistance() {
        return tourService.sortByDistance();
    }
    
    @GetMapping("/sortByQuantity")
    public List<Tour> sortByQuantity(){
        return tourService.sortByQuantity();
    }
}