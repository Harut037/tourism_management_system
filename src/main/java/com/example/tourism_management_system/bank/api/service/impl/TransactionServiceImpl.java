package com.example.tourism_management_system.bank.api.service.impl;

import com.example.tourism_management_system.bank.api.model.entity.CardEntity;
import com.example.tourism_management_system.bank.api.model.entity.TransactionEntity;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.repository.TransactionRepository;
import com.example.tourism_management_system.bank.api.service.TransactionService;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String makeTransaction(Card card, double price) {
        TransactionEntity transactionEntity = new TransactionEntity(card, price);
        if (card.getBalance() >= price) {
            double d = validationForCard.getRate("AMD", card.getCurrency(), price);
            cardService.withdrawBalance(card.getCardNumber(), d);
            cardService.rechargeBalance("4847243400981111", price);
            transactionEntity.setTransactionNumber(validationForCard.generateTransactionNumber());
            transactionEntity.setCurrency(card.getCurrency());
            transactionRepository.save(transactionEntity);
            return transactionEntity.getTransactionNumber();

        } else
            return "You don`t have enough money";


    }

    @Override
    public String revertTransaction(String transactionNumber) {
        Optional<TransactionEntity> transactionEntity = transactionRepository.findTransaction(transactionNumber);
        if (transactionEntity.isPresent() && transactionEntity.get().getFlag()) {
            cardService.withdrawBalance(transactionEntity.get().getReceiver(), transactionEntity.get().getPrice());
            CardEntity cardEntity = cardService.getCard(transactionEntity.get().getSender()).get();
            double     d          = validationForCard.getRate("AMD", cardEntity.getCurrency(), transactionEntity.get().getPrice());
            cardService.rechargeBalance(transactionEntity.get().getSender(), d);
            transactionRepository.revert(transactionNumber);
            return "Success";
        } throw new IllegalArgumentException("Transaction Is Already Reverted");
    }
}