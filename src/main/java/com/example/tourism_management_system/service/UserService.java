package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.ReviewEntity;
import com.example.tourism_management_system.model.pojos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    
    String signUp(final User user);
    
    String signIn(final String email, final String password);
    
    String editInfo(final EditInfo editInfo);
    
    String forgotPassword(final String email);
    
    String forgotPasswordChange(final String email, final String password);
    
    String passwordChange(SignIn signIn, String password);
    
    void bookTour(UserInTour userInTour);
    
    void editTour(UserInTour userInTour);
    
    void cancelTour(UserInTour userInTour);
    
    void leaveReview(ReviewEntity reviewEntity);
    
    List <Tour> getHistoryOfTours(Long userId);
    
    void logout(Long userId);
    
    Long getIdByEmail (String email);
    
    User getInfo (Long userId);
    
    String changeEmail (SignIn signIn, String email);
}