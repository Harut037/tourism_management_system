package com.example.tourism_management_system.bank.api.service;


import com.example.tourism_management_system.bank.api.model.pojo.Card;
import com.example.tourism_management_system.model.entities.CardEntityForUser;
import com.example.tourism_management_system.model.pojos.CardForUser;

import java.util.List;

public interface CardService {

    Integer save(CardForUser cardForUsers);
    
    Boolean addCard(CardForUser cardForUser);

    String createCard(Card card);

    boolean compareCard(CardForUser cardForUser);
}