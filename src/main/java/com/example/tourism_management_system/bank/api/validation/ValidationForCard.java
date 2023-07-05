package com.example.tourism_management_system.bank.api.validation;

import com.example.tourism_management_system.bank.api.model.enumForCard.CardType;
import com.example.tourism_management_system.bank.api.model.enumForCard.Currency;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

@Component
public class ValidationForCard {

    private final ExchangeRate exchangeRate = new ExchangeRate();

    /**
     * Validates the provided card number against known card number patterns for various card types (Visa, MasterCard, American Express, and Arca).
     *
     * @param cardNumber The card number to be validated.
     * @return The validated card number if it matches any of the known patterns.
     * @throws IllegalArgumentException if the card number does not match any of the known patterns.
     */
    public String cardNumberValidation(String cardNumber) {
        String visa = "^4[0-9]{12}(?:[0-9]{3})?$";
        String masterCard = "^(5[1-5][0-9]{14})|(56[0-9]{14})$";
        String americanExpress = "^3[47][0-9]{13}$";
        String arca = "^90[0-9]{14}$";
        if (Pattern.matches(visa, cardNumber)) {
            validateForCardType("VISA");
            return cardNumber;
        } else if (Pattern.matches(masterCard, cardNumber)) {
            validateForCardType("MASTER_CARD");
            return cardNumber;
        } else if (Pattern.matches(americanExpress, cardNumber)) {
            validateForCardType("AMERICAN_EXPRESS");
            return cardNumber;
        } else if (Pattern.matches(arca, cardNumber)) {
            validateForCardType("ARCA");
            return cardNumber;
        } else {
            throw new IllegalArgumentException("Invalid card");
        }
    }

    /**
     * Validates and converts the provided card type to a standardized format.
     *
     * @param cardType The card type to be validated and converted.
     * @return The standardized card type if it is valid, or a message indicating an invalid card type.
     */
    public String validateForCardType(String cardType) {
        if (cardType.equals("MASTERCARD")) {
            cardType = "MASTER_CARD";
        } else if (cardType.equals("AMERICANEXPRESS")) {
            cardType = "AMERICAN_EXPRESS";
        }
        try {
            switch (CardType.valueOf(cardType)) {
                case VISA -> {
                    return "VISA";
                }
                case MASTER_CARD -> {
                    return "MASTER_CARD";
                }
                case AMERICAN_EXPRESS -> {
                    return "AMERICAN_EXPRESS";
                }
                case ARCA -> {
                    return "ARCA";
                }
                default -> {
                    return "Invalid card type";
                }
            }
        } catch (IllegalArgumentException e) {
            return "Invalid card type";
        }
    }

    /**
     * Validates the provided expiration date against the current date.
     *
     * @param expirationDate The expiration date to be validated.
     * @return The validated expiration date if it is after the current date, or null if it is not.
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
     * Checks if the provided card is valid based on various validation criteria:
     * The card number is validated and is not considered an invalid card number.
     * The card type is validated and is not considered an invalid card type.
     * The expiration date is validated and is not null (indicating it is after the current date).
     * The currency is validated and is a valid Currency enum value.
     *
     * @param card The card to be validated.
     * @return true if the card is considered valid based on the validation criteria, false otherwise.
     */
    public boolean isValidCard(Card card) {
        try {
            return !cardNumberValidation(card.getCardNumber()).equals("Invalid card number") && !validateForCardType(card.getType()).equals("Invalid card type") && validateExpirationDate(card.getExpirationDate()) != null && Currency.valueOf(card.getCurrency()).toString() != null;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Calculates the amount after currency conversion between two currency types.
     *
     * @param currencyTypeOne The currency type to convert from.
     * @param currencyTypeTwo The currency type to convert to.
     * @param amount          The amount to be converted.
     * @return The converted amount after the currency conversion.
     * @throws IllegalArgumentException if any of the provided currency types is invalid.
     */
    public double getRate(String currencyTypeOne, String currencyTypeTwo, double amount) {
        double sendAmount;
        double receiveAmount;
        try {
            sendAmount = switch (Currency.valueOf(currencyTypeOne)) {
                case RUR -> amount * exchangeRate.getRUR();
                case USD -> amount * exchangeRate.getUSD();
                case AMD -> amount;
                case EUR -> amount * exchangeRate.getEUR();
            };
            receiveAmount = switch (Currency.valueOf(currencyTypeTwo)) {
                case RUR -> sendAmount / exchangeRate.getRUR();
                case USD -> sendAmount / exchangeRate.getUSD();
                case AMD -> sendAmount;
                case EUR -> sendAmount / exchangeRate.getEUR();
            };
            return receiveAmount;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid currency type.");
        }
    }

    /**
     * Generates a unique transaction number based on the current timestamp and a random number.
     *
     * @return The generated transaction number.
     */
    public String generateTransactionNumber() {
        String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 4));
        String formattedRandomNumber = String.format("%0" + 4 + "d", randomNumber);
        return timestamp + formattedRandomNumber;
    }

    /**
     * Generates a CVV (Card Verification Value) consisting of three random digits.
     *
     * @return The generated CVV as a string.
     */
    public String generateCvv() {
        StringBuilder cvv = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(9));
        }
        return cvv.toString();
    }
}