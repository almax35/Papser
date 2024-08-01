package com.example.parser.entity;

import org.springframework.stereotype.Component;

@Component
public class TableString {
    private String name;
    private double buffPrice;
    private double steamPrice;
    private String steamHref;
    private String imageHref;

    public TableString(String name, double buffPrice, double steamPrice, String imageHref) {
        this.name = name;
        this.buffPrice = buffPrice;
        this.steamPrice = steamPrice;
        this.imageHref = imageHref;
    }

    public TableString(String name, double buffPrice, double steamPrice, String steamHref, String imageHref) {
        this.name = name;
        this.buffPrice = buffPrice;
        this.steamPrice = steamPrice;
        this.steamHref = steamHref;
        this.imageHref = imageHref;
    }

    public TableString() {
    }

    public String getName() {
        return name;
    }

    public double getBuffPrice() {
        return buffPrice;
    }

    public double getSteamPrice() {
        return steamPrice;
    }

    public String getSteamHref() {
        return steamHref;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuffPrice(double buffPrice) {
        this.buffPrice = buffPrice;
    }

    public void setSteamPrice(double steamPrice) {
        this.steamPrice = steamPrice;
    }


    public void setSteamHref(String steamHref) {
        this.steamHref = steamHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }


    @Override
    public String toString() {
        return "TableString{" +
                "name='" + name + '\'' +
                ", buffPrice=" + buffPrice +
                ", steamPrice=" + steamPrice +
                ", steamHref='" + steamHref + '\'' +
                ", imageHref='" + imageHref + '\'' +
                '}';
    }

    public Resale findMarketsWithMaxPercent() {
        return new Resale(getName(), (findMax() / findMin() - 1) * 100, findMarketWithPrice(findMin()), findMarketWithPrice(findMax()));
    }

    private double findMin() {
        double min = Double.MAX_VALUE;
        if (min > this.buffPrice && this.buffPrice != 0) {
            min = this.buffPrice;
        }
        if (min > this.steamPrice && this.steamPrice != 0) {
            min = this.steamPrice;
        }
        return min;
    }

    private double findMax() {
        double max = Double.MIN_VALUE;
        max = Math.max(max, this.buffPrice);
        max = Math.max(max, this.steamPrice);
        return max;
    }

    private String findMarketWithPrice(double price) {
        if (this.buffPrice == price) {
            return "Buff";
        }
        if (this.steamPrice == price) {
            return "Steam";
        }
        return null;
    }
}
