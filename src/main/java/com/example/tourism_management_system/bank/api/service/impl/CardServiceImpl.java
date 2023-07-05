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
    public CardServiceImpl(CardRepository cardRepository, ValidationForCard validationForCard) {
        this.cardRepository = cardRepository;
        this.validationForCard = validationForCard;
    }

    /**
     * Creates a new card and saves it in the card repository.
     *
     * @param card The card object to be created and saved.
     * @return A string indicating the success message of the card creation process.
     */
    @Override
    public String createCard(Card card) {
        card.setCvv(validationForCard.generateCvv());
        card.setStatus(Status.ACTIVE);
        CardEntity cardEntity = new CardEntity(card);
        cardRepository.save(cardEntity);
        return "Card created successfully";
    }

    /**
     * Compares the provided card details with the cards stored in the card repository.
     *
     * @param cardForUser The card details to be compared.
     * @return {@code true} if a card with matching details is found in the repository, {@code false} otherwise.
     */
    @Override
    public boolean compareCard(CardForUser cardForUser) {
        Optional<CardEntity> cardEntity = cardRepository.findCard(cardForUser.getCardNumber(), cardForUser.getOwner(),
                cardForUser.getExpirationDate(), cardForUser.getCvv());
        return cardEntity.isPresent();
    }

    /**
     * Retrieves a card entity from the card repository based on the provided card number.
     *
     * @param cardNumber The card number associated with the card entity to be retrieved.
     * @return An optional containing the card entity if found, or an empty optional if not found.
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
     * Recharges the balance of a card identified by the provided card number.
     *
     * @param cardNumber The card number associated with the card to be recharged.
     * @param balance    The amount of balance to be added to the card.
     * @return A string indicating the success message of the balance recharge process.
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
     * Withdraws the specified balance from the card identified by the provided card number.
     *
     * @param cardNumber The card number associated with the card to withdraw balance from.
     * @param balance    The amount of balance to be withdrawn from the card.
     * @return A string indicating the status of the withdrawal transaction.
     */
    public String withdrawBalance(String cardNumber, double balance) {
        Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(cardNumber);
        if (balance <= cardEntity.get().getBalance() && balance >= 1) {
            cardEntity.get().setBalance((cardEntity.get().getBalance() - balance));
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

    /**
     * Retrieves a Card object based on the provided CardForUser details.
     *
     * @param cardForUser The CardForUser object containing the card details to retrieve.
     * @return A Card object created from the CardEntity found in the card repository.
     */
    @Override
    public Card getCard(CardForUser cardForUser) {
        return new Card(cardRepository.findCardEntityByCardNumber(cardForUser.getCardNumber()).get());
    }
}