package com.example.tourism_management_system.service.impl;

import com.example.tourism_management_system.model.entities.TourEntity;
import com.example.tourism_management_system.model.pojos.Tour;
import com.example.tourism_management_system.model.pojos.UserInTour;
import com.example.tourism_management_system.repository.TourRepository;
import com.example.tourism_management_system.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    /**
     * Overrides the default save behavior to save Tour objects.
     *
     * @param tour the Tour objects to be saved
     */
    @Override
    public String save(Tour tour) {
        TourEntity tourEntity = new TourEntity(tour);
        tourRepository.save(tourEntity);
        return "The tour has been successfully created";
    }

    /**
     * Overrides the default getAll behavior to retrieve all TourEntity objects.
     *
     * @return a list of all TourEntity objects
     */
    @Override
    public List<Tour> getAll() {
        List<Tour> list = new ArrayList<>();
        List<TourEntity> listEntity = tourRepository.findAll();
        for(TourEntity i: listEntity){
            list.add(new Tour(i));
        }
        return list;
    }

    @Override
    public List<TourEntity> getAllForSchedule() {
        return tourRepository.findAll();
    }

    /**
     * Overrides the default getById behavior to retrieve a TourEntity object by its id.
     *
     * @param id the ID of the tour to be retrieved
     * @return an Optional containing the TourEntity object if found, or an empty Optional if not found
     */
    @Override
    public Tour getById(Long id) {
        Optional<TourEntity> op = tourRepository.findById(id);
        if (op.isPresent()) {
            return  new Tour(op.get());
        } else return null;
    }


    /**
     * Overrides the default deleteById behavior to delete a TourEntity object by its ID.
     *
     * @param id the ID of the tour to be deleted
     * @return a string indicating the success of the deletion operation
     */
    @Override
    public String deleteById(Long id) {
        if (tourRepository.findById(id).isPresent()) {
            tourRepository.flag(id);
            return "Successfully has been deleted";
        } else return "This id does not exist.";
    }


    /**
     * Overrides the default sortByCost behavior to retrieve all TourEntity objects sorted by cost.
     *
     * @return a list of TourEntity objects sorted by cost
     */
    @Override
    public List<Tour> sortByCost() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByCost();
        List<Tour> tours = new ArrayList <>();
        for (TourEntity i: tourEntities)
            tours.add(new Tour(i));
        return tours;
    }


    /**
     * Overrides the default sortByDate behavior to retrieve all TourEntity objects sorted by tour date.
     *
     * @return a list of TourEntity objects sorted by tour date
     */
    @Override
    public List<Tour> sortByDate() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByTourDate();
        List<Tour> tours = new ArrayList <>();
        for (TourEntity i: tourEntities)
            tours.add(new Tour(i));
        return tours;
    }

    @Override
    public List<Tour> sortByDistance() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByDistance();
        List<Tour> tours = new ArrayList <>();
        for (TourEntity i: tourEntities)
            tours.add(new Tour(i));
        return tours;
    }


    /**
     * Overrides the default sortByQuantity behavior to retrieve all TourEntity objects sorted by quantity.
     *
     * @return a list of TourEntity objects sorted by quantity
     */
    @Override
    public List<Tour> sortByQuantity() {
        List<TourEntity> tourEntities = tourRepository.findAllOrderByQuantity();
        List<Tour> tours = new ArrayList <>();
        for (TourEntity i: tourEntities)
            tours.add(new Tour(i));
        return tours;
    }


    
    //TODO
    @Override
    public String update (UserInTour userInTour) {
        return null;
    }
    
    //TODO
    @Override
    public String updateTour (Tour tour) {
        return null;
    }
    
    //TODO
    @Override
    public String removeTour (Tour tour) {
        return null;
    }
}