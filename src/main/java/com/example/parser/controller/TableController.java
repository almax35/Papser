package com.example.parser.controller;

import com.example.parser.entity.ItemCategory;
import com.example.parser.entity.Resale;
import com.example.parser.entity.TableString;
import com.example.parser.service.MainService;
import com.example.parser.service.buff.BuffJsonParser;
import com.example.parser.service.buff.BuffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
public class TableController {

    @GetMapping("/table")
    public String initTable(Model model) {
        model.addAttribute("results", new ArrayList<TableString>());
        model.addAttribute("maxPercentage", new Resale());
        return "table";
    }

    @PostMapping("/table")
    public String showTable(@RequestParam(required = false) String name, @RequestParam(required = false) double minPrice, @RequestParam(required = false) double maxPrice, @RequestParam(required = false) int quantity, @RequestParam(required = false) String type, Model model) throws IOException, InterruptedException {
        MainService mainService=new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        mainService.searchItems(name,minPrice,maxPrice,quantity,type,model);
        return "/table";
    }

    @PostMapping("/sort")
    public String sortTable(@RequestParam String typeSort, Model model) throws IOException, InterruptedException {
        MainService mainService=new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        mainService.sortTable(typeSort);
        model.addAttribute("results", mainService.getStrings());
        return "/table";
    }
}
