package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.SignIn;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserRepository userRepository;
    private UserServiceImpl userService;


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
        assertEquals(1L, user.getId().longValue());
        assertEquals("John Doe", user.getFirstName());
        assertEquals("johndoe@example.com", user.getEmail());
    }

    @Test
    public void testGetInfo_UserNotFound() {
        User result = userService.getInfo(999L);
        assertNull(result);
    }
}