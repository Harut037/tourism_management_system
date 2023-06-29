package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.CardEntityForUser;
import com.example.tourism_management_system.model.pojos.CardForUser;
import org.springframework.stereotype.Service;

@Service
public interface CardForUserService {
    
    CardEntityForUser save (CardForUser cardForUser);
    
    CardEntityForUser getCard (CardForUser cardForUser);
    
    Boolean deleteCard (CardForUser cardForUser);
}