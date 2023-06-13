package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.ReviewEntity;
import com.example.tourism_management_system.model.pojos.Review;
import com.example.tourism_management_system.model.pojos.UserInTour;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.model.pojos.SignIn;

@Service
public interface UserService {
    
    void signup(final User user);
    
    void signIn(final String login, final String password, final int loginChoice);
    
    void editInfo(final User user);

    boolean contains(final String string, final char symbol);
    
    void forgotPassword(final String login, final int loginChoice);

    int loginType(final String login);
    
    void forgotPasswordChange(final String login, final String password, final int loginChoice);
    
    void passwordChange(SignIn signIn, String password, int loginChoice);
    
    void bookTour(UserInTour userInTour);
    
    void editTour(UserInTour userInTour);
    
    void cancelTour(UserInTour userInTour);
    
    void leaveReview(ReviewEntity reviewEntity);

}