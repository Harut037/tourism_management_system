package com.example.tourism_management_system.validation.tour;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ValidationForUserTest {
    @Test
    void testIsAdult() {
        ValidationForUser validation = new ValidationForUser();

        LocalDate birthDate = LocalDate.of(2000, 1, 1);
        LocalDate result = validation.isAdult(birthDate);
        Assertions.assertEquals(birthDate, result);

        LocalDate birtDate0 = LocalDate.now().minusYears(18).minusDays(1);
        LocalDate result0 = validation.isAdult(birtDate0);
        Assertions.assertEquals(birtDate0, result0);

        LocalDate birthDate1 = LocalDate.of(2007, 4, 25);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validation.isAdult(birthDate1);
        });

        LocalDate birthDate2 = LocalDate.now();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validation.isAdult(birthDate2);
        });

        LocalDate birthDate3 = LocalDate.now().plusMonths(3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validation.isAdult(birthDate3);
        });
    }
}