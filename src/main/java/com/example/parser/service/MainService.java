package com.example.parser.service;

import com.example.parser.entity.BuffItem;
import com.example.parser.entity.Resale;
import com.example.parser.entity.TableString;
import com.example.parser.entity.ValuteCourse;
import com.example.parser.service.buff.BuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class MainService {
    private final BuffService buffService;
    private final ValuteCourse valuteCourse;
    private List<TableString> strings;


    @Autowired
    public MainService(BuffService buffService) throws IOException, InterruptedException {
        this.buffService = buffService;
        valuteCourse = new ValuteCourse();
    }

    public List<TableString> searchWithName(String name) throws IOException, InterruptedException {
        List<TableString> tableStrings = new ArrayList<>();
        TableString tableString = new TableString();
        BuffItem buffItem = buffService.searchByName(name);
        if (buffItem == null) {
            return null;
        }
        String realName = buffItem.getName();
        double roundBuffPrice = Math.round(buffItem.getBuffPrice() * valuteCourse.getUah() * 100.0) / 100.0;
        double roundSteamPrice = Math.round(buffItem.getSteamPrice() * valuteCourse.getUah() * 100.0) / 100.0;
        tableString.setName(buffItem.getName());
        tableString.setBuffPrice(roundBuffPrice);
        tableString.setSteamPrice(roundSteamPrice);
        tableString.setSteamHref(buffItem.getSteamHref());
        tableString.setImageHref(buffItem.getImageHref());
        tableStrings.add(tableString);
        strings = tableStrings;
        return tableStrings;
    }

    public List<TableString> searchWithParams(double minPrice, double maxPrice, int quantity, String type) throws IOException, InterruptedException {
        double minRubPrice = Math.round(minPrice / valuteCourse.getUah() * 100.0) / 100.0;
        double maxRubPrice = Math.round(maxPrice / valuteCourse.getUah() * 100.0) / 100.0;
        List<BuffItem> buffItems = buffService.searchWithParams(minRubPrice, maxRubPrice, quantity, type);
        if (buffItems == null) {
            return null;
        }
        List<TableString> tableStrings = new ArrayList<>();
        for (BuffItem buffItem : buffItems) {
            String name = buffItem.getName();
            double roundBuffPrice = Math.round(buffItem.getBuffPrice() * valuteCourse.getUah() * 100.0) / 100.0;
            double roundSteamPrice = Math.round(buffItem.getSteamPrice() * valuteCourse.getUah() * 100.0) / 100.0;
            TableString tableString = new TableString(buffItem.getName(), roundBuffPrice, roundSteamPrice, buffItem.getSteamHref(), buffItem.getImageHref());
            tableStrings.add(tableString);
        }
        strings = tableStrings;
        System.out.println(tableStrings);
        return tableStrings;
    }

    public void sortTable(String type) {
        switch (type) {
            case "Buff":
                strings.sort(Comparator.comparing(TableString::getBuffPrice));
                break;
            case "Steam":
                strings.sort(Comparator.comparing(TableString::getSteamPrice));
                break;
        }
    }

    public Resale findMaxPercentageAtAll() {
        double maxPercentage = Double.MIN_VALUE;
        Resale resale = new Resale();
        if (strings == null) {
            return null;
        }
        for (TableString string : strings) {
            if (string.findMarketsWithMaxPercent().getPercent() > maxPercentage) {
                maxPercentage = string.findMarketsWithMaxPercent().getPercent();
                resale = string.findMarketsWithMaxPercent();
            }
        }
        return resale;
    }

    public List<TableString> getStrings() {
        return strings;
    }

    public void setStrings(List<TableString> strings) {
        this.strings = strings;
    }
}
