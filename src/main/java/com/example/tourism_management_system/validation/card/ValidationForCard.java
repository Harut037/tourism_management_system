package com.example.tourism_management_system.validation.card;


import com.example.tourism_management_system.model.enums.enumForCard.CardType;
import com.example.tourism_management_system.model.enums.enumForCard.Currency;
import com.example.tourism_management_system.model.pojos.Card;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class ValidationForCard {

    ExchangeRate exchangeRate = new ExchangeRate();

    public String cardNumberValidation(String cardNumber) {
        if (cardNumber.startsWith("4") && cardNumber.length() == 16){
            validateForCardType("VISA");
            return cardNumber;
        }else
        if (cardNumber.startsWith("51") ||
                cardNumber.startsWith("52") ||
                cardNumber.startsWith("53") ||
                cardNumber.startsWith("54") ||
                cardNumber.startsWith("55") ||
                cardNumber.startsWith("56")  && cardNumber.length() == 16){
            validateForCardType("MASTER_CARD");
            return cardNumber;
        }else
        if (cardNumber.startsWith("34") ||
                cardNumber.startsWith("35") ||
                cardNumber.startsWith("36") ||
                cardNumber.startsWith("37") ||
                cardNumber.startsWith("38") && cardNumber.length() == 15){
            validateForCardType("AMERICAN_EXPRESS");
            return cardNumber;
        } else
            return "Invalid card number";
    }




    public String validateForCardType(String cardType) {
        switch (CardType.valueOf(cardType)){
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


    public String validateDateForTransaction() {
        Format f = new SimpleDateFormat("MM/yy");
        return f.format(new Date());
    }

    public LocalDate validateExpirationDate(LocalDate expirationDate){
        LocalDate currentDate = LocalDate.now();
        currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        if(currentDate.isBefore(expirationDate)) {
            return  expirationDate;
        } else return null;
    }



    public String generateTransactionNumber() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 4));
        String formattedRandomNumber = String.format("%0" + 4 + "d", randomNumber);
        return timestamp + formattedRandomNumber;
    }

    public double getRate(String currencyTypeOne, String currencyTypeTwo, double amount) {
        double sendAmount = 1;
        double receiveAmount = 1;
        switch (Currency.valueOf(currencyTypeOne)) {
            case RUR:
                sendAmount = amount * exchangeRate.getRUR();
                break;
            case USD:
                sendAmount = amount * exchangeRate.getUSD();
                break;
            case AMD:
                sendAmount = amount;
                break;
            case EUR:
                sendAmount = amount * exchangeRate.getEUR();

        }
        switch (Currency.valueOf(currencyTypeTwo)) {
            case RUR:
                receiveAmount = sendAmount / exchangeRate.getRUR();
                break;
            case USD:
                receiveAmount = sendAmount / exchangeRate.getUSD();
                break;
            case AMD:
                receiveAmount = sendAmount;
                break;
            case EUR:
                receiveAmount = sendAmount / exchangeRate.getEUR();
        }
        return receiveAmount;
    }

    public boolean isValidCard(Card card) {
        if (cardNumberValidation(card.getCardNumber()).equals("Invalid card number")
        ||  validateForCardType(card.getType()).equals("Invalid card type")
        || validateExpirationDate(card.getExpirationDate()) == null
                || Currency.valueOf(card.getCurrency()).toString() == null)
            return false;
        return true;
    }
}