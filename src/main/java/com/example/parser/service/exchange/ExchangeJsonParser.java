package com.example.parser.service.exchange;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ExchangeJsonParser {
    public double getCnyToRub(String body) {
        JSONObject object = new JSONObject(body);
        return object.getJSONObject("Valute").getJSONObject("CNY").getDouble("Value");
    }
}
