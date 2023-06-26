package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.pojos.*;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( value = "/User" )
public class UserController {
    
    final String EMAIL_REGEXP = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";

    private final UserService userService;
    private final TourService tourService;
    
    @Autowired
    public UserController (UserService userService, TourService tourService) {
        this.userService = userService;
        this.tourService = tourService;
    }
    
    @GetMapping( "/activeTours")
    public List <Tour> getActiveTours () {
        return tourService.getAll();
    }
    
    @GetMapping( "/tourHistory")
    public List <Tour> getTourHistory (String email) {
        if (!email.matches(EMAIL_REGEXP))
            throw new IllegalArgumentException("Invalid email");
        Long userId = userService.getIdByEmail(email);
        if(userId == null)
            throw new IllegalArgumentException("No Such User");
        return userService.getHistoryOfTours(userId);
    }
    
    @GetMapping ( value = "/getInfo" )
    public User getInfo (String email) {
        if (!email.matches(EMAIL_REGEXP))
            throw new IllegalArgumentException("Invalid email");
        Long userId = userService.getIdByEmail(email);
        if(userId == null)
            throw new IllegalArgumentException("No Such User");
        return userService.getInfo(userId);
    }

    @PutMapping ( "/editInfo" )
    public String editInfo (@Valid @RequestBody @NonNull EditInfo editInfo) {
        return userService.editInfo(editInfo);
    }
    
    @PutMapping ( "/changePassword" )
    public String changePassword (@Valid @RequestBody @NonNull PasswordChange passwordChange) {
        if (passwordChange.getNew1().equals(passwordChange.getNew2())) {
            return userService.passwordChange(passwordChange.getSignIn(), passwordChange.getNew1());
        } else {
            return "New Passwords Are Not Equal";
        }
    }
    
    @GetMapping ( "/forgotPassword" )
    public String forgotPassword (String email) {
        if (!email.matches(EMAIL_REGEXP))
            throw new IllegalArgumentException("Invalid email");
        return userService.forgotPassword(email);
    }

    @PutMapping ( "/changeForgotPassword" )
    public String changeForgotPassword (@Valid @RequestBody @NonNull ForgotPasswordChange forgotPasswordChange) {
        if (forgotPasswordChange.getNew1().equals(forgotPasswordChange.getNew2())) {
            return userService.forgotPasswordChange(forgotPasswordChange.getEmail(), forgotPasswordChange.getNew1());
        } else {
            return "New Passwords Are Not Equal";
        }
    }
    
    @PutMapping ( "/changeEmail" )
    public String changeEmail (@Valid @RequestBody @NonNull EmailChange emailChange) {
        return userService.changeEmail(emailChange.getSignIn(), emailChange.getEmail());
    }
    
    @PutMapping ( "/changePhoneNumber" )
    public String changePhoneNumber (@Valid @RequestBody @NonNull PhoneNumberChange phoneNumberChange) {
        return userService.changePhoneNumber(phoneNumberChange.getSignIn(), phoneNumberChange.getPhoneNumber());
    }
    
    @PostMapping("/bookTour")
    public String bookTour (@Valid @RequestBody @NonNull UserInTour userInTour) {
        return userService.bookTour(userInTour);
    }
    
    @PostMapping("/editTour")
    public String editTour (@Valid @RequestBody @NonNull UserInTour userInTour) {
        return userService.editTour(userInTour);
    }
    
    @PutMapping("/cancelTour")
    public String cancelTour (@Valid @RequestBody @NonNull UserInTour userInTour) {
        return userService.cancelTour(userInTour);
    }
    
    @PutMapping("/leaveReview")
    public String leaveReview (@Valid @RequestBody @NonNull UserInTour userInTour) {
        return userService.leaveReview(userInTour);
    }
    
    @PostMapping("/addCard")
    public String addCard (@Valid @RequestBody @NonNull CardForUser cardForUser, @Valid @RequestBody @NonNull User user) {
        return userService.addCard(cardForUser, user);
    }
    
    @PutMapping("/deleteCard")
    public String deleteCard (@Valid @RequestBody @NonNull CardForUser cardForUser, @Valid @RequestBody @NonNull User user) {
        return userService.deleteCard(cardForUser, user);
    }
    //TODO logout
    @GetMapping("/logout")
    public String logout (@Valid @RequestBody @NonNull User user) {
        return userService.logout(user);
    }
}