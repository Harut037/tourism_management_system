package com.example.tourism_management_system.bank.api.controller;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.bank.api.service.impl.CardServiceImpl;
import com.example.tourism_management_system.bank.api.validation.ValidationForCard;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardServiceImpl cardService;

    ValidationForCard validationForCard = new ValidationForCard();

    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/getAll")
    public List<Card> getAllCards() {
        return cardService.getAllCard();
    }

    @PostMapping("/add")
    public void addCard(@Valid @RequestBody Card card) {
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