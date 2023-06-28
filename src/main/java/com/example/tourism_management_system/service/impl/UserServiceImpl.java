package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.model.entities.*;
import com.example.tourism_management_system.model.pojos.*;
import com.example.tourism_management_system.repository.UserRepository;
import com.example.tourism_management_system.service.*;
import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationForTour validationForTour = new ValidationForTour();
    private final TransactionService transactionService;
    private final UserInTourService userInTourService;
    private final JwtService jwtService;
    private final TourService tourService;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TransactionService transactionService, UserInTourService userInTourService, JwtService jwtService, TourService tourService, RoleService roleService) {
        this.userRepository = userRepository;
        this.transactionService = transactionService;
        this.userInTourService = userInTourService;
        this.jwtService = jwtService;
        this.tourService = tourService;
        this.roleService = roleService;
    }
    
    @Override
    public Long getIdByEmail (String email) {
        Optional<UserEntity> op = userRepository.findByEmail(email);
        return op.map(UserEntity::getId).orElse(null);
    }

    @Override
    public String signUp(final User user) {
        UserEntity userEntity = new UserEntity(user);
        Optional<UserEntity> op1 = userRepository.findByEmail(user.getEmail());
        Optional<UserEntity> op2 = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (op1.isPresent()) {
            throw new IllegalArgumentException("This Email Is Already In Use: " + op1.get().getEmail());
        }
        if (op2.isPresent()) {
            throw new IllegalArgumentException("This Phone Number Is Already In Use: " + op2.get().getPhoneNumber());
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setAdminRole(false);
        roleEntity.setUserRole(true);
        userEntity.setRoleEntity(roleEntity);
        roleService.saveRole(roleEntity);
        userRepository.save(userEntity);
        return "Success";
    }

    @Override
    public String editInfo(final EditInfo editInfo, final String email) {
        boolean t = false;
        if (editInfo.getFirstName() != null) {
            t = true;
            userRepository.updateFirstName(editInfo.getFirstName(), email);
        }
        if (editInfo.getLastName() != null) {
            t = true;
            userRepository.updateLastName(editInfo.getLastName(), email);
        }
        if (editInfo.getBirthDate() != null) {
            t = true;
            userRepository.updateBirthDate(editInfo.getBirthDate(), email);
        }
        if (t) {
            return "Edited Successfully";
        }
        throw new IllegalArgumentException("All Fields Are Null");
    }
    
    @Override
    public String passwordChange(String email, String password) {
        password = new BCryptPasswordEncoder().encode(password);
        return userRepository.resetPassword(email, password);
    }
    
    //TODO: return is not used?
    @Override
    public String forgotPassword(String email) {
        userRepository.forgotPassword(email);
        return jwtService.generateToken(email);
    }

    @Override
    public String forgotPasswordChange(String email, String password) {
        password = new BCryptPasswordEncoder().encode(password);
        return userRepository.resetPassword(email, password);
    }

    @Override
    public String bookTour(UserInTour userInTour) {
        if (validationForTour.isEnableForBooking(userInTour.getTour(), userInTour.getQuantity())){
            if (userInTour.getUser().getCardForUser() == null){
                throw new IllegalArgumentException("There Is No Card For This User");
            }
            String transactionNumber = transactionService.makeTransaction(new Card(userInTour.getUser().getCardForUser()), userInTour.getPrice());
            if(transactionNumber == null || transactionNumber.equals("Not Successful")){
                throw new IllegalArgumentException("Not Successful Transaction Please Try Again");
            }
            userInTour.setTransactionNumber(transactionNumber);
            userInTourService.save(userInTour);
            return "Successful";
        }
        throw new IllegalArgumentException("Not Enable For Booking");
    }
    
    //TODO
    @Override
    public String editTour(UserInTour userInTour) {
        return null;
    }

    @Override
    public String cancelTour(UserInTour userInTour) {
        if(validationForTour.isEnableForCanceling(userInTour.getTour())){
            String result = transactionService.revertTransaction(userInTour.getTransactionNumber());
            if(result.equals("Success")){
                return userInTourService.cancel(userInTour);
            }
            return result;
        }
        throw new IllegalArgumentException("Not Available For Canceling");
    }
    
    //TODO
    @Override
    public String leaveReview(LeaveReview leaveReview, String email) {
        return null;
    }
    
    @Override
    public List <Tour> getHistoryOfTours (String email) {
        List<Tour> tours = new ArrayList <>();
        List<UserInTour> userInTours = userInTourService.findByUserId(getIdByEmail(email));
        if (userInTours == null)
            return Collections.emptyList();
        for (UserInTour userInTour : userInTours) {
            tours.add(userInTour.getTour());
        }
        return tours;
    }
    //TODO
    @Override
    public String logout (User user) {
        return null;
    }
    
    @Override
    public User getInfo (String email) {
        Optional<UserEntity> op = userRepository.findByEmail(email);
        return op.map(User::new).orElse(null);
    }
    
    @Override
    public String changeEmail (String email, String newEmail) {
        return userRepository.updateEmail(email, newEmail);
    }
    
    @Override
    public String changePhoneNumber (String email, String newPhoneNumber) {
        return userRepository.updatePhoneNumber(email, newPhoneNumber);
    }
    //TODO
    @Override
    public String addCard (CardForUser cardForUser, String email) {
        return null;
    }
    //TODO
    @Override
    public String deleteCard (CardForUser cardForUser, String email) {
        return null;
    }
}