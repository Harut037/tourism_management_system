package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.pojos.CardForUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ValidationForCardForUser {

    /**
     * Validates the expiration date against the current date.
     *
     * @param expirationDate The expiration date to be validated.
     * @return The validated expiration date if it is after the current date, or null if the expiration date is null or before the current date.
     */
    public LocalDate validateExpirationDate(LocalDate expirationDate) {
        LocalDate currentDate = LocalDate.now();
        currentDate.format(DateTimeFormatter.ofPattern("MM/yy"));
        try {
            if (currentDate.isBefore(expirationDate)) {
                return expirationDate;
            } else throw new NullPointerException();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Checks if a given card is valid.
     *
     * @param cardForUser The card information to be validated.
     * @return true if the card is valid, false otherwise.
     */
    public boolean isValidCard(CardForUser cardForUser) {
        try {
            if (validateExpirationDate(cardForUser.getExpirationDate()) == null) {
                return false;
            } else {
                return true;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}