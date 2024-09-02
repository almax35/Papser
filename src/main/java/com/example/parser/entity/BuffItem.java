package com.example.parser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuffItem implements ItemInterface {
    private final String name;
    private final double buffPrice;
    private final double steamPrice;
    private final String steamHref;
    private final String imageHref;
}