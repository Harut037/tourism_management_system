package com.example.tourism_management_system.bank.api.service;

import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.model.pojos.CardForUser;

public interface CardService {

    String createCard(Card card);

    boolean compareCard(CardForUser cardForUser);
    
    Card getCard (CardForUser cardForUser);
}