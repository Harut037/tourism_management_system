package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.service.TourService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class TourSchedulerTest {

    @Mock
    private TourService tourService;

    @InjectMocks
    private TourScheduler tourScheduler;


    /**
     * Set up method executed before each test case.
     * Initializes the necessary dependencies and creates an instance of the TourScheduler class.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tourScheduler = new TourScheduler(tourService);
    }


    /**
     * Test case to verify the deletion of tours before the current date.
     * The method verifies that tours with dates before the current date are deleted correctly.
     */
    @Test
    void deletePastDateTours_shouldDeleteToursBeforeCurrentDate1() {

        LocalDate currentDate = LocalDate.of(2023, 6, 18);

        List<TourEntity> tours = new ArrayList<>();
        TourEntity tour1 = new TourEntity();
        tour1.setId(1L);
        tour1.setTourDate(LocalDate.of(2023, 6, 16));
        tours.add(tour1);

        TourEntity tour2 = new TourEntity();
        tour2.setId(2L);
        tour2.setTourDate(LocalDate.of(2023, 6, 17));
        tours.add(tour2);

        when(tourService.getAllForSchedule()).thenReturn(tours);
        tourScheduler.deletePastDateTours();
        verify(tourService, times(2)).deleteById(anyLong());
        verify(tourService).deleteById(1L);
        verify(tourService).deleteById(2L);
    }


    /**
     * Test case to verify that the method deletePastDateTours() does not delete tours
     * that have tour dates on the current date or future dates.
     */
    @Test
    void deletePastDateTours_shouldNotDeleteToursOnCurrentDateOrFutureDates() {
        LocalDate currentDate = LocalDate.of(2023, 6, 18);

        List<TourEntity> tours = new ArrayList<>();
        TourEntity tour1 = new TourEntity();
        tour1.setId(1L);
        tour1.setTourDate(LocalDate.of(2023, 6, 18));
        tours.add(tour1);

        TourEntity tour2 = new TourEntity();
        tour2.setId(2L);
        tour2.setTourDate(LocalDate.of(2023, 6, 19));
        tours.add(tour2);

        when(tourService.getAllForSchedule()).thenReturn(tours);
        tourScheduler.deletePastDateTours();
        verify(tourService, never()).deleteById(anyLong());
    }


    /**
     * Test case to verify the deletion of tours before the current date.
     * The method verifies that tours with dates before the current date are deleted correctly.
     */
    @Test
    void deletePastDateTours_ShouldDeleteToursBeforeCurrentDate2() {
        LocalDate currentDate = LocalDate.now();
        List<TourEntity> tours = new ArrayList<>();
        tours.add(createTourEntity("Tour 1", currentDate.minusDays(1)));
        tours.add(createTourEntity("Tour 2", currentDate.minusDays(2)));
        tours.add(createTourEntity("Tour 3", currentDate.plusDays(1)));

        when(tourService.getAllForSchedule()).thenReturn(tours);
        tourScheduler.deletePastDateTours();
        verify(tourService, never()).deleteById(anyLong());
    }


    /**
     * Creates a new instance of TourEntity with the given tour name and tour date.
     *
     * @param tourName the name of the tour
     * @param tourDate the date of the tour
     * @return a new TourEntity object with the specified tour name and tour date
     */
    private TourEntity createTourEntity(String tourName, LocalDate tourDate) {
        TourEntity tourEntity = new TourEntity();
        tourEntity.setTourName(tourName);
        tourEntity.setTourDate(tourDate);
        return tourEntity;
    }
}