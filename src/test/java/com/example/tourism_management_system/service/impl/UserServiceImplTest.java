package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.repository.UserRepository;
import com.example.tourism_management_system.bank.api.service.CardService;
import com.example.tourism_management_system.service.RoleService;
import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.service.UserService;
import com.example.tourism_management_system.validation.tour.ValidationForCardForUser;
import com.example.tourism_management_system.validation.tour.ValidationForTour;
import com.example.tourism_management_system.validation.tour.ValidationForUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidationForTour validationForTour;

    @Mock
    private TransactionService transactionService;

    @Mock
    private CardService cardService;

    @Mock
    private RoleService roleService;

    @Mock
    private ValidationForCardForUser validationForCardForUser;

    @Mock
    private ValidationForUser validationForUser;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetIdByEmail() {
        String email = "AngelinaHarutyunyan@gmail.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        Optional<UserEntity> optionalUser = Optional.of(userEntity);
        when(userRepository.findByEmail(email)).thenReturn(optionalUser);

        Long userId = userService.getIdByEmail(email);

        assertEquals(null, userId);
        verify(userRepository, times(0)).findByEmail(email);
    }
}