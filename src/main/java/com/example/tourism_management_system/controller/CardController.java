package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.entities.CardEntity;
import com.example.tourism_management_system.service.impl.CardServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/card")
public class CardController {

    private final CardServiceImpl cardService;


    public CardController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/get/{cardNumber}")
    public Optional<CardEntity> getCard(@PathVariable("cardNumber")String cardNumber){
        return cardService.getCard(cardNumber);
    }


    @GetMapping("/getAll")
    public List<CardEntity> getAll(){
        return cardService.getAllCard();
    }

    @PostMapping("/add")
    public void addCard(@RequestBody CardEntity cardEntity){
//        CardModel cardModel = new CardModel();
//        cardModel.setUserId(cardEntity.getUserId());
//        cardModel.setFirstName(cardEntity.getFirstName());
//        cardModel.setLastName(cardEntity.getLastName());
//        cardModel.setCardNumber(validation.cardNumberValidation(cardEntity.getCardNumber()));
//                cardModel.setType(validation.validateForCardType(cardEntity.getType()));
//                cardModel.setExpirationDate(validation.validateExpirationDate(cardEntity.getExpirationDate()));
//                cardModel.setCurrency(Currency.valueOf(cardEntity.getCurrency()).toString());
//        cardService.createCard(cardModel);
    }



    @PutMapping("/recharge/{cardNumber}/{balance}")
    public String rechargeBalance(@PathVariable("cardNumber") String cardNumber,@PathVariable("balance") double balance){
        return cardService.rechargeBalance(cardNumber, balance);
    }

    @GetMapping("/check/balance/{cardNumber}")
    public String checkBalance(@PathVariable("cardNumber") String cardNumber){
        return cardService.checkBalance(cardNumber);
    }

    @PutMapping("/withdraw/{cardNumber}/{balance}")
    public String withdrawBalance(@PathVariable("cardNumber") String cardNumber,
                                  @PathVariable("balance") double balance){
        return cardService.withdrawBalance(cardNumber,balance);
    }



}
