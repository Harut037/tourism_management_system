package com.example.tourism_management_system.bank.api.service;

import com.example.tourism_management_system.bank.api.model.pojo.Card;

import java.util.List;

public interface CardService {

    void save(List<Card> cards);
    
    String addCard(Card card);
}
