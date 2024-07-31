package com.example.parser.entity;

import com.example.parser.service.exchange.ExchangeJsonParser;
import com.example.parser.service.exchange.ExchangeService;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class ValuteCourse {
    private final double usd;
    private final double uah;

    public ValuteCourse() throws IOException, InterruptedException {
        ExchangeService service = new ExchangeService(new ExchangeJsonParser());
        this.usd = service.saveValuteCourse().get(0);
        this.uah = service.saveValuteCourse().get(1);
    }

    public double getUsd() {
        return usd;
    }

    public double getUah() {
        return uah;
    }
}
