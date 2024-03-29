package com.example.tourism_management_system.validation.tour;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.enums.Status;
import com.example.tourism_management_system.model.enums.enumForTour.Transport;
import com.example.tourism_management_system.repository.TourRepository;
import com.example.tourism_management_system.repository.UserInTourRepository;
import com.example.tourism_management_system.service.TourService;
import com.example.tourism_management_system.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Scheduler {

    private final TourService tourService;
    private final JwtService  jwtService;
    private final ValidationForTour validation;
    private final TourRepository tourRepository;
    private final UserInTourRepository userInTourRepository;

    @Autowired
    public Scheduler(TourService tourService, JwtService jwtService, TourRepository tourRepository, ValidationForTour validation, UserInTourRepository userInTourRepository) {
        this.tourService = tourService;
        this.jwtService = jwtService;
        this.validation = validation;
        this.tourRepository = tourRepository;
        this.userInTourRepository = userInTourRepository;
    }

    /**
     * Deletes past date tours and updates car type for upcoming tours.
     * This method is scheduled to run every hour.
     */
//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 0 * * * *")
    public void deletePastDateTours() {
        LocalDate currentDate = LocalDate.now();
        List<TourEntity> tours = tourService.getAllForSchedule();
        for (TourEntity tour : tours) {
            if (tour.getTourDate().isBefore(currentDate) && (tour.getStatus().equals(Status.ACTIVE) || tour.getStatus().equals(Status.RESERVED))) {
                tourService.doneById(tour.getId());
            }
            if (tour.getStatus().equals(Status.ACTIVE) && tour.getTourDate().minusDays(2).isBefore(currentDate) || tour.getTourDate().minusDays(2).isEqual(currentDate)) {
                String s = validation.forCarType(tour.getGeneralQuantity());
                tourRepository.updateCarType(Transport.valueOf(s), tour.getTourName(), tour.getTourDate());
                tourRepository.reserved(tour.getId(), Status.RESERVED);
                userInTourRepository.done(tour, Status.DONE);
            }
        }
    }

    /**
     * Deletes expired tokens from the blacklist.
     * This method is scheduled to run at a fixed rate of 30 minutes.
     * It retrieves the list of invalidated tokens from the JwtService class,
     * checks if each token is expired using the isTokenExpired() method,
     * and removes the expired tokens from the list.
     */
    @Scheduled(fixedRate = 1000 * 60 * 60 * 5)
    public void deleteExpiredTokensFromBlackList() {
        Map<String, Boolean> map = JwtService.invalidatedTokens;
        Map<String, Boolean> newMap = new HashMap<>();
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