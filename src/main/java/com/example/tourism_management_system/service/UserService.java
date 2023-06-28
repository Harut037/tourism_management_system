package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.pojos.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    
    String signUp(final User user);
    
    String editInfo(final EditInfo editInfo, final String email);
    
    String forgotPassword(final String email);
    
    String forgotPasswordChange(final String email, final String password);
    
    String passwordChange(String email, String password);
    
    String bookTour(UserInTour userInTour);
    
    String editTour(UserInTour userInTour);
    
    String cancelTour(UserInTour userInTour);
    
    String leaveReview(LeaveReview leaveReview, String email);
    
    Long getIdByEmail (String email);
    
    List <Tour> getHistoryOfTours(String email);
    
    String logout(User user);
    
    User getInfo (String email);
    
    String changeEmail (String email, String newEmail);
    
    String changePhoneNumber (String email, String phoneNumber);
    
    String addCard (CardForUser cardForUser, String email);
    
    String deleteCard (CardForUser cardForUser, String email);
}