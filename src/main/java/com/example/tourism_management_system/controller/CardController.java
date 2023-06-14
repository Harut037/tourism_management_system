package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.entities.CardEntity;
import com.example.tourism_management_system.model.pojos.Card;
import com.example.tourism_management_system.service.impl.CardServiceImpl;
import com.example.tourism_management_system.validation.card.ValidationForCard;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardServiceImpl cardService;

    ValidationForCard validationForCard = new ValidationForCard();

    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }
    
    @PostMapping("/add")
    public void addCard(@RequestBody Card card) {
        if (validationForCard.isValidCard(card)) {
            cardService.createCard(card);
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