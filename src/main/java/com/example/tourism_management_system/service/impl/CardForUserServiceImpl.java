package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.CardEntityForUser;
import com.example.tourism_management_system.model.pojos.CardForUser;
import com.example.tourism_management_system.repository.CardForUserRepository;
import com.example.tourism_management_system.service.CardForUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardForUserServiceImpl implements CardForUserService {
    
    private final CardForUserRepository cardForUserRepository;
    
    @Autowired
    public CardForUserServiceImpl (CardForUserRepository cardForUserRepository) {
        this.cardForUserRepository = cardForUserRepository;
    }
    
    @Override
    public CardEntityForUser save (CardForUser cardForUser) {
        return cardForUserRepository.save(new CardEntityForUser(cardForUser));
    }
    
    @Override
    public Boolean deleteCard (CardForUser cardForUser) {
        return cardForUserRepository.deleteByCardNumber(cardForUser.getCardNumber())>0;
    }
}