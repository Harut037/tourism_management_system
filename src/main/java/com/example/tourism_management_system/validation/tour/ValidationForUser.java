package com.example.tourism_management_system.validation.tour;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidationForUser {

    /**
     * Checks if a given birthdate represents an adult based on a minimum age of 18 years.
     *
     * @param birthDate The birthdate to be checked.
     * @return The birthdate if it represents an adult (age 18 or older).
     * @throws IllegalArgumentException if the birthdate represents a person younger than 18 years.
     */
    public boolean isAdult(LocalDate birthDate) {
        LocalDate validatedDate = LocalDate.now().minusYears(16);
        if (birthDate.isBefore(validatedDate)) {
            return true;
        } else {
            throw new IllegalArgumentException("Age must be at least 16.");
        }
    }
}