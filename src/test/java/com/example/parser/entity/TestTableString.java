package com.example.parser.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTableString {
    @Test
    void whenFindMarketsWithMaxPercentCalledThenReturnResale() {
        TableString tableString = new TableString("name", 1, 2, "image", "href");
        Resale resale = tableString.findMarketsWithMaxPercent();
        assertAll(() -> assertEquals(resale.getFirstMarket(), "Buff"),
                () -> assertEquals(resale.getSecondMarket(), "Steam"),
                () -> assertEquals(resale.getName(), "name"),
                () -> assertEquals(resale.getPercent(), 100));
    }
}
