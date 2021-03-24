package com.example.accsSkl.Model;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.example.accsSkl.Model.Model;

public interface ModelRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Client
    RowMapper<Model> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Model(resultSet.getLong("id"), resultSet.getString("name"));
    };
 
    List<Model> findAll();
 
    Model findOne(String id);
 
    Model save(Model contragent);
 
    int delete(String id);
}
