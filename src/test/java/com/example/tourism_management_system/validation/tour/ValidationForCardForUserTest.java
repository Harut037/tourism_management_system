package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.pojos.CardForUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidationForCardForUserTest {

    ValidationForCardForUser validation = new ValidationForCardForUser();

    @Test
    void testValidateExpirationDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        LocalDate futureExpirationDate = currentDate.plusYears(1);
        LocalDate validatedDate = validation.validateExpirationDate(futureExpirationDate);
        assertEquals(futureExpirationDate, validatedDate);

        LocalDate pastExpirationDate = currentDate.minusYears(1);
        LocalDate expiredDate = validation.validateExpirationDate(pastExpirationDate);
        assertNull(expiredDate);
    }
    @Test
    void testIsValidCard() {
        CardForUser validCard = mock(CardForUser.class);
        when(validCard.getExpirationDate()).thenReturn(LocalDate.now().plusYears(1));

        CardForUser expiredCard = mock(CardForUser.class);
        when(expiredCard.getExpirationDate()).thenReturn(LocalDate.now().minusYears(1));

//        ValidationForCardForUser validator = new ValidationForCardForUser();
//
//        assertTrue(validator.isValidCard(validCard));
//
//        assertFalse(validator.isValidCard(expiredCard));
//
//        assertFalse(validator.isValidCard(null));
    }
}