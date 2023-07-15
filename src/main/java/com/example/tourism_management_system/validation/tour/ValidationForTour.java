package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.enums.enumForTour.*;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Component
public class ValidationForTour {
    
    private final TourService tourService;
    
    @Autowired
    public ValidationForTour (TourService tourService) {
        this.tourService = tourService;
    }
    
    /**
     * Retrieves the corresponding tour name based on the given tour type and tour name.
     *
     * @param tourType The type of the tour.
     * @param tourName The name of the tour.
     * @return The corresponding tour name based on the tour type and name.
     * @throws IllegalArgumentException If the tour type or tour name is invalid.
     */
    public String tourName(String tourType, String tourName) {
        tourName = tourName.toUpperCase();
        try {
            switch (TourType.valueOf(tourType).toString()) {
                case "CULTURAL" -> {
                    return PlacesForCultural.valueOf(tourName).toString();
                }
                case "CAMPAIGN" -> {
                    return PlacesForCampaign.valueOf(tourName).toString();
                }
                case "ADVENTURE" -> {
                    return PlacesForAdventure.valueOf(tourName).toString();
                }
                default -> throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException i) {
            throw new IllegalArgumentException(i.getMessage());
        }
    }

    /**
     * The ValidateDate method checks if the new tour's creation/save date is valid by comparing it to the current date.
     * If the date provided is at least 3 days after the future date, then it is considered valid.
     *
     * @param date the date to be validated
     * @return the provided date if it is after the future date, or null if it is not valid
     * @throws IllegalArgumentException if an invalid tour type is provided
     */
    public LocalDate validateDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(3);
        if (date.isAfter(futureDate)) {
            return date;
        }
        return null;
    }

    /**
     * The validateStartTime method checks if a given start time is valid by comparing it with a predefined time range.
     * The method validates that the start time is after 06:59 (inclusive) and before noon (exclusive).
     *
     * @param startTime the start time to be validated
     * @return the provided start time if it falls within the valid time range, or null if it is not valid
     */
    public LocalTime validateStartTime(LocalTime startTime) {
        LocalTime currentTime = LocalTime.now();
        currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        if (startTime.isAfter(LocalTime.of(6, 59)) && startTime.isBefore(LocalTime.NOON)) {
            return startTime;
        } else return null;
    }

    /**
     * Validates the tour information and returns a list of objects containing the validated information.
     *
     * @param tourType The type of tour.
     * @param tourName The name of the tour place.
     * @return A list of objects containing the validated information.
     * @throws IllegalArgumentException If the tour type or tour name is invalid.
     */
    public List<Object> validateTourInformation(String tourType, String tourName) {
        tourName = tourName.toUpperCase();
        tourType = tourType.toUpperCase();
        try {
            switch (TourType.valueOf(tourType)) {
                case CULTURAL -> {
                    return forCultural(tourName);
                }
                case ADVENTURE -> {
                    return forAdventure(tourName);
                }
                case CAMPAIGN -> {
                    return forCampaign(tourName);
                }
                default -> throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Returns a list of objects containing the validated information for a cultural tour.
     *
     * @param tourName The name of the cultural tour.
     * @return A list of objects containing the validated information.
     */
    public List<Object> forCultural(String tourName) {
        tourName = tourName.toUpperCase();
        List<Object> objectList = null;
        PlacesForCultural placesForCultural = PlacesForCultural.valueOf(tourName);
        if (EnumSet.allOf(PlacesForCultural.class).contains(placesForCultural)) {
            objectList = new ArrayList<>();
            objectList.add(tourName);
            objectList.add(placesForCultural.getDistance());
            objectList.add(placesForCultural.getDuration());
            objectList.add(placesForCultural.getCost());
        }
        return objectList;
    }

    /**
     * Returns a list of objects containing the validated information for an adventure tour.
     *
     * @param tourName The name of the adventure tour.
     * @return A list of objects containing the validated information.
     */
    public List<Object> forAdventure(String tourName) {
        tourName = tourName.toUpperCase();
        List<Object> objectList = null;
        PlacesForAdventure placesForAdventure = PlacesForAdventure.valueOf(tourName);
        if (EnumSet.allOf(PlacesForAdventure.class).contains(placesForAdventure)) {
            objectList = new ArrayList<>();
            objectList.add(tourName);
            objectList.add(placesForAdventure.getDistance());
            objectList.add(placesForAdventure.getDuration());
            objectList.add(placesForAdventure.getCost());
        }
        return objectList;
    }

    /**
     * Returns a list of objects containing the validated information for a campaign tour.
     *
     * @param tourName The name of the campaign tour.
     * @return A list of objects containing the validated information.
     */
    public List<Object> forCampaign(String tourName) {
        tourName = tourName.toUpperCase();
        List<Object> objectList = null;
        PlacesForCampaign placesForCampaign = PlacesForCampaign.valueOf(tourName);
        if (EnumSet.allOf(PlacesForCampaign.class).contains(placesForCampaign)) {
            objectList = new ArrayList<>();
            objectList.add(tourName);
            objectList.add(placesForCampaign.getDistance());
            objectList.add(placesForCampaign.getDuration());
            objectList.add(placesForCampaign.getCost());
        }
        return objectList;
    }

    /**
     * Validates a tour and returns True if it is valid, False otherwise.
     *
     * @param tour The tour to be validated.
     * @return True if the tour is valid, False otherwise.
     * @throws IllegalArgumentException If the tour is not valid.
     */
    public boolean isValidTour(Tour tour) {
        if (tourName(tour.getTourType(), tour.getTourName()) == null ||
                validateDate(tour.getTourDate()) == null ||
                validateStartTime(tour.getStartTime()) == null
        ) {
            return false;
        }
        List<Object> list = validateTourInformation(tour.getTourType(), tour.getTourName());
        tour.setDistance(list.get(1) + " km");
        tour.setDuration(list.get(2) + " hours");
        if (tour.getCost() != null) {
            if (tour.getCost() < 3000) {
                throw new IllegalArgumentException("The cost cannot be cheaper than 3000");
            }
            if (tour.getCost() > 30000) {
                throw new IllegalArgumentException("The cost cannot be more expensive than 30000");
            }
        } else {
            tour.setCost((Integer) list.get(3));
        }
        if (tour.getMaxQuantity() != null) {
            if (tour.getMaxQuantity() > 50) {
                throw new IllegalArgumentException("MaxQuantity Can Not Be More Than 50");
            }
            if (tour.getMaxQuantity() < 7) {
                throw new IllegalArgumentException("MaxQuantity Can Not Be Less Than 7");
            }
        } else {
            tour.setMaxQuantity(50);
        }
        tour.setGeneralQuantity(0);
        tour.setCarType(Transport.UNDEFINED);
        return true;
    }

    /**
     * Checks if a tour is enabled for booking.
     *
     * @param tour     The tour to check.
     * @param quantity The number of tickets to book.
     * @return True if the tour is enabled for booking, false otherwise.
     */
    public boolean isEnableForBooking(Tour tour, int quantity) {
        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.plusDays(2);
        LocalDate tourDate = tour.getTourDate();
        return !tourDate.isBefore(beforeDate) && (quantity + tour.getGeneralQuantity()) <= tour.getMaxQuantity();
    }

    /**
     * Checks if a tour is enabled for canceling.
     *
     * @param tour The tour to check.
     * @return True if the tour is enabled for canceling, false otherwise.
     */
    public boolean isEnableForCanceling(Tour tour) {
        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.plusDays(2);
        LocalDate tourDate = tour.getTourDate();
        return !tourDate.isBefore(beforeDate);
    }

    /**
     * Returns the type of car for a given number of passengers.
     *
     * @param quantity The number of passengers.
     * @return The type of car, as a string.
     * @throws IllegalArgumentException If the number of passengers is not within the valid range.
     */
    public String forCarType(Integer quantity) {
        if (quantity == 0) {
            return Transport.UNDEFINED.toString();
        }
        else if (quantity >= 1 && quantity <= 7) {
            return Transport.MINIVAN.toString();
        } else if (quantity >= 8 && quantity <= 18) {
            return Transport.MINIBUS.toString();
        } else if (quantity >= 19 && quantity <= 50) {
            return Transport.BUS.toString();
        }
        throw new IllegalArgumentException();
    }
    
    //TODO: Implement
    public boolean isValidTourForEdit (Tour tour) {
        return false;
    }
    
    
    public boolean isValidTourForRemove (Tour tour) {
        return tourService.getId(tour) != null && tourService.getId(tour) > 0;
    }
}