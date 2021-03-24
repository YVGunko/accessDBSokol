package com.example.accsSkl.Season;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface SeasonRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Client
    RowMapper<Season> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Season(resultSet.getLong("id"), resultSet.getString("name"));
    };
 
    List<Season> findAll();
 
    Season findOne(String id);
 
    Season save(Season contragent);
 
    int delete(String id);
}
