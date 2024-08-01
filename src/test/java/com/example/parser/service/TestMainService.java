package com.example.parser.service;

import com.example.parser.entity.ItemCategory;
import com.example.parser.entity.Resale;
import com.example.parser.entity.TableString;
import com.example.parser.service.buff.BuffJsonParser;
import com.example.parser.service.buff.BuffService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TestMainService {
    @Test
    void whenMainServiceSearchByNameThenReturnTableString() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        String name = "AK-47 | Elite Build (Field-Tested)";
        List<TableString> strings = service.searchWithName(name);
        assertAll(() -> assertEquals(strings.get(0).getName(), name),
                () -> assertTrue(strings.get(0).getSteamPrice() > 0),
                () -> assertTrue(strings.get(0).getBuffPrice() > 0),
                () -> assertNotNull(strings.get(0).getSteamHref()),
                () -> assertNotNull(strings.get(0).getImageHref()));
    }

    @Test
    void whenMainServiceSearchByBadNameThenReturnNull() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        String name = "Bad name";
        assertNull(service.searchWithName(name));
    }

    @Test
    void whenMainServiceSearchWithRightParamsThenReturnList() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        double minPrice = 100;
        double maxPrice = 1000;
        int size = 10;
        String type = "weapon_ak47";
        List<TableString> items = service.searchWithParams(minPrice, maxPrice, size, type);
        assertEquals(items.size(), size);
        for (TableString item : items) {
            assertAll(() -> assertTrue(item.getBuffPrice() >= 100 && item.getBuffPrice() <= 1000),
                    () -> assertTrue(item.getSteamPrice() >= 100),
                    () -> assertNotNull(item.getSteamHref()),
                    () -> assertNotNull(item.getImageHref()));
        }
    }

    @Test
    void whenMainServiceSearchWithBadParamsThenReturnNull() throws IOException, InterruptedException {
        double minPrice = 1000;
        double maxPrice = 100;
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        List<TableString> items = service.searchWithParams(minPrice, maxPrice, 3, "bad type");
        assertNull(items);
    }

    @Test
    void whenMainServiceSortTableThenReturnSortedTable() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        double minPrice = 100;
        double maxPrice = 1000;
        int size = 10;
        String type = "weapon_ak47";
        service.searchWithParams(minPrice, maxPrice, size, type);
        service.sortTable("Buff");
        List<Double> buffPriceList = service.getStrings().stream()
                .map(TableString::getBuffPrice)
                .collect(Collectors.toList());
        assertTrue(isListSorted(buffPriceList));
        service.sortTable("Steam");
        List<Double> steamList = service.getStrings().stream()
                .map(TableString::getSteamPrice)
                .collect(Collectors.toList());
        assertTrue(isListSorted(steamList));
    }

    @Test
    void whenMainServiceCalledFindMaxPercentageAtAllThenReturnBestResale() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        TableString tableString1 = new TableString("name1", 1, 2, "image1", "href1");
        TableString tableString2 = new TableString("name2", 10, 30, "image2", "href2");
        List<TableString> tableStrings = new ArrayList<>();
        tableStrings.add(tableString1);
        tableStrings.add(tableString2);
        service.setStrings(tableStrings);
        Resale resale = service.findMaxPercentageAtAll();
        assertAll(() -> assertEquals(resale.getName(), "name2"),
                () -> assertEquals(resale.getPercent(), 200),
                () -> assertEquals(resale.getFirstMarket(), "Buff"),
                () -> assertEquals(resale.getSecondMarket(), "Steam"));
    }

    private boolean isListSorted(List<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
