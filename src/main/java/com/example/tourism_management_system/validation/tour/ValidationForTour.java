package com.example.tourism_management_system.validation.tour;


import com.example.tourism_management_system.model.enums.enumForTour.PlacesForCultural;
import com.example.tourism_management_system.model.enums.enumForTour.TourType;
import com.example.tourism_management_system.model.enums.enumForTour.Transport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;


public class ValidationForTour {


    public String tourTypeValidate(String tourType) {


        switch (TourType.valueOf(tourType)) {
            case CULTURAL -> {
                tourName("CULTURAL");
                return "CULTURAL";
            }
            case CAMPAIGN -> {
                tourName("CAMPAIGN");
                return "CAMPAIGN";
            }

            case ADVENTURE -> {
                tourName("ADVENTURE");
                return "ADVENTURE";
            }
            default -> {
                return "There is not such tour type";
            }
        }
    }


    public List<String> tourName(String tourName) {
        List<String> list;
        if (tourName.equals("CULTURAL")) {
            return list = new ArrayList<>(Arrays.asList("GEGHARD", "ECHMIADZIN", "AMBERD",
                    "GYUMRI", "DILIJAN", "LORI",
                    "SEVAN", "AKHTALA", "JERMUK", "TATEV"));
        }
        if (tourName.equals("CAMPAIGN")) {
            return list = new ArrayList<>(Arrays.asList("DIMAC", "HATIS", "AGHMAGHAN"));
        }

        if (tourName.equals("ADVENTURE")) {
            return list = new ArrayList<>(Arrays.asList("LASTIVER", "RAFTING"));
        }
        return null;
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

    public List<Object> validateTourInformation(String name){
        List<Object> objectList = new ArrayList<>();
        PlacesForCultural plf = PlacesForCultural.valueOf(name);
        if (EnumSet.allOf(PlacesForCultural.class).contains(plf)){
            objectList.add(name);
            objectList.add(plf.getDistance());
            objectList.add(plf.getDuration());
            objectList.add(plf.getCost());
        }
        return objectList;
    }

}