package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.bank.api.service.CardService;
import com.example.tourism_management_system.model.entities.*;
import com.example.tourism_management_system.model.pojos.*;
import com.example.tourism_management_system.repository.UserRepository;
import com.example.tourism_management_system.service.*;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
//        roleEntity.setTourAdministratorRole(false);
//        roleEntity.setSupportRole(false);
        userEntity.setRoleEntity(roleEntity);
        roleService.saveRole(roleEntity);
        userRepository.save(userEntity);
        return "Success";
    }

    @Override
    public String signIn(final String email, final String password) {
        return userRepository.signIn(email, password);
    }

    @Override
    public String editInfo(final EditInfo editInfo) {
        Optional<UserEntity> op = userRepository.findByEmail(editInfo.getEmail());
        if (op.isEmpty()) {
            return "User Not Found";
        }
        return userRepository.update(editInfo);
    }
    
    @Override
    public String passwordChange(SignIn signIn, String password) {
        String result = this.signIn(signIn.getEmail(), password);
        if (result.equals("Success")) {
            return userRepository.resetPassword(signIn.getEmail(), password);
        }
        return result;
    }

    @Override
    public String forgotPassword(String email) {
        return userRepository.forgotPassword(email);
    }

    @Override
    public String forgotPasswordChange(String email, String password) {
        return userRepository.resetPassword(email, password);
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
    public List <Tour> getHistoryOfTours (Long userId) {
        List<Tour> tours = new ArrayList <>();
        List<UserInTour> userInTours = userInTourService.findByUserId(userId);
        if (userInTours == null)
            return Collections.emptyList();
        for (UserInTour userInTour : userInTours) {
            tours.add(userInTour.getTour());
        }
        return tours;
    }
    
    @Override
    public void logout (Long userId) {
    
    }
    
    @Override
    public Long getIdByEmail (String email) {
        Optional<UserEntity> op = userRepository.findByEmail(email);
        return op.map(UserEntity::getId).orElse(null);
    }
    
    @Override
    public User getInfo (Long userId) {
        Optional<UserEntity> op = userRepository.findById(userId);
        return op.map(User::new).orElse(null);
    }
    
    @Override
    public String changeEmail (SignIn signIn, String newEmail) {
        String result = this.signIn(signIn.getEmail(), signIn.getPassword());
        if (result.equals("Success")){
            return userRepository.updateEmail(signIn.getEmail(), newEmail);
        }
        return result;
    }
}