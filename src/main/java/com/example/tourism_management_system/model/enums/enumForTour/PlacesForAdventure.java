package com.example.tourism_management_system.model.enums.enumForTour;

public enum PlacesForAdventure {
    LASTIVER (280,9,17000),
    HORSEBACK_AGHMAGHAN(275,14,27000),
    RAFTING (370,10,25000),
    ZIPLINES(310, 12, 28000);

    private int duration;
    private int distance;
    private int cost;

    public int getDuration() {
        return duration;
    }

    public int getDistance() {
        return distance;
    }

    public int getCost() {
        return cost;
    }

    PlacesForAdventure(int distance, int duration, int cost) {
        if (distance >= 7 && duration > 0 && cost >= 0) {
            this.distance = distance;
            this.duration = duration;
            this.cost = cost;
        } else throw new IllegalArgumentException();
    }
}