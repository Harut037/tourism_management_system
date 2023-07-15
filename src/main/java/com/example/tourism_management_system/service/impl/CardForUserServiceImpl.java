package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.CardEntityForUser;
import com.example.tourism_management_system.model.enums.Status;
import com.example.tourism_management_system.model.pojos.CardForUser;
import com.example.tourism_management_system.repository.CardForUserRepository;
import com.example.tourism_management_system.service.CardForUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardForUserServiceImpl implements CardForUserService {

    private final CardForUserRepository cardForUserRepository;

    @Autowired
    public CardForUserServiceImpl(CardForUserRepository cardForUserRepository) {
        this.cardForUserRepository = cardForUserRepository;
    }

    /**
     * Saves a CardForUser object by creating and persisting a corresponding CardEntityForUser object.
     *
     * @param cardForUser the CardForUser object to save
     * @return the saved CardEntityForUser object
     */
    @Override
    public CardEntityForUser save(CardForUser cardForUser) {
        return cardForUserRepository.save(new CardEntityForUser(cardForUser));
    }

    /**
     * Deletes a CardForUser object by removing the corresponding CardEntityForUser from the repository.
     *
     * @param cardNumber the Card Number to delete
     * @return true if the card was successfully deleted, false otherwise
     */
    @Override
    public Boolean deleteCard(String cardNumber) {
        return cardForUserRepository.deleteByCardNumber(cardNumber, Status.DELETED) > 0;
    }
}