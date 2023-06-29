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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidationForTourTest {

    @Test
    void testTourName() {
        ValidationForTour validationForTour = new ValidationForTour();

        String culturalTourName = validationForTour.tourName("CULTURAL", "AMBERD");
        Assertions.assertEquals("AMBERD", culturalTourName);

        String culturalTourName1 = validationForTour.tourName("CULTURAL", "Amberd");
        Assertions.assertEquals("AMBERD", culturalTourName1);

        String culturalTourName2 = validationForTour.tourName("CULTURAL", "dilijan");
        Assertions.assertEquals("DILIJAN", culturalTourName2);

        String campaignTourName = validationForTour.tourName("CAMPAIGN", "DIMAC");
        Assertions.assertEquals("DIMAC", campaignTourName);

        String adventureTourName = validationForTour.tourName("ADVENTURE", "LASTIVER");
        Assertions.assertEquals("LASTIVER", adventureTourName);

        String invalidTourType = validationForTour.tourName("UNKNOWN", "Unknown Tour");
        Assertions.assertNull(invalidTourType);

        String invalidTourName = validationForTour.tourName("CULTURAL", "Unknown Place");
        Assertions.assertNull(invalidTourName);

        String invalidTourName1 = validationForTour.tourName("SEVANA", "Unknown Place");
        Assertions.assertNull(invalidTourName1);

        String invalidTourName2 = validationForTour.tourName("CULTURAL", "123ABC");
        Assertions.assertNull(invalidTourName2);

        String invalidTourName3 = validationForTour.tourName("ADVENTURE", "DILIJAN");
        Assertions.assertNull(invalidTourName3);
    }


    @Test
    void testValidateDate() {
        ValidationForTour validationForTour = new ValidationForTour();

        LocalDate futureDate = LocalDate.now().plusDays(4);
        LocalDate validatedDate = validationForTour.validateDate(futureDate);
        Assertions.assertEquals(futureDate, validatedDate);

        LocalDate futureDateExactly3DaysAhead = LocalDate.now().plusDays(3);
        LocalDate validatedDate1 = validationForTour.validateDate(futureDateExactly3DaysAhead);
        Assertions.assertNull(validatedDate1);

        LocalDate tourDate = LocalDate.now().plusDays(2);
        LocalDate validatedDate2 = validationForTour.validateDate(tourDate);
        Assertions.assertNull(validatedDate2);

        LocalDate currentDate = LocalDate.now();
        LocalDate validatedDate3 = validationForTour.validateDate(currentDate);
        assertNull(validatedDate3);

        LocalDate pastDate = LocalDate.now().minusDays(1);
        LocalDate validatedDate4 = validationForTour.validateDate(pastDate);
        Assertions.assertNull(validatedDate4);
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

        LocalTime endStartTime = LocalTime.NOON;
        validatedStartTime = validationForTour.validateStartTime(endStartTime);
        Assertions.assertEquals(null, validatedStartTime);
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

        String carType = "Bus";
        String result = validationForTour.carType(carType);
        Assertions.assertEquals("BUS", result);

        String carTypeInvalid = "INVALID";
        String resultInvalid = validationForTour.carType(carTypeInvalid);
        Assertions.assertNull(resultInvalid);

        String invalidCarType = "Buses";
        String result0 = validationForTour.carType(invalidCarType);
        Assertions.assertEquals(null, result0);
    }

    @Test
    void testToValidateQuantity() {
        ValidationForTour validation = new ValidationForTour();

        String carTypeMinivan = "MINIVAN";
        int quantityForMinivan = validation.validateQuantity(carTypeMinivan);
        Assertions.assertEquals(7, quantityForMinivan);
        Assertions.assertNotEquals(3, quantityForMinivan);
        Assertions.assertNotEquals(14, quantityForMinivan);
        Assertions.assertNotEquals(-5, quantityForMinivan);
        Assertions.assertNotEquals(0, quantityForMinivan);

        String carTypeMinibus = "MINIBUS";
        int quantityForMinibus = validation.validateQuantity(carTypeMinibus);
        Assertions.assertEquals(17, quantityForMinibus);
        Assertions.assertNotEquals(3, quantityForMinibus);
        Assertions.assertNotEquals(21, quantityForMinibus);
        Assertions.assertNotEquals(-1, quantityForMinibus);
        Assertions.assertNotEquals(0, quantityForMinibus);

        String carTypeBus = "BUS";
        int quantityForBus = validation.validateQuantity(carTypeBus);
        Assertions.assertEquals(50, quantityForBus);
        Assertions.assertNotEquals(17, quantityForBus);
        Assertions.assertNotEquals(71, quantityForBus);
        Assertions.assertNotEquals(-3, quantityForBus);
        Assertions.assertNotEquals(0, quantityForBus);

        String carTypeBus1 = "Bus";
        int quantityForBus1 = validation.validateQuantity(carTypeBus1);
        Assertions.assertEquals(50, quantityForBus1);

        int quantityForSedan = validation.validateQuantity("SEDAN");
        assertEquals(-1, quantityForSedan);
    }

    @Test
    void testForValidateTourInformation() {
        ValidationForTour validation = new ValidationForTour();

        String tourType1 = "CULTURAL";
        String tourName1 = "TATEV";
        List<Object> result1 = validation.validateTourInformation(tourType1, tourName1);
        Assertions.assertEquals(4, result1.size());
        assertEquals("TATEV", result1.get(0));
        assertEquals(630, result1.get(1));
        assertNotEquals(10800, result1.get(3));

        String tourType2 = "CULTURAL";
        String tourName2 = "Jermuk";
        List<Object> result2 = validation.validateTourInformation(tourType2, tourName2);
        Assertions.assertEquals(4, result2.size());
        assertEquals("JERMUK", result2.get(0));
        assertEquals(14000, result2.get(3));
        assertNotEquals(11, result2.get(2));

        String tourType3 = "cultural";
        String tourName3 = "LoRi";
        List<Object> result3 = validation.validateTourInformation(tourType3, tourName3);
        Assertions.assertEquals(4, result3.size());
        assertEquals("LORI", result3.get(0));
        assertEquals(390, result3.get(1));
        assertNotEquals("AKHTALA", result3.get(0));

        String tourType4 = "ADVENTURE";
        String tourName4 = "HORSEBACK_AGHMAGHAN";
        List<Object> result4 = validation.validateTourInformation(tourType4, tourName4);
        Assertions.assertEquals(4, result4.size());
        assertEquals("HORSEBACK_AGHMAGHAN", result4.get(0));
        assertEquals(275, result4.get(1));
        assertNotEquals(25000, result4.get(3));

        String tourType5 = "ADVENTURE";
        String tourName5 = "Ziplines";
        List<Object> result5 = validation.validateTourInformation(tourType5, tourName5);
        Assertions.assertEquals(4, result5.size());
        assertEquals("ZIPLINES", result5.get(0));
        assertEquals(28000, result5.get(3));
        assertNotEquals(15, result5.get(2));

        String tourType6 = "adventure";
        String tourName6 = "LaSTiVeR";
        List<Object> result6 = validation.validateTourInformation(tourType6, tourName6);
        Assertions.assertEquals(4, result6.size());
        assertEquals("LASTIVER", result6.get(0));
        assertEquals(280, result6.get(1));
        assertNotEquals("RAFTING", result6.get(0));
        assertNotEquals("GEGHARD", result6.get(0));

        String tourType7 = "CAMPAIGN";
        String tourName7 = "SMBATABERD";
        List<Object> result7 = validation.validateTourInformation(tourType7, tourName7);
        Assertions.assertEquals(4, result7.size());
        assertEquals("SMBATABERD", result7.get(0));
        assertEquals(310, result7.get(1));
        assertNotEquals(18000, result7.get(3));

        String tourType8 = "CAMPAIGN";
        String tourName8 = "Azhdahak";
        List<Object> result8 = validation.validateTourInformation(tourType8, tourName8);
        Assertions.assertEquals(4, result8.size());
        assertEquals("AZHDAHAK", result8.get(0));
        assertEquals(7000, result8.get(3));
        assertNotEquals(12, result8.get(2));

        String tourType9 = "campaign";
        String tourName9 = "ArAgAts";
        List<Object> result9 = validation.validateTourInformation(tourType9, tourName9);
        Assertions.assertEquals(4, result9.size());
        assertEquals("ARAGATS", result9.get(0));
        assertEquals(130, result9.get(1));
        assertNotEquals("AGHMAGHAN", result9.get(0));

        String invalidTourType = "ABC";
        String validTourName = "TATEV";
        List<Object> result0 = validation.validateTourInformation(invalidTourType, validTourName);
        assertNull(result0);

        String invalidTourType1 = "1A";
        String validTourName1 = "ECHMIADZIN";
        List<Object> result00 = validation.validateTourInformation(invalidTourType1, validTourName1);
        assertNull(result00);

        String validTourType = "ADVENTURE";
        String invalidTourName = "123";
        List<Object> result = validation.validateTourInformation(validTourType, invalidTourName);
        assertNull(result);

        String validTourType1 = "CULTURAL";
        String invalidTourName1 = "SEVANA";
        List<Object> result01 = validation.validateTourInformation(validTourType1, invalidTourName1);
        assertNull(result01);
    }

    @Test
    public void testForCultural_ValidTourName() {
        ValidationForTour validation = new ValidationForTour();

        String validTourName = "SEVAN";
        List<Object> result = validation.forCultural(validTourName);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals(validTourName.toUpperCase(), result.get(0));
        Assertions.assertEquals(validTourName, result.get(0));
        Assertions.assertEquals(250, result.get(1));
        Assertions.assertEquals(9, result.get(2));
        Assertions.assertEquals(7000, result.get(3));
        Assertions.assertNotEquals(270, result.get(1));

        List<Object> result1 = validation.forCultural("Sevan");
        Assertions.assertEquals("SEVAN", result1.get(0));
        Assertions.assertNotEquals(12, result1.get(2));

        String invalidTourName = "Garni";
        Assertions.assertNotEquals(invalidTourName, result.get(0));
        Assertions.assertFalse(validTourName.equals(invalidTourName));
        assertNotEquals(validTourName, invalidTourName);
        Assertions.assertEquals(9, result.get(2));
    }

    @Test
    public void testForAdventure_ValidTourName() {
        ValidationForTour validation = new ValidationForTour();

        String validTourName = "RAFTING";
        List<Object> result = validation.forAdventure(validTourName);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals(validTourName.toUpperCase(), result.get(0));
        Assertions.assertEquals(validTourName, result.get(0));
        Assertions.assertEquals(370, result.get(1));
        Assertions.assertEquals(10, result.get(2));
        Assertions.assertEquals(25000, result.get(3));
        Assertions.assertNotEquals(270, result.get(1));

        List<Object> result1 = validation.forAdventure("Rafting");
        Assertions.assertEquals("RAFTING", result1.get(0));
        Assertions.assertNotEquals(15, result1.get(2));

        String invalidTourName = "Zip_Lines";
        Assertions.assertNotEquals(invalidTourName, result.get(0));
        Assertions.assertFalse(validTourName.equals(invalidTourName));
        assertNotEquals(validTourName, invalidTourName);
    }

    @Test
    public void testForCampaign_ValidTourName() {
        ValidationForTour validation = new ValidationForTour();

        String validTourName = "DIMAC";
        List<Object> result = validation.forCampaign(validTourName);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals(validTourName.toUpperCase(), result.get(0));
        Assertions.assertEquals(validTourName, result.get(0));
        Assertions.assertEquals(220, result.get(1));
        Assertions.assertEquals(12, result.get(2));
        Assertions.assertEquals(10000, result.get(3));
        Assertions.assertNotEquals(270, result.get(1));

        List<Object> result1 = validation.forCampaign("Dimac");
        Assertions.assertEquals("DIMAC", result1.get(0));
        Assertions.assertNotEquals(15, result1.get(2));

        String invalidTourName = "Apakeqar";
        Assertions.assertNotEquals(invalidTourName, result.get(0));
        Assertions.assertFalse(validTourName.equals(invalidTourName));
        assertNotEquals(validTourName, invalidTourName);
    }

    @Test
    void testForIsValidTour() {
        ValidationForTour validationForTour = new ValidationForTour();

        Tour validTour = new Tour();
        validTour.setTourType("ADVENTURE");
        validTour.setTourName("LastiveR");
        validTour.setTourDate(LocalDate.now().plusDays(4));
        validTour.setStartTime(LocalTime.of(8, 0));
        validTour.setCarType("MINIVAN");
        boolean resultValid = validationForTour.isValidTour(validTour);
        assertTrue(resultValid);
        Assertions.assertEquals("280 km", validTour.getDistance());
        Assertions.assertEquals("9 hours", validTour.getDuration());
        Assertions.assertEquals(17000, validTour.getCost());
        Assertions.assertNotEquals(25000, validTour.getCost());

        Tour tour = new Tour();
        tour.setTourType("");
        tour.setTourName("LastiveR");
        tour.setTourDate(LocalDate.now().plusDays(4));
        tour.setStartTime(LocalTime.of(8, 0));
        tour.setCarType("MINIVAN");
        boolean result = validationForTour.isValidTour(tour);
        assertFalse(result);

        Tour tour1 = new Tour();
        tour1.setTourType("Adventure");
        tour1.setTourName("");
        tour1.setTourDate(LocalDate.now().plusDays(4));
        tour1.setStartTime(LocalTime.of(8, 0));
        tour1.setCarType("MINIVAN");
        boolean result1 = validationForTour.isValidTour(tour1);
        assertFalse(result1);

        Tour tour2 = new Tour();
        tour2.setTourType("Adventure");
        tour2.setTourName("Lastiver");
        tour2.setTourDate(null);
        tour2.setStartTime(LocalTime.of(8, 0));
        tour2.setCarType("MINIVAN");
        boolean result2 = validationForTour.isValidTour(tour2);
        assertFalse(result2);

        Tour tour3 = new Tour();
        tour3.setTourType("Adventure");
        tour3.setTourName("");
        tour3.setTourDate(LocalDate.now().plusDays(4));
        tour3.setStartTime(null);
        tour3.setCarType("MINIVAN");
        boolean result3 = validationForTour.isValidTour(tour3);
        assertFalse(result3);

        Tour tour4 = new Tour();
        tour4.setTourType("Adventure");
        tour4.setTourName("");
        tour4.setTourDate(LocalDate.now().plusDays(4));
        tour4.setStartTime(LocalTime.of(7, 0));
        tour4.setCarType("");
        boolean result4 = validationForTour.isValidTour(tour4);
        assertFalse(result4);

        Tour tour5 = new Tour();
        tour5.setTourType("");
        tour5.setTourName("");
        tour5.setTourDate(null);
        tour5.setStartTime(null);
        tour5.setCarType("");
        boolean result5 = validationForTour.isValidTour(tour5);
        assertFalse(result5);

        Tour invalidTourType = new Tour();
        invalidTourType.setTourType("ABC");
        invalidTourType.setTourName("LASTIVER");
        invalidTourType.setTourDate(LocalDate.now().plusDays(5));
        invalidTourType.setCarType("MINIBUS");
        invalidTourType.setStartTime(LocalTime.of(9, 0));
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
    void testForIsEnableForBooking() {
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
        tour1.setGeneralQuantity(2);
        tour1.setMaxQuantity(7);
        boolean result1 = validationForTour.isEnableForBooking(tour1, 4);
        assertTrue(result1);

        Tour tour2 = new Tour();
        LocalDate tourDate2 = LocalDate.now();
        tour2.setTourDate(tourDate2);
        tour2.setGeneralQuantity(7);
        tour2.setMaxQuantity(10);
        boolean result2 = validationForTour.isEnableForBooking(tour2, 4);
        assertFalse(result2);

        Tour tour3 = new Tour();
        LocalDate tourDate3 = LocalDate.now().plusDays(1);
        tour3.setTourDate(tourDate3);
        tour3.setGeneralQuantity(13);
        tour3.setMaxQuantity(15);
        boolean result3 = validationForTour.isEnableForBooking(tour3, 3);
        assertFalse(result3);

        Tour tour4 = new Tour();
        LocalDate tourDate4 = LocalDate.now().minusDays(1);
        tour4.setTourDate(tourDate4);
        tour4.setGeneralQuantity(5);
        tour4.setMaxQuantity(10);
        boolean result4 = validationForTour.isEnableForBooking(tour4, 7);
        assertFalse(result4);
    }

    @Test
    void testForIsEnableForCanceling() {
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


    @Mock
    private Tour tour;

    @InjectMocks
    private ValidationForTour validationForTour;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validationForTour = new ValidationForTour();
    }

    @Test
    void isEnableForBookingTestWithMock_beforeTourDate() {
        LocalDate tourDate = LocalDate.now().minusDays(1);
        int quantity = 4;
        int generalQuantity = 3;
        int maxQuantity = 10;
        when(tour.getTourDate()).thenReturn(tourDate);
        when(tour.getGeneralQuantity()).thenReturn(generalQuantity);
        when(tour.getMaxQuantity()).thenReturn(maxQuantity);
        Boolean result = validationForTour.isEnableForBooking(tour, quantity);
        verify(tour).getTourDate();
        verify(tour).getGeneralQuantity();
        verify(tour).getMaxQuantity();
        verify(tour, times(1)).getTourDate();
        verify(tour, times(1)).getGeneralQuantity();
        verify(tour, times(1)).getMaxQuantity();
        verifyNoMoreInteractions(tour);
        assertEquals(true, result);
        assertTrue(validationForTour.isEnableForBooking(tour, quantity));
    }

    @Test
    void isEnableForBookingTestWithMock_beforeTourDateAndInvalidQuantity() {
        LocalDate tourDate1 = LocalDate.now().minusDays(1);
        int quantity1 = 4;
        int generalQuantity1 = 7;
        int maxQuantity1 = 10;
        when(tour.getTourDate()).thenReturn(tourDate1);
        when(tour.getGeneralQuantity()).thenReturn(generalQuantity1);
        when(tour.getMaxQuantity()).thenReturn(maxQuantity1);
        Boolean result1 = validationForTour.isEnableForBooking(tour, quantity1);
        verify(tour).getTourDate();
        verify(tour).getGeneralQuantity();
        verify(tour).getMaxQuantity();
        verify(tour, times(1)).getTourDate();
        verify(tour, times(1)).getGeneralQuantity();
        verify(tour, times(1)).getMaxQuantity();
        verifyNoMoreInteractions(tour);
        assertEquals(false, result1);
        assertFalse(validationForTour.isEnableForBooking(tour, quantity1));
    }

    @Test
    void isEnableForBookingTestWithMock_onTheDayOfTheTour() {
        LocalDate tourDate = LocalDate.now();
        int quantity = 2;
        int generalQuantity = 3;
        int maxQuantity = 10;
        when(tour.getTourDate()).thenReturn(tourDate);
        when(tour.getGeneralQuantity()).thenReturn(generalQuantity);
        when(tour.getMaxQuantity()).thenReturn(maxQuantity);
        Boolean result = validationForTour.isEnableForBooking(tour, quantity);
        verify(tour).getTourDate();
//        verify(tour).getGeneralQuantity();
//        verify(tour).getMaxQuantity();
        verify(tour, times(1)).getTourDate();
//        verify(tour, times(1)).getGeneralQuantity();
//        verify(tour, times(1)).getMaxQuantity();
        verifyNoMoreInteractions(tour);
        assertEquals(false, result);
        assertFalse(validationForTour.isEnableForBooking(tour, quantity));
    }

    @Test
    void isEnableForBookingTestWithMock_afterTourDate() {
        LocalDate tourDate = LocalDate.now().plusDays(1);
        int quantity = 3;
        int generalQuantity = 5;
        int maxQuantity = 10;
        when(tour.getTourDate()).thenReturn(tourDate);
        when(tour.getGeneralQuantity()).thenReturn(generalQuantity);
        when(tour.getMaxQuantity()).thenReturn(maxQuantity);
        Boolean result = validationForTour.isEnableForBooking(tour, quantity);
        verify(tour).getTourDate();
//        verify(tour).getGeneralQuantity();
//        verify(tour).getMaxQuantity();
        verify(tour, times(1)).getTourDate();
//        verify(tour, times(1)).getGeneralQuantity();
//        verify(tour, times(1)).getMaxQuantity();
        verifyNoMoreInteractions(tour);
        assertEquals(false, result);
        assertFalse(validationForTour.isEnableForBooking(tour, quantity));
    }

    @Test
    void isEnableForCancelingTestWithMock_beforeTourDate() {
        LocalDate tourDate = LocalDate.now().minusDays(1);
        when(tour.getTourDate()).thenReturn(tourDate);
        Boolean result = validationForTour.isEnableForCanceling(tour);
        verify(tour).getTourDate();
        verify(tour, times(1)).getTourDate();
        verifyNoMoreInteractions(tour);
        assertEquals(true, result);
        assertTrue(validationForTour.isEnableForCanceling(tour));
    }

    @Test
    void isEnableForCancelingTestWithMock_onTheDayOfTheTour() {
        LocalDate tourDate = LocalDate.now();
        when(tour.getTourDate()).thenReturn(tourDate);
        Boolean result = validationForTour.isEnableForCanceling(tour);
        verify(tour).getTourDate();
        verify(tour, times(1)).getTourDate();
        verifyNoMoreInteractions(tour);
        assertEquals(false, result);
        assertFalse(validationForTour.isEnableForCanceling(tour));
    }

    @Test
    void isEnableForCancelingTestWithMock_afterTourDate() {
        LocalDate tourDate = LocalDate.now().plusDays(1);
        when(tour.getTourDate()).thenReturn(tourDate);
        Boolean result = validationForTour.isEnableForCanceling(tour);
        verify(tour).getTourDate();
        verify(tour, times(1)).getTourDate();
        verifyNoMoreInteractions(tour);
        assertEquals(false, result);
        assertFalse(validationForTour.isEnableForCanceling(tour));
    }
}