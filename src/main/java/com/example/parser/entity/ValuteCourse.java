package com.example.parser.entity;

import com.example.parser.service.exchange.ExchangeJsonParser;
import com.example.parser.service.exchange.ExchangeService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ValuteCourse {
    private final double uah;

    public ValuteCourse() throws IOException, InterruptedException {
        ExchangeService service = new ExchangeService(new ExchangeJsonParser());
        this.uah = service.saveValuteCourse().get(0);

    }

    public double getUah() {
        return uah;
    }
}
