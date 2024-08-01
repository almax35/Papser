package com.example.parser.service.exchange;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExchangeJsonParser {
    @Test
    void whenExchangeJsonParserGetRightResponseThenReturnUsdAndCny(){
        String fileName = "C:\\Users\\Spectra\\IdeaProjects\\TableParser\\src\\test\\resources\\cbrRightResponse.json";
        StringBuilder line = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                line.append(currentLine);
            }
            ExchangeJsonParser exchangeJsonParser=new ExchangeJsonParser();;
            double cny=exchangeJsonParser.getCnyToRub(line.toString());
            assertAll(
                    () -> assertEquals(cny,12.8816));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
