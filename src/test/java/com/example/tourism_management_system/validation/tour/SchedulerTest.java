package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.enums.Status;
import com.example.tourism_management_system.model.enums.enumForTour.Transport;
import com.example.tourism_management_system.repository.TourRepository;
import com.example.tourism_management_system.repository.UserInTourRepository;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.impl.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SchedulerTest {
    @Mock
    private TourService tourService;
    @Mock
    private JwtService jwtService;
    @Mock
    private ValidationForTour validation;
    @Mock
    private TourRepository tourRepository;
    @Mock
    private UserInTourRepository userInTourRepository;
    @InjectMocks
    private Scheduler scheduler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
//        scheduler = new Scheduler(tourService, jwtService, tourRepository, validation, userInTourRepository);
    }

    @Test
    void testDeletePastDateTours() {
        LocalDate currentDate = LocalDate.now();
        List<TourEntity> tours = new ArrayList<>();
        TourEntity tour1 = new TourEntity();
        tour1.setId(1L);
        tour1.setTourDate(currentDate.minusDays(1));
        tour1.setStatus(Status.ACTIVE);
        tours.add(tour1);

        TourEntity tour2 = new TourEntity();
        tour2.setId(2L);
        tour2.setTourDate(currentDate.plusDays(1));
        tour2.setStatus(Status.ACTIVE);
        tours.add(tour2);

        when(tourService.getAllForSchedule()).thenReturn(tours);
        when(validation.forCarType(anyInt())).thenReturn(Transport.values().toString());

        scheduler.deletePastDateTours();
    }

    @Test
    void testDeleteExpiredTokensFromBlackList() {
        Map<String, Boolean> invalidatedTokens = new HashMap<>();
        invalidatedTokens.put("token1", false);
        invalidatedTokens.put("token2", true);
        invalidatedTokens.put("token3", false);

        Map<String, Boolean> newMap = new HashMap<>();
        newMap.put("token1", false);
        newMap.put("token3", false);

        when(jwtService.isTokenExpired("token1")).thenReturn(false);
        when(jwtService.isTokenExpired("token2")).thenReturn(true);
        when(jwtService.isTokenExpired("token3")).thenReturn(false);

        JwtService.invalidatedTokens = invalidatedTokens;

        scheduler.deleteExpiredTokensFromBlackList();
    }
}