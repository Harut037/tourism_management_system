package com.example.tourism_management_system.validation.tour;


import com.example.tourism_management_system.model.enums.enumForTour.*;
import com.example.tourism_management_system.model.pojos.Tour;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;


public class ValidationForTour {


//    public String tourTypeValidate(String tourType) {
//        switch (TourType.valueOf(tourType)) {
//            case CULTURAL -> {
//                tourName("CULTURAL");
//                return "CULTURAL";
//            }
//            case CAMPAIGN -> {
//                tourName("CAMPAIGN");
//                return "CAMPAIGN";
//            }
//
//            case ADVENTURE -> {
//                tourName("ADVENTURE");
//                return "ADVENTURE";
//            }
//            default -> {
//                return "There is not such tour type";
//            }
//        }
//    }


    public String tourName(String tourType, String tourName) {

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
                    return null;
            }
        } catch (IllegalArgumentException i) {
            return null;
        }
    }

    public LocalDate validateDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(3);
        if (date.isAfter(futureDate)) {
            return date;
        }
        return null;
    }

    public LocalTime validateStartTime(LocalTime startTime) {
        LocalTime currentTime = LocalTime.now();
        currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        if (startTime.isAfter(LocalTime.of(06, 59)) && startTime.isBefore(LocalTime.NOON)) {
            return startTime;
        } else return null;
    }

    public String carType(String carType) {
        switch (Transport.valueOf(carType)) {

            case BUS -> {
                validateQuantity(carType);
                return "BUS";
            }

            case MINIBUS -> {
                validateQuantity(carType);
                return "MINIBUS";
            }

            case MINIVAN -> {
                validateQuantity(carType);
                return "MINIVAN";
            }
            default -> {
                return "There is not such car type";
            }
        }
    }


    public int validateQuantity(String carType) {
        switch (Transport.valueOf(carType)) {
            case SEDAN -> {
                return 4;
            }
            case MINIVAN -> {
                return 7;
            }
            case MINIBUS -> {
                return 15;
            }
            case BUS -> {
                return 30;
            }
        }
        return -1;
    }

    public List<Object> validateTourInformation(String tourName) {
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


    public boolean isValidTour(Tour tour) {
        if (tourName(tour.getTourType(), tour.getTourName()) == null ||
                validateDate(tour.getTourDate()) == null ||
                carType(tour.getCarType()) == null ||
        validateStartTime(tour.getStartTime()) == null
        ){
            return false;
        }
        List<Object> list = validateTourInformation(tour.getTourName());
        tour.setDistance(list.get(1) + " km");
        tour.setDuration(list.get(2) + " hours");
        tour.setCost((Integer) list.get(3));
        tour.setFlag(true);
        return true;
    }
}