package com.example.accsSkl.Brand;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface BrandRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Client
    RowMapper<Brand> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Brand(resultSet.getLong("id"), resultSet.getString("name"));
    };
 
    List<Brand> findAll();
 
    Brand findOne(String id);
 
    Brand save(Brand contragent);
 
    int delete(String id);
}
