package com.example.tourism_management_system.bank.api.validation;

import com.example.tourism_management_system.bank.api.enumForCard.CardType;
import com.example.tourism_management_system.bank.api.enumForCard.Currency;
import com.example.tourism_management_system.bank.api.model.pojo.Card;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class ValidationForCard {

    ExchangeRate exchangeRate = new ExchangeRate();

    /**
     * The cardNumberValidation method validates a given card number and determines its card type.
     *
     * @param cardNumber the card number to be validated
     * @return the validated card number if it is valid and recognized, or an error message if it is invalid or unrecognized
     */
    public String cardNumberValidation(String cardNumber) {
        String visaRegex = "^4[0-9]{12}(?:[0-9]{3})?$";
        String mastercardRegex = "^(5[1-5][0-9]{14})|(56[0-9]{14})$";
        String amexRegex = "^3[47][0-9]{13}$";

        if (Pattern.matches(visaRegex, cardNumber)) {
            validateForCardType("VISA");
            return cardNumber;
        } else if (Pattern.matches(mastercardRegex, cardNumber)) {
            validateForCardType("MASTER_CARD");
            return cardNumber;
        } else if (Pattern.matches(amexRegex, cardNumber)) {
            validateForCardType("AMERICAN_EXPRESS");
            return cardNumber;
        } else {
            return "Invalid card number";
        }
    }


    /**
     * The validateForCardType method validates a given card type and returns the corresponding card type string.
     *
     * @param cardType the type of the card (VISA, MASTER_CARD, or AMERICAN_EXPRESS)
     * @return the validated card type as a string, or an error message if the card type is invalid or unrecognized
     */
    public String validateForCardType(String cardType) {
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
            default -> {
                return "Invalid card type";
            }
        }
    }

    /**
     * The validateExpirationDate method validates a given expiration date and checks if it is after the current date.
     *
     * @param expirationDate the expiration date to be validated
     * @return the validated expiration date if it is after the current date, or null if it is not
     */
    public LocalDate validateExpirationDate(LocalDate expirationDate) {
        LocalDate currentDate = LocalDate.now();
        currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        if (currentDate.isBefore(expirationDate)) {
            return expirationDate;
        } else return null;
    }

    /**
     * The isValidCard method checks if a given card object is valid by validating its card number, card type, expiration date, and currency.
     * If any of these validations fail, the method returns false. Otherwise, it returns true.
     *
     * @param card the card object to be validated
     * @return true if the card object is valid, false otherwise
     */
    public boolean isValidCard(Card card) {
        if (cardNumberValidation(card.getCardNumber()).equals("Invalid card number")
                || validateForCardType(card.getType()).equals("Invalid card type")
                || validateExpirationDate(card.getExpirationDate()) == null
                || Currency.valueOf(card.getCurrency()).toString() == null)
            return false;
        return true;
    }

    /**
     * The getRate method calculates the exchange rate between two currencies and converts an amount from one currency to another.
     *
     * @param currencyTypeOne the type of the currency to convert from
     * @param currencyTypeTwo the type of the currency to convert to
     * @param amount          the amount to be converted
     * @return the converted amount in the specified currency type, based on the exchange rates
     */
    public double getRate(String currencyTypeOne, String currencyTypeTwo, double amount) {
        double sendAmount = 1;
        double receiveAmount = 1;
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
    }

    /**
     * The generateTransactionNumber method generates a unique transaction number by combining a timestamp and a random number.
     *
     * @return a unique transaction number in the format "yyyyMMddHHmm" followed by a 4-digit random number
     */
    public String generateTransactionNumber() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 4));
        String formattedRandomNumber = String.format("%0" + 4 + "d", randomNumber);
        return timestamp + formattedRandomNumber;
    }


    /**
     * The validateDateForTransaction method validates the current date and returns it formatted as "MM/yy".
     *
     * @return the validated current date formatted as "MM/yy"
     */
    public String validateDateForTransaction() {
        Format f = new SimpleDateFormat("MM/yy");
        return f.format(new Date());
    }
}