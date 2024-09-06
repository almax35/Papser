package com.example.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableStringDto {
    private String name;
    private double buffPrice;
    private double steamPrice;
    private String steamHref;
    private String imageHref;

}
