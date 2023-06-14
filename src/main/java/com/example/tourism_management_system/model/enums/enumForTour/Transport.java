package com.example.tourism_management_system.model.enums.enumForTour;

public enum Transport {
    MINIVAN(5, 8),
    MINIBUS(9, 18),
    BUS(19, 50);

    private final int quantityMin;
    private final int quantityMax;

    public int getQuantityMin() {
        return quantityMin;
    }

    public int getQuantityMax() {
        return quantityMax;
    }

    Transport(int quantityMin, int quantityMax) {
        this.quantityMin = quantityMin;
        this.quantityMax = quantityMax;
    }
}