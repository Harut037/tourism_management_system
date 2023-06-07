package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.CardEntity;
import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.enums.enumForCard.Status;
import com.example.tourism_management_system.model.pojos.CardModel;
import com.example.tourism_management_system.repository.CardRepository;
import com.example.tourism_management_system.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl (CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public void createCard(CardModel cardModel){
        CardEntity cardEntity = new CardEntity(new UserEntity(cardModel.getUser()), cardModel.getFirstName(),
                cardModel.getLastName(), cardModel.getCardNumber(), cardModel.getType(),
                cardModel.getBalance(),cardModel.getExpirationDate(), Status.ACTIVE.toString(),
                cardModel.getCurrency());
                cardRepository.save(cardEntity);
    }


    public Optional<CardEntity> getCard(String cardNumber) {
        return cardRepository.findCardEntityByCardNumber(cardNumber);
    }


    public List<CardEntity> getAllCard() {
        return cardRepository.findAll();
    }




    public String rechargeBalance(String cardNumber, double balance) {
        Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(cardNumber);
        if (cardEntity.isPresent()) {
            cardEntity.get().setBalance(cardEntity.get().getBalance() + balance);
            cardRepository.save(cardEntity.get());
        }
        return "Your balance has been successfully recharged";
    }

    public String withdrawBalance(String cardNumber, double balance) {
        Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(cardNumber);
        if (balance <= cardEntity.get().getBalance() && balance >= 1) {
            cardEntity.get().setBalance(cardEntity.get().getBalance() - balance);
            cardRepository.save(cardEntity.get());
        } else return "You don't have enough money to complete the transaction";
        return "Transaction completed successfully";
    }

    public String checkBalance(String cardNumber) {
        Optional<CardEntity> cardEntity = cardRepository.findCardEntityByCardNumber(cardNumber);
        return "Your current balance` " + cardEntity.get().getBalance() + " " + cardEntity.get().getCurrency();
    }

}
