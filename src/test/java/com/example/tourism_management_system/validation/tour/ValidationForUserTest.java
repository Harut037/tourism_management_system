package com.example.tourism_management_system.validation.tour;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ValidationForUserTest {

    private final ValidationForUser validation = new ValidationForUser();

    @Test
    void testIsAdult() {
        LocalDate birthDate00 = LocalDate.now().minusYears(16).minusDays(1);
        boolean isAdult = validation.isAdult(birthDate00);
        Assertions.assertTrue(isAdult);

        LocalDate birthDate1 = LocalDate.of(2000, 1, 1);
        Assertions.assertTrue(validation.isAdult(birthDate1));

        LocalDate birthDate01 = LocalDate.now().minusYears(16);
        Assertions.assertThrows(IllegalArgumentException.class, () -> validation.isAdult(birthDate01));

        LocalDate birthDate2 = LocalDate.now();
        Assertions.assertThrows(IllegalArgumentException.class, () -> validation.isAdult(birthDate2));

        LocalDate birthDate3 = LocalDate.now().plusMonths(3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> validation.isAdult(birthDate3));
    }
}