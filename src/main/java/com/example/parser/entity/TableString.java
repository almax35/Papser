package com.example.parser.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity (name = "item")
@Component
public class TableString {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private double buffPrice;
    @NotNull
    private double steamPrice;
    @NotNull
    private String steamHref;
    @NotNull
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
