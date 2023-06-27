package com.example.tourism_management_system.bank.api.validation;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
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

        String cardNumberForVisa = "4123456789012345";
        String actualResultForVisa = validationForCard.cardNumberValidation(cardNumberForVisa);
        assertEquals(cardNumberForVisa, actualResultForVisa);

        String actualResultForMaster = validationForCard.cardNumberValidation("5145781223568957");
        assertEquals("5145781223568957", actualResultForMaster);

        String actualResultForMaster1 = validationForCard.cardNumberValidation("5745781223568957");
        assertNotEquals("5745781223568957", actualResultForMaster1);

        String actualResultForAmericanExpress = validationForCard.cardNumberValidation("314578123568957");
        assertEquals("Invalid card number", actualResultForAmericanExpress);

        String actualResultForAmericanExpress1 = validationForCard.cardNumberValidation("344578123568957");
        assertEquals("344578123568957", actualResultForAmericanExpress1);

        String actualResultForArca = validationForCard.cardNumberValidation("9045781243568957");
        assertEquals("9045781243568957", actualResultForArca);

        String actualResultForArca1 = validationForCard.cardNumberValidation("9145781243568957");
        assertEquals("Invalid card number", actualResultForArca1);
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
        currentDate.format(DateTimeFormatter.ofPattern("MM/yy"));
        LocalDate expirationDate = LocalDate.now().plusDays(10);
        expirationDate.format(DateTimeFormatter.ofPattern("MM/yy"));
        LocalDate result = validationForCard.validateExpirationDate(expirationDate);
        assertEquals(expirationDate, result);
        assertNull(validationForCard.validateExpirationDate(LocalDate.now().minusDays(1)));

        LocalDate expirationDate1 = LocalDate.now().plusMonths(1);
        expirationDate1.format(DateTimeFormatter.ofPattern("MM/yy"));
        LocalDate result1 = validationForCard.validateExpirationDate(expirationDate1);
        assertEquals(expirationDate1, result1);

        LocalDate expirationDate2 = LocalDate.now();
        expirationDate2.format(DateTimeFormatter.ofPattern("MM/yy"));
        LocalDate result2 = validationForCard.validateExpirationDate(expirationDate2);
        assertNotEquals(expirationDate2, result2);

        LocalDate expirationDate3 = LocalDate.now().minusDays(50);
        LocalDate result3 = validationForCard.validateExpirationDate(expirationDate3);
        assertNull(result3);
    }

    @Test
    void isValidCard() {
        ValidationForCard validationForCard = new ValidationForCard();
        Card card = new Card();
        card.setCardNumber("4318290092359237");
        card.setType("VISA");
        card.setExpirationDate(validationForCard.validateExpirationDate(LocalDate.now().plusDays(10)));
        card.setCurrency("USD");

        assertEquals(true, validationForCard.isValidCard(card));

        card.setCardNumber("43182900923592370");
        assertEquals(false, validationForCard.isValidCard(card));

        card.setCardNumber("4318290092359237");
        card.setType("VISAA");
        assertEquals(false, validationForCard.isValidCard(card));

        card.setType("VISA");
        card.setCurrency("US");
        assertEquals(false, validationForCard.isValidCard(card));

        card.setCurrency("USD");
        card.setExpirationDate(validationForCard.validateExpirationDate(LocalDate.now().minusDays(10)));
        assertEquals(false, validationForCard.isValidCard(card));

        card.setExpirationDate(null);
        assertEquals(false, validationForCard.isValidCard(card));

        //TODO
//        Card validCard = new Card("4123456789123456", "VISA", LocalDate.now(), "USD");
//        assertTrue(validationForCard.isValidCard(validCard));
    }

    @Test
    void getRate() {
        ValidationForCard validationForCard = new ValidationForCard();
        ExchangeRate rate = new ExchangeRate();
        double amount = 100.0;

        validationForCard.getRate("USD", "AMD", 100.0);
        double expectedRateUSDtoAMD = amount * rate.getUSD();
        double actualRateUSDtoAMD = validationForCard.getRate("USD", "AMD", amount);
        assertEquals(expectedRateUSDtoAMD, actualRateUSDtoAMD, 0.01);
        assertEquals(Double.parseDouble("38594.0"), actualRateUSDtoAMD);

        validationForCard.getRate("EUR", "AMD", 100.0);
        double expectedRateEURtoAMD = amount * rate.getEUR();
        double actualRateEURtoAMD = validationForCard.getRate("EUR", "AMD", amount);
        assertEquals(expectedRateEURtoAMD, actualRateEURtoAMD, 0.01);
        assertEquals(Double.parseDouble("41516.0"), actualRateEURtoAMD);

        validationForCard.getRate("RUR", "AMD", 100.0);
        double expectedRateRURtoAMD = amount * rate.getRUR();
        double actualRateRURtoAMD = validationForCard.getRate("RUR", "AMD", amount);
        assertEquals(expectedRateRURtoAMD, actualRateRURtoAMD, 0.01);
        assertEquals(Double.parseDouble("483.0"), actualRateRURtoAMD);

        validationForCard.getRate("AMD", "USD", 1000.0);
        double expectedRateAMDtoUSD = amount / rate.getUSD();
        double actualRateAMDtoUSD = validationForCard.getRate("AMD", "USD", amount);
        assertEquals(expectedRateAMDtoUSD, actualRateAMDtoUSD, 0.01);
        //TODO
        //nayel xi 0.25
//        assertEquals("2.5911", actualRateAMDtoUSD);

        validationForCard.getRate("AMD", "EUR", 1000.0);
        double expectedRateAMDtoEUR = amount / rate.getEUR();
        double actualRateAMDtoEUR = validationForCard.getRate("AMD", "EUR", amount);
        assertEquals(expectedRateAMDtoEUR, actualRateAMDtoEUR, 0.01);
//        assertEquals("2.409", actualRateAMDtoUSD);


        validationForCard.getRate("AMD", "RUR", 1000.0);
        double expectedRateAMDtoRUR = amount / rate.getRUR();
        double actualRateAMDtoRUR = validationForCard.getRate("AMD", "RUR", amount);
        assertEquals(expectedRateAMDtoRUR, actualRateAMDtoRUR, 0.01);
//        assertEquals("207.0393", actualRateAMDtoRUR);
    }

    @Test
    void generateTransactionNumber() {
        ValidationForCard validationForCard = new ValidationForCard();


    }
}