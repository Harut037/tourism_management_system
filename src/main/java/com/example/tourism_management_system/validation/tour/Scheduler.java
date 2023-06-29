package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.repository.TourRepository;
import com.example.tourism_management_system.service.impl.JwtService;
import com.example.tourism_management_system.service.impl.TourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Scheduler {
    
    private final TourServiceImpl tourService;
    private final JwtService jwtService;
    private final ValidationForTour validation;
    private final TourRepository tourRepository;

    @Autowired
    public Scheduler(TourServiceImpl tourService, JwtService jwtService, TourRepository tourRepository,  ValidationForTour validation) {
        this.tourService = tourService;
        this.jwtService = jwtService;
        this.validation = validation;
        this.tourRepository = tourRepository;
    }

    /**
     * Deletes tours that have dates before the current date.
     * This method is scheduled to run at midnight every day.
     */
//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 0 * * * *")
    public void deletePastDateTours() {
        LocalDate currentDate = LocalDate.now();
        List<TourEntity> tours = tourService.getAllForSchedule();
        for (TourEntity tour : tours) {
            if (tour.getTourDate().minusDays(2).isBefore(currentDate) || tour.getTourDate().minusDays(2).isEqual(currentDate)){
               String s = validation.forCarType(tour.getGeneralQuantity());
                tourRepository.updateCarType(s,tour.getTourName(),tour.getTourDate());
            }
            if (tour.getTourDate().isBefore(currentDate)) {
                tourService.deleteById(tour.getId());
            }
        }
    }
    
    @Scheduled(fixedRate = 1000*60*30)
    public void deleteExpiredTokensFromBlackList() {
        Map<String, Boolean> map = JwtService.invalidatedTokens;
        Map<String, Boolean> newMap = new HashMap <>();
        for (String token : map.keySet()) {
            Boolean expired;
            try {
                expired = jwtService.isTokenExpired(token);
            } catch (Exception e) {
                expired = true;
            }
            if (expired == null || !expired) {
                newMap.put(token, true);
            }
        }
        JwtService.invalidatedTokens = newMap;
    }
}