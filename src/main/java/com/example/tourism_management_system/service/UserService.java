package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.User;

public interface UserService {

    public void signUp(User user);

    public void logIn(User user);

    public void bookTour(User user, Tour tour);

    public void editeTour(User user,Tour tour);

    public void cancelTour(User user,Tour tour);

    public String leaveReview(User user);


}
