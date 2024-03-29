package com.example.tourism_management_system.service;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TourAdministratorService {

    String addTour(Tour tour);

    String editTour(Tour tour);

    String removeTour(Tour tour);

    List<UserInTour> getAllUserInToursOfTour(Tour tour);
}