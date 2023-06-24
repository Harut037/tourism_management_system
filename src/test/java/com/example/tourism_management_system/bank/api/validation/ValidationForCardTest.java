package com.example.tourism_management_system.bank.api.validation;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ValidationForCardTest {

    @Test
    void cardNumberValidation() {
        ValidationForCard validationForCard = new ValidationForCard();
        String cardNumbers = validationForCard.cardNumberValidation("48790000111122226");
        assertEquals("Invalid card number", cardNumbers);

    }

    @Test
    void validateForCardType() {
        ValidationForCard validationForCard = new ValidationForCard();
        String[] cardTypes = new String[]{"VISA", "MASTERCARD", "AMERICANEXPRESS", "ACBA"};


        String cardType = validationForCard.validateForCardType(cardTypes[0]);
        assertEquals("VISA", cardType);

        cardType = validationForCard.validateForCardType(cardTypes[1]);
        assertEquals("MASTER_CARD", cardType);

        cardType = validationForCard.validateForCardType(cardTypes[2]);
        assertEquals("AMERICAN_EXPRESS", cardType);


        cardType = validationForCard.validateForCardType(cardTypes[3]);
        assertEquals("Invalid card type", cardType);

    }

    @Test
    void validateExpirationDate() {
        ValidationForCard validationForCard = new ValidationForCard();
        LocalDate currentDate = LocalDate.now();
        currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        LocalDate expirationDate = LocalDate.now().plusDays(10);
        expirationDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));

//        TODO
//                assertEquals(currentDate,validationForCard.validateExpirationDate(expirationDate));

        expirationDate = expirationDate.minusDays(20);
        assertNull(validationForCard.validateExpirationDate(expirationDate));

    }

    @Test
    void isValidCard() {
        ValidationForCard validationForCard = new ValidationForCard();
        Card card = new Card();
        card.setCardNumber("4318290092359237");
        card.setType("VISA");
        card.setExpirationDate(validationForCard.validateExpirationDate(LocalDate.now().plusDays(10)));
        card.setCurrency("USD");


        assertEquals(true,validationForCard.isValidCard(card));

        card.setCardNumber("43182900923592370");
        assertEquals(false,validationForCard.isValidCard(card));

        card.setCardNumber("4318290092359237");
        card.setType("VISAA");
        assertEquals(false,validationForCard.isValidCard(card));


        card.setType("VISA");
        card.setCurrency("US");
        assertEquals(false,validationForCard.isValidCard(card));

        card.setCurrency("USD");
        card.setExpirationDate(validationForCard.validateExpirationDate(LocalDate.now().minusDays(10)));
        assertEquals(false,validationForCard.isValidCard(card));

    }
}