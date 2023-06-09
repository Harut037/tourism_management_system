package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.entities.UserEntity;

public interface UserService {

    public void signUp(UserEntity userEntity);

    public void logIn(UserEntity userEntity);

    public void bookTour(UserEntity userEntity, TourEntity tourEntity);

    public void editeTour(UserEntity userEntity,TourEntity tourEntity);

    public void cancelTour(UserEntity userEntity,TourEntity tourEntity);

    public String leaveReview(UserEntity userEntity);


}
