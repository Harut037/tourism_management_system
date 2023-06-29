package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.service.CardService;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.entities.RoleEntity;
import com.example.tourism_management_system.model.entities.CardEntityForUser;
import com.example.tourism_management_system.model.pojos.*;
import com.example.tourism_management_system.repository.TourRepository;
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

    private final TourRepository tourRepository;
    private final ValidationForTour validationForTour;
    private final TransactionService transactionService;
    private final CardService cardService;
    @Autowired
    private UserInTourService userInTourService;
    private final JwtService jwtService;
    private final RoleService roleService;
    private final CardForUserService cardForUserService;
    private final ReviewService reviewService;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository, TourRepository tourRepository, ValidationForTour validationForTour, TransactionService transactionService, CardService cardService, JwtService jwtService, RoleService roleService, CardForUserService cardForUserService, ReviewService reviewService) {
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
        this.validationForTour = validationForTour;
        this.transactionService = transactionService;
        this.cardService = cardService;
        this.jwtService = jwtService;
        this.roleService = roleService;
        this.cardForUserService = cardForUserService;
        this.reviewService = reviewService;
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
        roleEntity.setTourAdministratorRole(false);
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
    public String bookTour(BookTour bookTour, String email) {
        User user = new User(userRepository.findByEmail(email).get());
        UserInTour userInTour = new UserInTour(user, bookTour.getTour(), bookTour.getQuantity());
        if (validationForTour.isEnableForBooking(bookTour.getTour(), bookTour.getQuantity())) {
            if (userInTour.getUser().getCardForUser() == null) {
                throw new IllegalArgumentException("There Is No Card For This User");
            }
            String transactionNumber = transactionService.makeTransaction(new Card(userInTour.getUser().getCardForUser()), userInTour.getPrice());
            if (transactionNumber == null || transactionNumber.equals("Not Successful")) {
                throw new IllegalArgumentException("Not Successful Transaction Please Try Again");
            }
            userInTour.setTransactionNumber(transactionNumber);
            userInTourService.save(userInTour);
            return "Successful";
        } else tourRepository.updateIsEnableForBooking(bookTour.getTour().getTourName(),bookTour.getTour().getTourDate());
        throw new IllegalArgumentException("Not Enable For Booking");
    }

    @Override
    public String editTour(BookTour bookTour, String email) {
        return null;
    }

//    @Override
//    public String editTour(BookTour bookTour, String email) {
//        if(validationForTour.isEnableForEditing(bookTour)){
//            if(userInTourService.edit(bookTour, email) > 0){
//                return "Successful";
//            }
//            throw new IllegalArgumentException("Error Occurred Please Try Again");
//        }
//        throw new IllegalArgumentException("Not Enable For Editing");
//    }

    @Override
    public String cancelTour(Tour tour, String email) {
        if(validationForTour.isEnableForCanceling(tour)){
            UserInTour userInTour = userInTourService.getUserInTour(tour, email);
            String result = transactionService.revertTransaction(userInTour.getTransactionNumber());
            if(result.equals("Success")){
                return userInTourService.cancel(userInTour);
            }
            throw new IllegalArgumentException("Error For Reverting Transaction Occurs");
        }
        throw new IllegalArgumentException("Not Available For Canceling");
    }
    
    @Override
    public String leaveReview(LeaveReview leaveReview, String email) {
        Long reviewId = reviewService.save(leaveReview.getReview());
        if (userInTourService.addReview(userInTourService.getUserInTour(leaveReview.getTour(), email), reviewId) > 0){
            return "Success";
        }
        throw new IllegalArgumentException("Error Occurred Please Try Again");
    }
    
    @Override
    public List <Tour> getHistoryOfTours (String email) {
        List<Tour> tours = new ArrayList <>();
        List<UserInTour> userInTours = userInTourService.findByUser(userRepository.findByEmail(email).get());
        if (userInTours == null)
            return Collections.emptyList();
        for (UserInTour userInTour : userInTours) {
            tours.add(userInTour.getTour());
        }
        return tours;
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
    
    
    @Override
    public String addCard (CardForUser cardForUser, String email) {
        Boolean isExist = cardService.addCard(cardForUser);
        if (isExist) {
            CardEntityForUser card = cardForUserService.save(cardForUser);
            return userRepository.addCard(card, email);
        }
        throw new IllegalArgumentException("Wrong Card");
    }
    
    @Override
    public String deleteCard (CardForUser cardForUser, String email) {
        if(cardForUserService.deleteCard(cardForUser)) {
            return userRepository.deleteCard(email);
        }
        throw new IllegalArgumentException("Error deleting");
    }
    
    @Override
    public UserEntity getUser (String email) {
        return userRepository.findByEmail(email).get();
    }
}