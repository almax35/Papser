package com.example.parser.controller;

import com.example.parser.entity.Resale;
import com.example.parser.entity.TableString;
import com.example.parser.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class MainController {
    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/table")
    public String initTable(Model model) {
        model.addAttribute("results", new ArrayList<TableString>());
        model.addAttribute("maxPercentage", new Resale());
        return "table";
    }

    @PostMapping("/table")
    public String showTable(@RequestParam(required = false) String name, @RequestParam(required = false) double minPrice, @RequestParam(required = false) double maxPrice, @RequestParam(required = false) int quantity, @RequestParam(required = false) String type, Model model) throws IOException, InterruptedException {
        ArrayList<TableString> tableStrings;
        if (Objects.equals(name, "")) {
            tableStrings = (ArrayList<TableString>) mainService.searchWithParams(minPrice, maxPrice, quantity, type);
            model.addAttribute("maxPercentage", mainService.findMaxPercentageAtAll());
            if (tableStrings == null) {
                model.addAttribute("message", "Не удалось найти предметы по заданным параметрам поиска");
            }
        } else {
            tableStrings = (ArrayList<TableString>) mainService.searchWithName(name);
            model.addAttribute("maxPercentage", mainService.findMaxPercentageAtAll());
            if (tableStrings == null) {
                model.addAttribute("message", "Не удалось найти предмет с заданным названием");
            }
        }
        model.addAttribute("results", tableStrings);
        return "/table";
    }

    @PostMapping("/sort")
    public String sortTable(@RequestParam String typeSort, Model model) {
        mainService.sortTable(typeSort);
        model.addAttribute("results", mainService.getStrings());
        return "/table";
    }
}
