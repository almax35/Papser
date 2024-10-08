package com.example.parser.service;

import com.example.parser.entity.BuffItem;
import com.example.parser.entity.Resale;
import com.example.parser.entity.TableString;
import com.example.parser.entity.ValuteCourse;
import com.example.parser.service.buff.BuffService;
import com.example.parser.utils.TableStringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
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
            case "Buff" -> strings.sort(Comparator.comparing(TableString::getBuffPrice));
            case "Steam" -> strings.sort(Comparator.comparing(TableString::getSteamPrice));
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
    public void searchItems(String name,double minPrice, double maxPrice, int quantity,String type, Model model) throws IOException, InterruptedException {
        ArrayList<TableString> tableStrings;
        if (Objects.equals(name, "")) {
            tableStrings = (ArrayList<TableString>) this.searchWithParams(minPrice, maxPrice, quantity, type);
            model.addAttribute("maxPercentage", this.findMaxPercentageAtAll());
            if (tableStrings == null) {
                model.addAttribute("message", "Не удалось найти предметы по заданным параметрам поиска");
            }
        } else {
            tableStrings = (ArrayList<TableString>) this.searchWithName(name);
            model.addAttribute("maxPercentage", this.findMaxPercentageAtAll());
            if (tableStrings == null) {
                model.addAttribute("message", "Не удалось найти предмет с заданным названием");
            }
        }
        model.addAttribute("results", tableStrings);
    }

    public String searchItemsAndReturnStringOfItems(String name,double minPrice, double maxPrice, int quantity,String type) throws IOException, InterruptedException {
        ArrayList<TableString> tableStrings;
        if (Objects.equals(name, "")) {
            tableStrings = (ArrayList<TableString>) this.searchWithParams(minPrice, maxPrice, quantity, type);
        } else {
            tableStrings = (ArrayList<TableString>) this.searchWithName(name);
        }
        return new TableStringMapper().convertToEntity(tableStrings);
    }

    public List<TableString> getStrings() {
        return strings;
    }

    public void setStrings(List<TableString> strings) {
        this.strings = strings;
    }
}
