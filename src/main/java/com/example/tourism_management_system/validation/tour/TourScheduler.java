package com.example.tourism_management_system.validation.tour;


import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class TourScheduler {
    private final TourService tourService;

    @Autowired
    public TourScheduler(TourService tourService) {
        this.tourService = tourService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void deletePastDateTours() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        List<TourEntity> tours = tourService.getAll();

        for (TourEntity tour : tours) {
            if (tour.getTourDate().isBefore(currentDate) || tour.getTourDate().equals(currentDate) || tour.getStartTime().isBefore(currentTime)) {
                tourService.deleteById(tour.getId());
            }
        }
    }
}