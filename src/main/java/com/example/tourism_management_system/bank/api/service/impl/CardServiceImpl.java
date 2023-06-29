package com.example.tourism_management_system.bank.api.service.impl;

import com.example.tourism_management_system.bank.api.model.entity.CardEntity;
import com.example.tourism_management_system.bank.api.model.enumForCard.Status;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.repository.CardRepository;
import com.example.tourism_management_system.bank.api.service.CardService;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import com.example.tourism_management_system.model.pojos.CardForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final ValidationForCard validationForCard;
    private final CardRepository cardRepository;


    @Autowired
    public CardServiceImpl (CardRepository cardRepository, ValidationForCard validationForCard) {
        this.cardRepository = cardRepository;
        this.validationForCard = validationForCard;
    }

    /**
     * The createCard method creates a new card by converting a Card object into a CardEntity and saving it to the cardRepository.
     *
     * @param card the Card object containing the card information to be created
     */

    @Override
    public String createCard(Card card) {
        card.setCvv(validationForCard.generateCvv());
        card.setStatus(Status.valueOf("ACTIVE").toString());
        CardEntity cardEntity = new CardEntity(card);
        cardRepository.save(cardEntity);
        return "Card created successfully";
    }

    @Override
    public boolean compareCard(CardForUser cardForUser) {
        Optional<CardEntity> cardEntity = cardRepository.findCard(cardForUser.getCardNumber(), cardForUser.getOwner(),
                cardForUser.getExpirationDate(), cardForUser.getCvv());
        if (cardEntity.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The getCard method retrieves a CardEntity from the cardRepository based on the provided card number.
     *
     * @param cardNumber the card number used to search for the card entity
     * @return an Optional containing the CardEntity if found, or an empty Optional if not found
     */
    public Optional<CardEntity> getCard(String cardNumber) {
        return cardRepository.findCardEntityByCardNumber(cardNumber);
    }

    /**
     * The getAllCard method retrieves all CardEntities from the cardRepository and converts them into a list of Card objects.
     *
     * @return a list of Card objects containing all the cards retrieved from the cardRepository
     */
    public List<CardEntity> getAllCards() {
        return cardRepository.findAll();
    }


    /**
     * The rechargeBalance method updates the balance of a card by adding the specified amount to the existing balance.
     *
     * @param cardNumber the card number for which the balance needs to be recharged
     * @param balance    the amount to be added to the existing balance
     * @return a string indicating the success of the balance recharge
     */
    public String rechargeBalance(String cardNumber, double balance) {
        Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(cardNumber);
        if (cardEntity.isPresent()) {
            cardEntity.get().setBalance((float) (cardEntity.get().getBalance() + balance));
            cardRepository.save(cardEntity.get());
        }
        return "Your balance has been successfully recharged";
    }

    /**
     * The withdrawBalance method updates the balance of a card by deducting the specified amount from the existing balance.
     *
     * @param cardNumber the card number for which the balance needs to be withdrawn
     * @param balance    the amount to be deducted from the existing balance
     * @return a string indicating the success of the balance withdrawal or an error message if the transaction cannot be completed
     */
    public String withdrawBalance(String cardNumber, double balance) {
        Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(cardNumber);
        if (balance <= cardEntity.get().getBalance() && balance >= 1) {
            cardEntity.get().setBalance((float) (cardEntity.get().getBalance() - balance));
            cardRepository.save(cardEntity.get());
        } else return "You don't have enough money to complete the transaction";
        return "Transaction completed successfully";
    }


    /**
     * The checkBalance method retrieves the current balance of a card.
     *
     * @param cardNumber the card number for which the balance needs to be checked
     * @return a string containing the current balance and currency of the card
     */
    public String checkBalance(String cardNumber) {
        Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(cardNumber);
        return "Your current balance` " + cardEntity.get().getBalance() + " " + cardEntity.get().getCurrency();
    }

    //TODO
    /**
     * Overrides the default save behavior to save a list of CardEntity objects.
     *
     * @param cardForUsers the list of CardEntity objects to be saved
     */
    @Override
    public Integer save(CardForUser cardForUsers) {

        return null;
    }
    
    
}