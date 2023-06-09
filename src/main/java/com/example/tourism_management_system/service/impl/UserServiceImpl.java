package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.*;
import com.example.tourism_management_system.model.pojos.Review;
import com.example.tourism_management_system.model.pojos.SignIn;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.repository.CardRepository;
import com.example.tourism_management_system.repository.UserRepository;
import com.example.tourism_management_system.service.CardService;
import com.example.tourism_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private CardService    cardService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CardService    cardService){
        this.userRepository = userRepository;
        this.cardService = cardService;
    }
    
    @Override
    public ResponseEntity <User> signup (final User user) {
        UserEntity userEntity = new UserEntity(user);
        boolean    t    = false;
        for (int i = 0; i < user.getCards().size(); i++) {
            Optional <UserEntity> op1 = userRepository.findByCardNumber(user.getCards().get(i).getCardNumber());
            t = t || op1.isPresent();
        }
        Optional <UserEntity> op2 = userRepository.findByEmail(user.getEmail());
        Optional <UserEntity> op4 = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (t || op2.isPresent() || op4.isPresent()) {
            return new ResponseEntity <>(HttpStatusCode.valueOf(505));
        }
        userRepository.save(userEntity);
        for (CardEntity i : userEntity.getCardEntities()) {
            i.setUser(userEntity);
        }
        cardService.save(userEntity.getCardEntities());
        return new ResponseEntity <>(HttpStatusCode.valueOf(201));
    }
    
    @Override
    public ResponseEntity <User> signIn (final String login, final String password, final int loginChoice) {
        return switch (loginChoice) {
            case 1 -> userRepository.signInViaEmail(login, password);
            case 2 -> userRepository.signInViaPhoneNumber(login, password);
            default -> new ResponseEntity <>(HttpStatusCode.valueOf(501));
        };
    }
    
    @Override
    public ResponseEntity <User> editInfo (final User user) {
        return this.userRepository.update(user);
    }
    
    @Override
    public ResponseEntity <User> forgotPassword (String login, int loginChoice) {
        return switch (loginChoice) {
            case 1 -> userRepository.forgotPasswordViaEmail(login);
            case 2 -> userRepository.forgotPasswordViaPhoneNumber(login);
            default -> new ResponseEntity <>(HttpStatusCode.valueOf(501));
        };
    }
    
    @Override
    public ResponseEntity <User> forgottedPasswordChange (String login, String password, int loginChoice) {
        return switch (loginChoice) {
            case 1 -> userRepository.resetPasswordViaEmail(login, password);
            case 2 -> userRepository.resetPasswordViaPhoneNumber(login, password);
            default -> new ResponseEntity <>(HttpStatusCode.valueOf(501));
        };
    }
    
    @Override
    public ResponseEntity <User> passwordChange (SignIn signIn, String password, int loginChoice) {
        int value = this.signIn(signIn.getLogin(), signIn.getPassword(), loginChoice).getStatusCode().value();
        if (value >= 200 && value < 300) {
            return switch (loginChoice) {
                case 1 -> userRepository.resetPasswordViaEmail(signIn.getLogin(), password);
                case 2 -> userRepository.resetPasswordViaPhoneNumber(signIn.getLogin(), password);
                default -> new ResponseEntity <>(HttpStatusCode.valueOf(501));
            };
        } else if (value >= 400 && value < 500) {
            return new ResponseEntity <>(HttpStatusCode.valueOf(401));
        } else {
            return new ResponseEntity <>(HttpStatusCode.valueOf(501));
        }
    }
    
    @Override
    public boolean contains (final String string, final char symbol) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == symbol) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int loginType (String login) {
        if (this.contains(login, '@')) {
            return 1;
        } else if (this.contains(login, '+')) {
            return 2;
        } else {
            return -1;
        }
    }
    
    @Override
    public ResponseEntity <UserInTour> bookTour (UserInTour userInTour) {
        return null;
    }
    
    @Override
    public ResponseEntity <UserInTour> editTour (UserInTour userInTour) {
        return null;
    }
    
    @Override
    public ResponseEntity <User> cancelTour (UserInTour userInTour) {
        return null;
    }
    
    @Override
    public ResponseEntity <Review> leaveReview (ReviewEntity reviewEntity) {
        return null;
    }
}