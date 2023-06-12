package com.example.tourism_management_system.model.enums.enumForTour;

public enum PlacesForCampaign {
    DIMAC (220,12,10000),
    HATIS (100,8,7000),
    AGHMAGHAN(275,11,8000);

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

    PlacesForCampaign(int distance, int duration, int cost) {
        if (distance >= 7 && duration > 0 && cost >= 0) {
            this.distance = distance;
            this.duration = duration;
            this.cost = cost;
        } else throw new IllegalArgumentException();
    }
}