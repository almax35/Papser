package com.example.parser.entity;

import lombok.Data;

@Data
public class BuffItem implements ItemInterface {
    private final String name;
    private final double buffPrice;
    private final double steamPrice;
    private final String steamHref;
    private final String imageHref;
    
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", buffPrice=" + buffPrice +
                ", steamPrice=" + steamPrice +
                ", steamHref='" + steamHref + '\'' +
                ", imageHref='" + imageHref + '\'' +
                '}';
    }

    public BuffItem(String name, double buffPrice, double steamPrice, String steamHref, String imageHref) {
        this.name = name;
        this.buffPrice = buffPrice;
        this.steamPrice = steamPrice;
        this.steamHref = steamHref;
        this.imageHref = imageHref;
    }
}