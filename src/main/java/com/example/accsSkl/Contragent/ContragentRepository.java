package com.example.accsSkl.Contragent;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface ContragentRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Client
    RowMapper<Contragent> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Contragent(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("sequence"));
    };
 
    List<Contragent> findAll();
 
    Contragent findOne(String id);
 
    Contragent save(Contragent contragent);
 
    int delete(String id);
}
