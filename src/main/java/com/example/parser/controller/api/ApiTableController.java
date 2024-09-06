package com.example.parser.controller.api;

import com.example.parser.dto.TableRequest;
import com.example.parser.entity.ItemCategory;
import com.example.parser.service.MainService;
import com.example.parser.service.buff.BuffJsonParser;
import com.example.parser.service.buff.BuffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@Tag(name="tableController")
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiTableController {
    @Operation(
            summary = "items by params",
            description = "return list of items by params: name, min price, max price, quantity, type"
    )
    @PostMapping("/table")
    public ResponseEntity<String> showTable(@RequestBody TableRequest tableRequest) throws IOException, InterruptedException {
        MainService mainService=new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()));
        String result=mainService.searchItemsAndReturnStringOfItems(tableRequest.getName(), tableRequest.getMinPrice(), tableRequest.getMaxPrice(), tableRequest.getQuantity(),tableRequest.getType());
        return ResponseEntity.ok(result);
    }
}
