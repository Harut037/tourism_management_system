package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.SignIn;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.repository.UserRepository;
import com.example.tourism_management_system.service.UserInTourService;
import com.example.tourism_management_system.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith ( SpringRunner.class)
@DataJpaTest
class UserServiceImplTest {
    
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserInTourService userInTourService;
    
    @Autowired
    UserServiceImplTest (UserService userService, UserRepository userRepository, UserInTourService userInTourService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userInTourService = userInTourService;
    }
    
    @Test
    public void testChangePhoneNumber_Success() {
        SignIn signIn = new SignIn("example@example.com", "password");
        String signInResult = userService.signIn(signIn.getEmail(), signIn.getPassword());
        assertEquals("Success", signInResult);
        String newPhoneNumber = "1234567890";
        String changePhoneNumberResult = userService.changePhoneNumber(signIn, newPhoneNumber);
        assertEquals("Phone number updated", changePhoneNumberResult);
    }

    @Test
    public void testChangePhoneNumber_Failure() {
        SignIn signIn = new SignIn("example@example.com", "wrongPassword");
        String signInResult = userService.signIn(signIn.getEmail(), signIn.getPassword());
        assertEquals("Invalid credentials", signInResult);
        String newPhoneNumber = "1234567890";
        String changePhoneNumberResult = userService.changePhoneNumber(signIn, newPhoneNumber);
        assertEquals("Invalid credentials", changePhoneNumberResult);
    }

    @Test
    public void testChangeEmail_Success() {
        SignIn signIn = new SignIn("example@example.com", "password");
        String signInResult = userService.signIn(signIn.getEmail(), signIn.getPassword());
        assertEquals("Success", signInResult);
        String newEmail = "newexample@example.com";
        String changeEmailResult = userService.changeEmail(signIn, newEmail);
        assertEquals("Email updated", changeEmailResult);
    }

    @Test
    public void testChangeEmail_Failure() {
        SignIn signIn = new SignIn("example@example.com", "wrongPassword");
        String signInResult = userService.signIn(signIn.getEmail(), signIn.getPassword());
        assertEquals("Invalid credentials", signInResult);
        String newEmail = "newexample@example.com";
        String changeEmailResult = userService.changeEmail(signIn, newEmail);
        assertEquals("Invalid credentials", changeEmailResult);
    }

    @Test
    public void testGetInfo_UserFound() {
        UserEntity testUserEntity = new UserEntity();
        testUserEntity.setId(1L);
        testUserEntity.setFirstName("John Doe");
        testUserEntity.setEmail("johndoe@example.com");
        userRepository.save(testUserEntity);
        User user = userService.getInfo(1L);
        assertNotNull(user);
        assertEquals("John Doe", user.getFirstName());
        assertEquals("johndoe@example.com", user.getEmail());
    }

    @Test
    public void testGetHistoryOfTours_UserHasTours() {
        User user = new User();
        Integer quantity1 = 1, quantity2 = 2;
        List<UserInTour> userInTours = new ArrayList<>();
        Tour tour1 = new Tour();
        Tour tour2 = new Tour();
        userInTours.add(new UserInTour(user, tour1, quantity1));
        userInTours.add(new UserInTour(user, tour2, quantity2));
        for (UserInTour userInTour : userInTours) {
            userInTourService.save(userInTour);
        }
        Long userId = userService.getIdByEmail(user.getEmail());
        List<Tour> result = userService.getHistoryOfTours(userId);
        assertEquals(2, result.size());
        assertEquals(tour1, result.get(0));
        assertEquals(tour2, result.get(1));
    }

    @Test
    public void testGetHistoryOfTours_UserHasNoTours() {
        Long userId = 1L;
        List<Tour> result = userService.getHistoryOfTours(userId);
        assertEquals(Collections.emptyList(), result);
    }
    @Test
    public void testCancelTour_EnabledForCanceling_Success() {
        UserInTour userInTour = new UserInTour();
        userInTour.setTour(new Tour());
        userInTour.setTransactionNumber("12345");
        String result = userService.cancelTour(userInTour);
        assertEquals("Cancellation success", result);
    }

    @Test
    public void testCancelTour_NotEnabledForCanceling() {
        UserInTour userInTour = new UserInTour();
        userInTour.setTour(new Tour());
        String result = userService.cancelTour(userInTour);
        assertEquals("Not Available For Canceling", result);
    }
    
}