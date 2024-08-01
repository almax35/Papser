package com.example.parser.service.buff;

import com.example.parser.config.ConfProperties;
import com.example.parser.entity.BuffItem;
import com.example.parser.entity.ItemCategory;
import com.example.parser.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class BuffService implements IService {
    private final BuffJsonParser buffJsonParser;
    private final ItemCategory category;

    @Autowired
    public BuffService(BuffJsonParser buffJsonParser, ItemCategory category) {
        this.buffJsonParser = buffJsonParser;
        this.category = category;
    }

    public BuffItem searchByName(String name) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedString = URLEncoder.encode(name, StandardCharsets.UTF_8);
        HttpRequest request1 = HttpRequest
                .newBuilder()
                .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&quality=normal&search=" + encodedString))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request1, HttpResponse.BodyHandlers.ofString());
        List<BuffItem> buffItems = buffJsonParser.parseResponseToList(response.body());
        if (buffItems.isEmpty()) {
            return null;
        }
        return buffItems.get(0);
    }

    public List<BuffItem> searchWithParams(double minPrice, double maxPrice, int quantity, String type) throws IOException, InterruptedException {
        StringBuilder uri = new StringBuilder("https://buff.163.com/api/market/goods?game=csgo&page_num=1&quality=normal");
        uri.append("&page_size=").append(quantity);
        if (minPrice >= maxPrice || minPrice < 0) {
            return null;
        }
        uri.append("&min_price=").append(minPrice);
        uri.append("&max_price=").append(maxPrice);
        if (category.getCategoryWeapon().contains(type)) {
            uri.append("&category=").append(type);
        }
        if (category.getCategoryType().contains(type)) {
            uri.append("&category_group=").append(type);
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                .uri(URI.create(uri.toString()))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return buffJsonParser.parseResponseToList(response.body());
    }

}
