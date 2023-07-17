package com.example.tourism_management_system.bank.api.service.impl;

import com.example.tourism_management_system.bank.api.model.entity.CardEntity;
import com.example.tourism_management_system.bank.api.model.entity.TransactionEntity;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.repository.TransactionRepository;
import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ValidationForCard validationForCard;
    private final CardServiceImpl cardService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, ValidationForCard validationForCard, CardServiceImpl cardService) {
        this.transactionRepository = transactionRepository;
        this.validationForCard = validationForCard;
        this.cardService = cardService;
    }

    /**
     * Performs a transaction using the provided card and price.
     *
     * @param card  The Card object associated with the transaction.
     * @param price The price of the transaction.
     * @return The transaction number if the transaction is successful, or a message indicating insufficient funds.
     */
    @Override
    public String makeTransaction(Card card, double price) {
        TransactionEntity transactionEntity = new TransactionEntity(card, price);
        double d = validationForCard.getRate("AMD", card.getCurrency(), price);
        if (card.getBalance() >= d) {
            cardService.withdrawBalance(card.getCardNumber(), d);
            cardService.rechargeBalance("4444444444444444", price);
            transactionEntity.setTransactionNumber(validationForCard.generateTransactionNumber());
            transactionEntity.setCurrency("AMD");
            transactionRepository.save(transactionEntity);
            return transactionEntity.getTransactionNumber();
        } else {
            return "You don`t have enough money";
        }
    }

    /**
     * Reverts a transaction based on the provided transaction number.
     *
     * @param transactionNumber The transaction number associated with the transaction to be reverted.
     * @return A string indicating the success status of the transaction reversion.
     * @throws IllegalArgumentException if the transaction is already reverted or not found.
     */
    @Override
    public String revertTransaction(String transactionNumber) {
        Optional<TransactionEntity> transactionEntity = transactionRepository.findTransaction(transactionNumber);
        if (transactionEntity.isPresent() && transactionEntity.get().getFlag()) {
            revert(transactionNumber, transactionEntity);
            return "Success";
        } else {
            throw new IllegalArgumentException("Transaction Is Already Reverted: Transaction Number - " + transactionEntity.get().getTransactionNumber());
        }
    }

    /**
     * Reverts a list of transactions.
     *
     * @param transactionNumbers the list of transaction numbers to revert
     * @return a string indicating the result of the operation
     * @throws IllegalArgumentException if a transaction is already reverted or not found
     */
    @Override
    public String revertTransactionList(List<String> transactionNumbers) {
        for (String transactionNumber : transactionNumbers) {
            Optional<TransactionEntity> transactionEntity = transactionRepository.findTransaction(transactionNumber);
            if (transactionEntity.isPresent() && transactionEntity.get().getFlag()) {
                revert(transactionNumber, transactionEntity);
            } else {
                throw new IllegalArgumentException("Transaction Is Already Reverted: Transaction Number - " + transactionEntity.get().getTransactionNumber());
            }
        }
        return "Success";
    }

    /**
     * Reverts a specific transaction.
     *
     * @param transactionNumber the transaction number to revert
     * @param transactionEntity an optional transaction entity containing the details of the transaction
     */
    private void revert(String transactionNumber, Optional<TransactionEntity> transactionEntity) {
        cardService.withdrawBalance(transactionEntity.get().getReceiver(), transactionEntity.get().getPrice());
        CardEntity cardEntity = cardService.getCard(transactionEntity.get().getSender()).get();
        double d = validationForCard.getRate("AMD", cardEntity.getCurrency(), transactionEntity.get().getPrice());
        cardService.rechargeBalance(transactionEntity.get().getSender(), d);
        transactionRepository.revert(transactionNumber);
    }
}