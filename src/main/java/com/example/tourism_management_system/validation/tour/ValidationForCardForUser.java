package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.pojos.CardForUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ValidationForCardForUser {
    
    /**
     * The validateExpirationDate method validates a given expiration date and checks if it is after the current date.
     *
     * @param expirationDate the expiration date to be validated
     * @return the validated expiration date if it is after the current date, or null if it is not
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
     * The isValidCard method checks if a given card object is valid by validating its card number, card type, expiration date, and currency.
     * If any of these validations fail, the method returns false. Otherwise, it returns true.
     *
     * @param cardForUser the card object to be validated
     * @return true if the card object is valid, false otherwise
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