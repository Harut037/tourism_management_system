package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.model.pojos.SignIn;

@Service
public interface UserService {
    
    ResponseEntity <User> signup (final User user);
    
    ResponseEntity <User> signIn (final String login, final String password, final int loginChoice);
    
    ResponseEntity <User> editInfo (final User user);
    
    boolean contains (final String string, final char symbol);
    
    ResponseEntity <User> forgotPassword (final String login, final int loginChoice);
    
    int loginType (final String login);
    
    ResponseEntity <User> forgottedPasswordChange (final String login, final String password, final int loginChoice);
    
    ResponseEntity <User> passwordChange (SignIn signIn, String password, int loginChoice);
    
    public void bookTour(UserEntity userEntity, TourEntity tourEntity);

    public void editeTour(UserEntity userEntity,TourEntity tourEntity);

    public void cancelTour(UserEntity userEntity,TourEntity tourEntity);

    public String leaveReview(UserEntity userEntity);


}
