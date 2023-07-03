package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.enums.enumForTour.*;
import com.example.tourism_management_system.model.pojos.Tour;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Component
public class ValidationForTour {

    /**
     * The tourName method returns the corresponding place name based on the provided tour type and tour name.
     * The tour type and tour name are used to determine the enum value and retrieve the corresponding place name.
     *
     * @param tourType the type of the tour (CULTURAL, CAMPAIGN, or ADVENTURE)
     * @param tourName the name of the tour
     * @return the corresponding place name for the given tour type and tour name, or null if not found or an invalid argument is provided
     */
    public String tourName(String tourType, String tourName) {
        tourName = tourName.toUpperCase();
        try {
            switch (TourType.valueOf(tourType).toString()) {
                case "CULTURAL": {
                    return PlacesForCultural.valueOf(tourName).toString();
                }
                case "CAMPAIGN": {
                    return PlacesForCampaign.valueOf(tourName).toString();
                }
                case "ADVENTURE": {
                    return PlacesForAdventure.valueOf(tourName).toString();
                }
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException i) {
            i.getMessage();
        }
        return null;
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
     * The ValidateTourInformation method validates the tour name and retrieves information about the cultural tour.
     * Finding the name of the tour, it gets the parameters about it
     *
     * @param tourName name the cultural tour
     * @return a list of objects containing information about the cultural tour or void if the tour name is not recognized
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
            e.getMessage();
        }
        return null;
    }

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
     * The isValidTour method checks if a given tour object is valid by validating its tour name, tour date, car type, and start time.
     * If any of these validations fail, the method returns false. Otherwise, it retrieves information about the tour and updates the tour object.
     *
     * @param tour the tour object to be validated
     * @return true if the tour object is valid and information is successfully retrieved, false otherwise
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
        tour.setCost((Integer) list.get(3));
        tour.setFlag(true);
        return true;
    }
    
    public boolean isEnableForBooking(Tour tour, int quantity) {
        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.minusDays(2);
        LocalDate tourDate = tour.getTourDate();
        return !tourDate.isBefore(beforeDate) && (quantity + tour.getGeneralQuantity()) <= tour.getMaxQuantity();
    }

    public boolean isEnableForCanceling(Tour tour) {
        LocalDate currentDate = LocalDate.now();
        LocalDate beforeDate = currentDate.minusDays(2);
        LocalDate tourDate = tour.getTourDate();
        return !tourDate.isBefore(beforeDate);
    }

    public String forCarType(Integer quantity){
        if (quantity >= 1 && quantity <= 7){
            return Transport.MINIVAN.toString();
        }else if (quantity >= 8 && quantity <= 18){
            return Transport.MINIBUS.toString();
        }else if (quantity >= 19 && quantity <= 50){
            return Transport.BUS.toString();
        }
        throw new IllegalArgumentException();
    }
    
    public boolean isValidTourForEdit (Tour tour) {
        
        return false;
    }
}