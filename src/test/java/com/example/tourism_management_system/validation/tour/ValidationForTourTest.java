package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.pojos.Tour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidationForTourTest {

    @Test
    void testTourName() {
        ValidationForTour validationForTour = new ValidationForTour();

        String culturalTourName = validationForTour.tourName("CULTURAL", "AMBERD");
        Assertions.assertEquals("AMBERD", culturalTourName);

        String campaignTourName = validationForTour.tourName("CAMPAIGN", "DIMAC");
        Assertions.assertEquals("DIMAC", campaignTourName);

        String adventureTourName = validationForTour.tourName("ADVENTURE", "LASTIVER");
        Assertions.assertEquals("LASTIVER", adventureTourName);

        String invalidTourType = validationForTour.tourName("UNKNOWN", "Unknown Tour");
        Assertions.assertNull(invalidTourType);

        String invalidTourName = validationForTour.tourName("CULTURAL", "Unknown Place");
        Assertions.assertNull(invalidTourName);
    }


    @Test
    void testValidateDate() {
        ValidationForTour validationForTour = new ValidationForTour();

        LocalDate futureDate = LocalDate.now().plusDays(4);
        LocalDate validatedDate = validationForTour.validateDate(futureDate);
        Assertions.assertEquals(futureDate, validatedDate);

        LocalDate futureDateExactly3DaysAhead = LocalDate.now().plusDays(3);
        validatedDate = validationForTour.validateDate(futureDateExactly3DaysAhead);
        Assertions.assertNull(validatedDate);

        LocalDate currentDate = LocalDate.now();
        validatedDate = validationForTour.validateDate(currentDate);
        Assertions.assertNull(validatedDate);

        LocalDate pastDate = LocalDate.now().minusDays(1);
        validatedDate = validationForTour.validateDate(pastDate);
        Assertions.assertNull(validatedDate);
    }

    @Test
    void testValidateStartTime() {
        ValidationForTour validationForTour = new ValidationForTour();

        LocalTime validStartTime = LocalTime.of(9, 30);
        LocalTime validatedStartTime = validationForTour.validateStartTime(validStartTime);
        Assertions.assertEquals(validStartTime, validatedStartTime);

        LocalTime earlyStartTime = LocalTime.of(6, 0);
        validatedStartTime = validationForTour.validateStartTime(earlyStartTime);
        Assertions.assertNull(validatedStartTime);

        LocalTime lateStartTime = LocalTime.of(13, 30);
        validatedStartTime = validationForTour.validateStartTime(lateStartTime);
        Assertions.assertNull(validatedStartTime);

        LocalTime lowerBoundaryStartTime = LocalTime.of(6, 59);
        validatedStartTime = validationForTour.validateStartTime(lowerBoundaryStartTime);
        Assertions.assertNull(validatedStartTime);

    }

    @Test
    void testCarType() {
        ValidationForTour validationForTour = new ValidationForTour();

        String carTypeBus = "BUS";
        String resultBus = validationForTour.carType(carTypeBus);
        Assertions.assertEquals("BUS", resultBus);

        String carTypeMinibus = "MINIBUS";
        String resultMinibus = validationForTour.carType(carTypeMinibus);
        Assertions.assertEquals("MINIBUS", resultMinibus);

        String carTypeMinivan = "MINIVAN";
        String resultMinivan = validationForTour.carType(carTypeMinivan);
        Assertions.assertEquals("MINIVAN", resultMinivan);

        String carTypeInvalid = "INVALID";
        String resultInvalid = validationForTour.carType(carTypeInvalid);
        Assertions.assertNull(resultInvalid);
    }


    @Test
    void validateQuantity() {
    }

    @Test
    void validateTourInformation() {
    }

    //    @Test
//    void testValidateTourInformation() {
//        ValidationForTour validationForTour = new ValidationForTour();
//
//        String tourNameValid = "GEGHARD";
//        List<Object> resultValid = validationForTour.validateTourInformation(tourNameValid);
//        Assertions.assertNotNull(resultValid);
//        Assertions.assertEquals(4, resultValid.size());
//        Assertions.assertEquals(tourNameValid, resultValid.get(0));
//        Assertions.assertEquals(350, resultValid.get(1));
//        Assertions.assertEquals(10, resultValid.get(2));
//        Assertions.assertEquals(9000, resultValid.get(3));
//
//        String tourNameInvalid = "INVALID";
//        List<Object> resultInvalid = validationForTour.validateTourInformation(tourNameInvalid);
//        Assertions.assertNull(resultInvalid);
//    }


    @Test
    void forCultural() {
    }

    @Test
    void forAdventure() {
    }

    @Test
    void forCampaign() {
    }

    @Test
    void testIsValidTour() {
        ValidationForTour validationForTour = new ValidationForTour();

        Tour validTour = new Tour();
        validTour.setTourType("ADVENTURE");
        validTour.setTourName("LASTIVER");
        validTour.setTourDate(LocalDate.now().plusDays(4));
        validTour.setCarType("MINIVAN");
        validTour.setStartTime(LocalTime.of(8, 0));
        boolean resultValid = validationForTour.isValidTour(validTour);
        assertTrue(resultValid);
        Assertions.assertEquals("450 km", validTour.getDistance());
        Assertions.assertEquals("14 hours", validTour.getDuration());
        Assertions.assertEquals(16000, validTour.getCost());

        Tour invalidTourType = new Tour();
        invalidTourType.setTourType("INVALID");
        invalidTourType.setTourName("LASTIVER");
        invalidTourType.setTourDate(LocalDate.now().plusDays(4));
        invalidTourType.setCarType("MINIVAN");
        invalidTourType.setStartTime(LocalTime.of(8, 0));
        boolean resultInvalidType = validationForTour.isValidTour(invalidTourType);
        assertFalse(resultInvalidType);

        Tour invalidStartTime = new Tour();
        invalidStartTime.setTourType("ADVENTURE");
        invalidStartTime.setTourName("LASTIVER");
        invalidStartTime.setTourDate(LocalDate.now().plusDays(4));
        invalidStartTime.setCarType("MINIVAN");
        invalidStartTime.setStartTime(LocalTime.of(5, 0));
        boolean resultInvalidTime = validationForTour.isValidTour(invalidStartTime);
        assertFalse(resultInvalidTime);
    }


    @Test
    void isEnableForBooking() {
        ValidationForTour validationForTour = new ValidationForTour();

        Tour tour = new Tour();
        LocalDate tourDate = LocalDate.now().minusDays(1);
        tour.setTourDate(tourDate);
        tour.setGeneralQuantity(5);
        tour.setMaxQuantity(10);
        boolean result = validationForTour.isEnableForBooking(tour, 5);
        assertTrue(result);

        Tour tour1 = new Tour();
        LocalDate tourDate1 = LocalDate.now().minusDays(2);
        tour1.setTourDate(tourDate1);
        tour1.setGeneralQuantity(4);
        tour1.setMaxQuantity(7);
        boolean result1 = validationForTour.isEnableForBooking(tour1, 4);
        assertTrue(result1);

        Tour tour2 = new Tour();
        LocalDate tourDate2 = LocalDate.now();
        tour2.setTourDate(tourDate2);
        tour2.setGeneralQuantity(7);
        tour2.setMaxQuantity(10);
        boolean result2 = validationForTour.isEnableForBooking(tour2, 7);
        assertFalse(result2);

        Tour tour3 = new Tour();
        LocalDate tourDate3 = LocalDate.now().plusDays(1);
        tour3.setTourDate(tourDate3);
        tour3.setGeneralQuantity(13);
        tour3.setMaxQuantity(15);
        boolean result3 = validationForTour.isEnableForBooking(tour3, 13);
        assertFalse(result3);
    }

    @Test
    void isEnableForCanceling() {
        ValidationForTour validationForTour = new ValidationForTour();

        Tour tour = new Tour();
        LocalDate tourDate = LocalDate.now().minusDays(1);
        tour.setTourDate(tourDate);
        boolean result = validationForTour.isEnableForCanceling(tour);
        assertTrue(result);

        Tour tour1 = new Tour();
        LocalDate tourDate1 = LocalDate.now().minusDays(2);
        tour1.setTourDate(tourDate1);
        boolean result1 = validationForTour.isEnableForCanceling(tour1);
        assertTrue(result1);

        Tour tour2 = new Tour();
        LocalDate tourDate2 = LocalDate.now();
        tour2.setTourDate(tourDate2);
        boolean result2 = validationForTour.isEnableForCanceling(tour2);
        assertFalse(result2);

        Tour tour3 = new Tour();
        LocalDate tourDate3 = LocalDate.now().plusDays(1);
        tour3.setTourDate(tourDate3);
        boolean result3 = validationForTour.isEnableForCanceling(tour3);
        assertFalse(result3);
    }
}