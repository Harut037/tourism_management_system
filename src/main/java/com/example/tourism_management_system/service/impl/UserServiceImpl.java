package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.bank.api.service.CardService;
import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.entities.RoleEntity;
import com.example.tourism_management_system.model.entities.CardEntityForUser;
import com.example.tourism_management_system.model.pojos.*;
import com.example.tourism_management_system.repository.UserRepository;
import com.example.tourism_management_system.service.*;
import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.validation.tour.ValidationForCardForUser;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import com.example.tourism_management_system.validation.tour.ValidationForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationForTour validationForTour;
    private final TransactionService transactionService;
    private final CardService cardService;
    @Autowired
    private UserInTourService userInTourService;
    private final JwtService jwtService;
    private final RoleService roleService;
    private final CardForUserService cardForUserService;
    private final ReviewService reviewService;
    private final TourService tourService;
    private final ValidationForCardForUser validationForCardForUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationForTour validationForTour, TransactionService transactionService, CardService cardService, JwtService jwtService, RoleService roleService, CardForUserService cardForUserService, ReviewService reviewService, TourService tourService, ValidationForCardForUser validationForCardForUser) {
        this.userRepository = userRepository;
        this.validationForTour = validationForTour;
        this.transactionService = transactionService;
        this.cardService = cardService;
        this.jwtService = jwtService;
        this.roleService = roleService;
        this.cardForUserService = cardForUserService;
        this.reviewService = reviewService;
        this.tourService = tourService;
        this.validationForCardForUser = validationForCardForUser;
    }

    /**
     * Retrieves the ID of the user associated with the specified email.
     *
     * @param email the email of the user
     * @return the ID of the user, or null if the user is not found
     */
    @Override
    public Long getIdByEmail(String email) {
        Optional<UserEntity> op = userRepository.findByEmail(email);
        return op.map(UserEntity::getId).orElse(null);
    }

    /**
     * Registers a new user based on the provided SignUpUser object.
     *
     * @param signUpUser the SignUpUser object containing the user's registration details
     * @return a string indicating the success of the registration process
     * @throws IllegalArgumentException if the email or phone number is already in use
     */
    @Override
    public String signUp(final SignUpUser signUpUser) {
        ValidationForUser validationForUser = new ValidationForUser();
        validationForUser.isAdult(signUpUser.getBirthDate());
        UserEntity userEntity = new UserEntity(signUpUser);
        Optional<UserEntity> op1 = userRepository.findByEmail(signUpUser.getEmail());
        Optional<UserEntity> op2 = userRepository.findByPhoneNumber(signUpUser.getPhoneNumber());
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

    /**
     * Updates the user's information based on the provided EditInfo object.
     *
     * @param editInfo the EditInfo object containing the updated user information
     * @param email    the email of the user
     * @return a string indicating the success of the update operation
     * @throws IllegalArgumentException if all fields in the EditInfo object are null
     */
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

    /**
     * Changes the password for the user with the specified email.
     *
     * @param email    the email of the user
     * @param password the new password to set
     * @return true if the password change was successful, false otherwise
     */
    @Override
    public Boolean passwordChange(String email, String oldPassword, String newPassword) {
        if(new BCryptPasswordEncoder().encode(oldPassword).equals(userRepository.getPassword(email))){
            newPassword = new BCryptPasswordEncoder().encode(newPassword);
            return userRepository.resetPassword(email, newPassword) > 0;
        }
        throw new IllegalArgumentException("Old Password Is Incorrect");
    }

    /**
     * Initiates the password reset process for the user with the specified email.
     *
     * @param email the email of the user
     * @return a token generated for the password reset process
     * @throws IllegalArgumentException if no user is found with the specified email
     */
    @Override
    public String forgotPassword(String email) {
        Optional<UserEntity> op = userRepository.findByEmail(email);
        if (op.isPresent()) {
            return jwtService.generateToken(email);
        }
        throw new IllegalArgumentException("Not Found User With Such Email");
    }


    /**
     * Resets the password for the user with the specified email.
     *
     * @param email    the email of the user
     * @param password the new password to set
     * @return true if the password reset was successful, false otherwise
     */
    @Override
    public Boolean resetChange(String email, String password) {
        password = new BCryptPasswordEncoder().encode(password);
        return userRepository.resetPassword(email, password) > 0;
    }

    /**
     * Books a tour for the user with the specified email.
     *
     *
     * @param bookTour the tour booking details
     * @param email    the email of the user
     * @return a message indicating the success of the booking
     * @throws IllegalArgumentException if the tour is not enabled for booking, or if the user has no card, or if the transaction is not successful
     */
    @Override
    public String bookTour(BookTour bookTour, String email) {
        TourEntity tourEntity = tourService.getTour(bookTour.getTour());
        User user = new User(userRepository.findByEmail(email).get());
        UserInTour userInTour = new UserInTour(user, new Tour(tourEntity), bookTour.getQuantity());
        if (validationForTour.isEnableForBooking(new Tour(tourEntity), bookTour.getQuantity())) {
            if (user.getCardForUser() == null) {
                throw new IllegalArgumentException("There Is No Card For This User");
            }
            String transactionNumber = transactionService.makeTransaction(cardService.getCard(user.getCardForUser()), userInTour.getPrice());
            if (transactionNumber == null || transactionNumber.equals("You don`t have enough money")) {
                throw new IllegalArgumentException("Not Successful Transaction Please Try Again");
            }
            userInTour.setTransactionNumber(transactionNumber);
            userInTourService.save(userInTour, bookTour.getTour(), email);
            return "Successful";
        }
        throw new IllegalArgumentException("Not Enable For Booking");
    }

    /**
     * Cancels the tour with the specified transaction number.
     *
     * @param transactionNumber the transaction number of the tour to be canceled
     * @return a message indicating the success of the cancellation
     * @throws IllegalArgumentException if the tour is not available for canceling or if there is an error reverting the transaction
     */
    @Override
    public String cancelTour(String transactionNumber) {
        if (validationForTour.isEnableForCanceling(userInTourService.getUserInTour(transactionNumber).getTour())) {
            UserInTour userInTour = userInTourService.getUserInTour(transactionNumber);
            String result = transactionService.revertTransaction(userInTour.getTransactionNumber());
            if (result.equals("Success")) {
                return userInTourService.cancel(userInTour);
            }
            throw new IllegalArgumentException("Error For Reverting Transaction Occurs");
        }
        throw new IllegalArgumentException("Not Available For Canceling");
    }

    /**
     * Allows a user to leave a review for a tour.
     *
     * @param leaveReview the review information provided by the user
     * @param email       the email of the user leaving the review
     * @return a message indicating the success of leaving the review
     * @throws IllegalArgumentException if there is an error while adding the review
     */
    @Override
    public String leaveReview(LeaveReview leaveReview, String email) {
        Long reviewId = reviewService.save(leaveReview.getReview());
        if (userInTourService.addReview(userInTourService.getUserInTour(leaveReview.getTransactionNumber()), reviewId) > 0) {
            return "Success";
        }
        throw new IllegalArgumentException("Error Occurred Please Try Again");
    }

    /**
     * Retrieves the history of tours for a user.
     *
     * @param email the email of the user
     * @return a list of tours representing the user's tour history
     */
    @Override
    public List<Tour> getHistoryOfTours(String email) {
        List<Tour> tours = new ArrayList<>();
        List<UserInTour> userInTours = userInTourService.findByUser(userRepository.findByEmail(email).get());
        if (userInTours == null)
            return Collections.emptyList();
        for (UserInTour userInTour : userInTours) {
            tours.add(userInTour.getTour());
        }
        return tours;
    }

    /**
     * Retrieves the information of a user.
     *
     * @param email the email of the user
     * @return a User object containing the user's information, or null if the user is not found
     */
    @Override
    public User getInfo(String email) {
        Optional<UserEntity> op = userRepository.findByEmail(email);
        return op.map(User::new).orElse(null);
    }

    /**
     * Changes the email address of a user.
     *
     * @param email    the current email address of the user
     * @param newEmail the new email address to be set
     * @return true if the email address is successfully changed, false otherwise
     */
    @Override
    public Boolean changeEmail(String email, String newEmail) {
        return userRepository.updateEmail(email, newEmail) > 0;
    }

    /**
     * Changes the phone number of a user.
     *
     * @param email          the email address of the user
     * @param newPhoneNumber the new phone number to be set
     * @return true if the phone number is successfully changed, false otherwise
     */
    @Override
    public Boolean changePhoneNumber(String email, String newPhoneNumber) {
        return userRepository.updatePhoneNumber(email, newPhoneNumber) > 0;
    }

    /**
     * Adds a new card to the user's account.
     *
     * @param cardForUser the card information to be added
     * @param email       the email address of the user
     * @return true if the card is successfully added, false otherwise
     * @throws IllegalArgumentException if the card information is invalid or already exists
     */
    @Override
    public Boolean addCard(CardForUser cardForUser, String email) {
        if (validationForCardForUser.isValidCard(cardForUser)) {
            boolean isExist = cardService.compareCard(cardForUser);
            if (isExist) {
                CardEntityForUser card = cardForUserService.save(cardForUser);
                return userRepository.addCard(card, email) > 0;
            }
            throw new IllegalArgumentException("Invalid");
        }
        throw new IllegalArgumentException("Wrong Card");
    }

    /**
     * Deletes a card from the user's account.
     *
     * @param cardForUser the card to be deleted
     * @param email       the email address of the user
     * @return true if the card is successfully deleted, false otherwise
     * @throws IllegalArgumentException if there is an error deleting the card
     */
    @Override
    public Boolean deleteCard(CardForUser cardForUser, String email) {
        if (cardForUserService.deleteCard(cardForUser)) {
            return userRepository.deleteCard(email) > 0;
        }
        throw new IllegalArgumentException("Error deleting");
    }

    /**
     * Retrieves a user entity based on the provided email.
     *
     * @param email the email address of the user
     * @return the user entity
     * @throws NoSuchElementException if no user entity is found with the given email
     */
    @Override
    public UserEntity getUser(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        if (userEntityOptional.isPresent()) {
            return userEntityOptional.get();
        } else {
            throw new NoSuchElementException("No user entity found with the email: " + email);
        }
    }
}