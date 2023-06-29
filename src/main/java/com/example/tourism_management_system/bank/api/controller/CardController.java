package com.example.tourism_management_system.bank.api.controller;

import com.example.tourism_management_system.bank.api.model.entity.CardEntity;
import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.service.impl.CardServiceImpl;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardServiceImpl cardService;
    private final ValidationForCard validationForCard;

    @Autowired
    public CardController(CardServiceImpl cardService, ValidationForCard validationForCard) {
        this.cardService = cardService;
        this.validationForCard = validationForCard;
    }
    
    @GetMapping("/getAll")
    public List<Card> getAllCards() {
        List<CardEntity> cardEntities = cardService.getAllCards();
        List<Card> cards = new ArrayList<>();
        for (CardEntity cardEntity : cardEntities) {
            cards.add(new Card(cardEntity));
        }
       return cards;

    }

    @PostMapping("/create")
    public String addCard(@Valid @RequestBody Card card) {
        if (validationForCard.isValidCard(card)) {
            return cardService.createCard(card);
        } else throw new IllegalArgumentException();
    }


    @PutMapping("/recharge/{cardNumber}/{balance}")
    public String rechargeBalance(@PathVariable("cardNumber") String cardNumber, @PathVariable("balance") double balance) {
        return cardService.rechargeBalance(cardNumber, balance);
    }

    @PutMapping("/withdraw/{cardNumber}/{balance}")
    public String withdrawBalance(@PathVariable("cardNumber") String cardNumber,
                                  @PathVariable("balance") double balance) {
        return cardService.withdrawBalance(cardNumber, balance);
    }
}