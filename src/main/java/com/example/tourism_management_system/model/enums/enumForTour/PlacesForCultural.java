package com.example.tourism_management_system.model.enums.enumForTour;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum PlacesForCultural {
    GEGHARD (350, 10, 9000),
    ECHMIADZIN (130, 6, 5000),
    AMBERD(300, 10, 9000),
    GYUMRI(320, 10, 8000),
    DILIJAN(350, 11,  10000),
    LORI(390, 12, 13000),
    SEVAN (250, 9, 7000),
    AKHTALA(450, 14, 16000),
    JERMUK(400, 13, 14000),
    TATEV(630, 16, 18000);


    private int duration;
    private int distance;
    private int cost;

    PlacesForCultural(int distance, int duration,  int cost) {

            if (distance >= 7 && duration > 0 && cost >= 0) {
                this.distance = distance;
                this.duration = duration;
                this.cost = cost;
            }
            else throw new IllegalArgumentException();


    }

    public int getDistance(){
        return distance;
    }

    public int getDuration(){
        return duration;
    }

    public int getCost(){
        return cost;
    }








}
