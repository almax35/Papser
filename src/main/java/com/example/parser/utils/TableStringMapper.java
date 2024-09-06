package com.example.parser.utils;

import com.example.parser.dto.TableStringDto;
import com.example.parser.entity.TableString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TableStringMapper {
    public String convertToEntity (List<TableString> tableStrings) throws JsonProcessingException {
        List<TableStringDto> resultList=new ArrayList<>();
        for (TableString tableString:tableStrings){
            resultList.add(new TableStringDto(tableString.getName(),tableString.getBuffPrice(),tableString.getSteamPrice(), tableString.getSteamHref(), tableString.getImageHref()));
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(resultList);
    }
}
