package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    
    String signUp(final SignUpUser signUpUser);
    
    String editInfo(final EditInfo editInfo, final String email);
    
    String forgotPassword(final String email);
    
    Boolean resetChange(final String email, final String password);
    
    Boolean passwordChange(String email, String oldPassword, String newPassword);
    
    String bookTour(BookTour bookTour, String email);
    
    String cancelTour(String transactionNumber);
    
    String leaveReview(LeaveReview leaveReview, String email);
    
    Long getIdByEmail (String email);
    
    List <Tour> getHistoryOfTours(String email);
    
    User getInfo (String email);
    
    Boolean changeEmail (String email, String newEmail);
    
    Boolean changePhoneNumber (String email, String phoneNumber);
    
    Boolean addCard (CardForUser cardForUser, String email);
    
    Boolean deleteCard (CardForUser cardForUser, String email);
    
    UserEntity getUser (String email);
}