package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.service.TourAdministratorService;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.UserInTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourAdministratorServiceImpl implements TourAdministratorService {

    private final TourService tourService;
    private final UserInTourService userInTourService;

    @Autowired
    public TourAdministratorServiceImpl(TourService tourService, UserInTourService userInTourService) {
        this.tourService = tourService;
        this.userInTourService = userInTourService;
    }

    /**
     * Adds a new tour by calling the save method of the tourService.
     *
     * @param tour the Tour object to be added
     * @return a string representing the result of the operation
     */
    @Override
    public String addTour(Tour tour) {
        return tourService.save(tour);
    }

    /**
     * Edits an existing tour by calling the update method of the tourService.
     *
     * @param tour the Tour object to be edited
     * @return a string representing the result of the operation
     */
    @Override
    public String editTour(Tour tour) {
        return tourService.update(tour);
    }

    /**
     * Removes a tour by calling the deleteById method of the tourService with the ID of the specified tour.
     *
     * @param tour the Tour object to be removed
     * @return a string representing the result of the operation
     */
    @Override
    public String removeTour(Tour tour) {
        return tourService.deleteById(tourService.getId(tour));
    }

    /**
     * Retrieves a list of UserInTour objects representing all the users associated with the specified tour.
     *
     * @param tour the Tour object for which to retrieve the user-in-tour relationships
     * @return a list of UserInTour objects representing the users in the tour
     */
    @Override
    public List<UserInTour> getAllUserInToursOfTour(Tour tour) {
        return userInTourService.findByTour(tour);
    }
}