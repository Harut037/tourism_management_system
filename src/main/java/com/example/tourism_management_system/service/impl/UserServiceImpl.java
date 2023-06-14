package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.*;
import com.example.tourism_management_system.model.pojos.SignIn;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.repository.UserRepository;
import com.example.tourism_management_system.service.*;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CardService cardService;
    private final ValidationForTour validationForTour = new ValidationForTour();
    private final TransactionService transactionService;
    private final UserInTourService userInTourService;
    
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CardService cardService, TransactionService transactionService, UserInTourService userInTourService, RoleService roleService) {
        this.userRepository = userRepository;
        this.cardService = cardService;
        this.transactionService = transactionService;
        this.userInTourService = userInTourService;
        this.roleService = roleService;
    }

    @Override
    public String signUp(final User user) {
        UserEntity userEntity = new UserEntity(user);
        boolean t = false;
//        for (int i = 0; i < user.getCards().size(); i++) {
//            Optional<UserEntity> op1 = userRepository.findByCardNumber(user.getCards().get(i).getCardNumber());
//            t = t || op1.isPresent();
//        }
        Optional<UserEntity> op2 = userRepository.findByEmail(user.getEmail());
        Optional<UserEntity> op4 = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (t || op2.isPresent() || op4.isPresent()) {
            return "Denied";
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setAdminRole(false);
        roleEntity.setUserRole(true);
        roleEntity.setTourAdministratorRole(false);
        roleEntity.setSupportRole(false);
        userEntity.setRoleEntity(roleEntity);
        roleService.saveRole(roleEntity);
        userRepository.save(userEntity);
        return "Success";
    }

    @Override
    public void signIn(final String login, final String password, final int loginChoice) {
        switch (loginChoice) {
            case 1 -> userRepository.signInViaEmail(login, password);
            case 2 -> userRepository.signInViaPhoneNumber(login, password);
            default -> new ResponseEntity<>(HttpStatusCode.valueOf(501));
        };
    }

    @Override
    public void editInfo(final User user) {
        this.userRepository.update(user);
    }

    @Override
    public void forgotPassword(String login, int loginChoice) {
        switch (loginChoice) {
            case 1 -> userRepository.forgotPasswordViaEmail(login);
            case 2 -> userRepository.forgotPasswordViaPhoneNumber(login);
            default -> new ResponseEntity<>(HttpStatusCode.valueOf(501));
        };
    }

    @Override
    public void forgotPasswordChange(String login, String password, int loginChoice) {
        switch (loginChoice) {
            case 1 -> userRepository.resetPasswordViaEmail(login, password);
            case 2 -> userRepository.resetPasswordViaPhoneNumber(login, password);
            default -> new ResponseEntity<>(HttpStatusCode.valueOf(501));
        };
    }

    @Override
    public void passwordChange(SignIn signIn, String password, int loginChoice) {
        int value = 0;//this.signIn(signIn.getLogin(), signIn.getPassword(), loginChoice).getStatusCode().value();
        if (value >= 200 && value < 300) {
            switch (loginChoice) {
                case 1 -> userRepository.resetPasswordViaEmail(signIn.getLogin(), password);
                case 2 -> userRepository.resetPasswordViaPhoneNumber(signIn.getLogin(), password);
                default -> new ResponseEntity<>(HttpStatusCode.valueOf(501));
            };
        } else if (value >= 400 && value < 500) {
            return ;
        } else {
            return ;
        }
    }

    @Override
    public boolean contains(final String string, final char symbol) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == symbol) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int loginType(String login) {
        if (this.contains(login, '@')) {
            return 1;
        } else if (this.contains(login, '+')) {
            return 2;
        } else {
            return -1;
        }
    }

    @Override
    public void bookTour(UserInTour userInTour) {
        if (validationForTour.isEnableForBooking(userInTour.getTour(), userInTour.getQuantity())){
            if (transactionService.makeTransaction(userInTour.getTransaction())){
               userInTourService.save(userInTour);
            }
            return ;
        }
        return ;
    }

    @Override
    public void editTour(UserInTour userInTour) {
    
    }

    @Override
    public void cancelTour(UserInTour userInTour) {
    
    }

    @Override
    public void leaveReview(ReviewEntity reviewEntity) {
    
    }
    
    @Override
    public void getHistoryOfTours (Long userId) {
    
    }
    
    @Override
    public void logout (Long userId) {
    
    }
}