package com.example.tourism_management_system.model.enums.enumForTour;

public enum PlacesForCampaign {
    DIMAC (220,12,10000),
    ARAGATS (130,13,12000),
    AGHMAGHAN(275,11,8000),
    AZHDAHAK(100, 10, 7000),
    SMBATABERD(310, 13, 13000);

    private  final int duration;
    private  final int distance;
    private  final int cost;

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