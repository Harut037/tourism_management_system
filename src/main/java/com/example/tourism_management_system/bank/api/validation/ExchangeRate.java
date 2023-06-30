package com.example.tourism_management_system.bank.api.validation;

public class ExchangeRate {

    private final double USD;
    private final double RUR;
    private final double AMD;
    private final double EUR;

    public ExchangeRate() {
        this.AMD = 1;
        this.USD = 385.05;
        this.RUR = 4.41;
        this.EUR = 421.91;
    }

    public double getUSD() {
        return USD;
    }

    public double getRUR() {
        return RUR;
    }

    public double getAMD() {
        return AMD;
    }

    public double getEUR() {
        return EUR;
    }
}