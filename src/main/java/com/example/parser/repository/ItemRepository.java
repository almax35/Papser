package com.example.parser.repository;

import com.example.parser.entity.TableString;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<TableString,Integer> {
}
