package com.example.tourism_management_system.controller;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.service.impl.TourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tours")
public class TourController {


    //Validation validation = new Validation();

    private final TourServiceImpl tourServiceImpl;

    @Autowired
    public TourController(TourServiceImpl tourServiceImpl) {
        this.tourServiceImpl = tourServiceImpl;
    }

    @PostMapping("/create")
    public void save(@RequestBody TourEntity tourEntity) {
//        String tourName = null;
//        if (validation.tourName(tourEntity.getTourType()).contains(tourEntity.getTourName())) {
//            tourName = tourEntity.getTourName();
//        } else return "You don't have a similar tour․";
//        if (validation.validateDate(tourEntity.getTourDate()) == null){
//           return "You can't create a tour earlier than 3 days.";
//        }
//        TourModel tourModel = new TourModel(validation.tourTypeValidate(tourEntity.getTourType()),
//                tourEntity.getTourName(), validation.validateDate(tourEntity.getTourDate()),
//                tourEntity.getStartTime(), tourEntity.getDuration(), tourEntity.getDistance(),
//                validation.carType(tourEntity.getCarType()),
//                validation.validateQuantity(tourEntity.getCarType()), tourEntity.getCost());
//        return tourServiceImpl.save(tourModel);
    }

    @GetMapping("/getAll")
    public List<TourEntity> getAll() {
        return tourServiceImpl.getAll();
    }

    @GetMapping("/get/{tourId}")
    public Optional<TourEntity> getById(@PathVariable("tourId") Long id) {
        return tourServiceImpl.getById(id);
    }

    @DeleteMapping("/delete/{tourId}")
    public String deleteById(@PathVariable("tourId") Long id) {
        return tourServiceImpl.deleteById(id);
    }

    @GetMapping("/get/sort/cost")
    public List<TourEntity> sortByCost() {
        return tourServiceImpl.sortByCost();
    }

    @GetMapping("/get/sort/date")
    public List<TourEntity> sortByDate() {
        return tourServiceImpl.sortByDate();
    }

    @GetMapping("/get/sort/quantity")
    public List<TourEntity> sortByQuantity() {
        return tourServiceImpl.sortByQuantity();
    }

}
