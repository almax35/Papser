package com.example.parser.service;

import com.example.parser.entity.ItemInterface;

import java.io.IOException;

public interface IService {
    ItemInterface searchByName(String name) throws IOException, InterruptedException;
}
