package com.example.tourism_management_system.bank.api.service;


import com.example.tourism_management_system.model.pojos.CardForUser;

import java.util.List;

public interface CardService {

    void save(List<CardForUser> cardForUsers);
    
    String addCard(CardForUser cardForUser);
}