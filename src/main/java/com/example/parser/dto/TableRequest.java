package com.example.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableRequest {
    private String name;
    private double minPrice;
    private double maxPrice;
    private int quantity;
    private String type;

}
