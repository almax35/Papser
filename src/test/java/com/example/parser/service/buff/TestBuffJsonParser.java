package com.example.parser.service.buff;

import com.example.parser.entity.BuffItem;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBuffJsonParser {
    @Test
    void whenBuffJsonParserGetRightResponseThenReturnBuffItem() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Spectra\\IdeaProjects\\TableParser\\src\\test\\resources\\buffJsonRightResponseWithOneItem.json"))) {
            BuffJsonParser buffJsonParser = new BuffJsonParser();
            JSONObject object = new JSONObject(reader.readLine());
            BuffItem item = buffJsonParser.parseJsonBuff(object);
            BuffItem realItem = new BuffItem("AK-47 | Uncharted (Field-Tested)", 1.57, 2.17, "https://steamcommunity.com/market/listings/730/AK-47%20%7C%20Uncharted%20%28Field-Tested%29", "https://market.fp.ps.netease.com/file/65f586dcafccad0b48a92b0aQwewLboP05");
            assertAll(
                    () -> assertEquals(item.getName(), realItem.getName()),
                    () -> assertEquals(item.getBuffPrice(), realItem.getBuffPrice()),
                    () -> assertEquals(item.getSteamPrice(), realItem.getSteamPrice()),
                    () -> assertEquals(item.getSteamHref(), realItem.getSteamHref()),
                    () -> assertEquals(item.getImageHref(), realItem.getImageHref())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void whenBuffJsonParserGetRightResponseThenReturnListItems() {
        String fileName = "C:\\Users\\Spectra\\IdeaProjects\\TableParser\\src\\test\\resources\\buffJsonRightResponseWithManyItems.json";
        StringBuilder line = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                line.append(currentLine);
            }
            BuffJsonParser buffJsonParser = new BuffJsonParser();
            List<BuffItem> item = buffJsonParser.parseResponseToList(line.toString());
            assertEquals(item.size(), 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
