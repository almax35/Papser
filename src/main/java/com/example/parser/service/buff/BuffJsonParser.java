package com.example.parser.service.buff;

import com.example.parser.entity.BuffItem;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuffJsonParser {
    public BuffItem parseJsonBuff(JSONObject object) {
        String name = object.getString("name");
        double buffPrice = object.getDouble("sell_min_price");
        JSONObject steamInfo = object.getJSONObject("goods_info");
        double steamPrice = steamInfo.getDouble("steam_price_cny");
        String steamHref = object.getString("steam_market_url");
        String imageHref = steamInfo.getString("original_icon_url");
        return new BuffItem(name, buffPrice, steamPrice, steamHref, imageHref);
    }

    public List<BuffItem> parseResponseToList(String body) {
        ArrayList<BuffItem> list = new ArrayList<>();
        JSONObject object = new JSONObject(body);
        if (object.keySet().contains("error")) {
            return list;
        }
        JSONObject data = object.getJSONObject("data");
        JSONArray items = data.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            list.add(parseJsonBuff(item));
        }
        return list;
    }
}
