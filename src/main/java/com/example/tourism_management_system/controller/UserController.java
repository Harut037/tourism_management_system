package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.*;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserService;
import com.example.tourism_management_system.service.impl.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( value = "/User" )
public class UserController {
    
    final String EMAIL_REGEXP = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";
    final String PHONE_NUMBER_REGEXP = "\\+374\\d{8}";

    private final UserService userService;
    private final TourService tourService;
    private final JwtService jwtService;
    
    @Autowired
    public UserController (UserService userService, TourService tourService, JwtService jwtService) {
        this.userService = userService;
        this.tourService = tourService;
        this.jwtService = jwtService;
    }
    
    @GetMapping( "/tourHistory")
    public List <Tour> getTourHistory (@RequestHeader(value = "Authorization") String authorizationToken) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.getHistoryOfTours(email);
    }
    
    @GetMapping ( value = "/getInfo" )
    public User getInfo (@RequestHeader(value = "Authorization") String authorizationToken) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.getInfo(email);
    }

    @PutMapping ( "/editInfo" )
    public String editInfo (@RequestHeader(value = "Authorization") String authorizationToken,
                            @Valid @RequestBody @NonNull EditInfo editInfo) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.editInfo(editInfo, email);
    }
    
    @PutMapping ( "/changePassword" )
    public String changePassword (@RequestHeader(value = "Authorization") String authorizationToken,
                                  @Valid @RequestBody @NonNull PasswordChange passwordChange) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        if (passwordChange.getNew1().equals(passwordChange.getNew2())) {
            return userService.passwordChange(email, passwordChange.getNew1());
        } else {
            throw new IllegalArgumentException("New Passwords Are Not Equal");
        }
    }
    
    @GetMapping ( "/forgotPassword" )
    public String forgotPassword (String email) {
        if (!email.matches(EMAIL_REGEXP))
            throw new IllegalArgumentException("Invalid email");
        return userService.forgotPassword(email);
    }

    @PutMapping ( "/changeForgotPassword" )
    public String changeForgotPassword (@RequestHeader(value = "Authorization") String authorizationToken,
                                        @Valid @RequestBody @NonNull ForgotPasswordChange forgotPasswordChange) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        if (forgotPasswordChange.getNew1().equals(forgotPasswordChange.getNew2())) {
            return userService.forgotPasswordChange(email, forgotPasswordChange.getNew1());
        } else {
            throw new IllegalArgumentException("New Passwords Are Not Equal");
        }
    }
    
    @PutMapping ( "/changeEmail" )
    public String changeEmail (@RequestHeader(value = "Authorization") String authorizationToken,
                               @NonNull String newEmail) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        if (!newEmail.matches(EMAIL_REGEXP))
            throw new IllegalArgumentException("Invalid email");
        return userService.changeEmail(email, newEmail);
    }
    
    @PutMapping ( "/changePhoneNumber" )
    public String changePhoneNumber (@RequestHeader(value = "Authorization") String authorizationToken,
                                     @Valid @RequestBody @NonNull String newPhoneNumber) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        if (!newPhoneNumber.matches(PHONE_NUMBER_REGEXP))
            throw new IllegalArgumentException("Invalid PHONE NUMBER");
        return userService.changePhoneNumber(email, newPhoneNumber);
    }
    
    @PostMapping("/bookTour")
    public String bookTour (@RequestHeader(value = "Authorization") String authorizationToken,
                            @Valid @RequestBody @NonNull UserInTour userInTour) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.bookTour(userInTour);
    }
    
    @PostMapping("/editTour")
    public String editTour (@RequestHeader(value = "Authorization") String authorizationToken,
                            @Valid @RequestBody @NonNull UserInTour userInTour) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.editTour(userInTour);
    }
    
    @PutMapping("/cancelTour")
    public String cancelTour (@RequestHeader(value = "Authorization") String authorizationToken,
                              @Valid @RequestBody @NonNull UserInTour userInTour) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.cancelTour(userInTour);
    }
    
    @PutMapping("/leaveReview")
    public String leaveReview (@RequestHeader(value = "Authorization") String authorizationToken,
                               @Valid @RequestBody @NonNull LeaveReview leaveReview) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.leaveReview(leaveReview, email);
    }
    
    @PostMapping("/addCard")
    public String addCard (@RequestHeader(value = "Authorization") String authorizationToken,
                           @Valid @RequestBody @NonNull CardForUser cardForUser,
                           @Valid @RequestBody @NonNull User user) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.addCard(cardForUser, email);
    }
    
    @PutMapping("/deleteCard")
    public String deleteCard (@RequestHeader(value = "Authorization") String authorizationToken,
                              @Valid @RequestBody @NonNull CardForUser cardForUser) {
        String email = jwtService.extractUsername(authorizationToken.substring(7));
        if (email == null)
            throw new UsernameNotFoundException("No Such User");
        return userService.deleteCard(cardForUser, email);
    }
    
    //TODO logout
    @GetMapping("/logout")
    public String logout (@Valid @RequestBody @NonNull User user) {
        return userService.logout(user);
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