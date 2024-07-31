package com.example.parser.entity;

public class Resale {
    private String name;
    private double percent;
    private String firstMarket;
    private String secondMarket;


    public Resale(String name, double percent, String firstMarket, String secondMarket) {
        this.name = name;
        this.percent = Math.round(percent * 100.0) / 100.0;
        this.firstMarket = firstMarket;
        this.secondMarket = secondMarket;
    }

    public Resale() {
    }

    public double getPercent() {
        return percent;
    }

    public String getName() {
        return name;
    }

    public String getFirstMarket() {
        return firstMarket;
    }

    public String getSecondMarket() {
        return secondMarket;
    }
}
