package com.example.accsSkl.Sizing;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.example.accsSkl.Sizing.Sizing;

public interface SizingRepository {
	// Маппер, превращающий строку из таблицы БД в объект класса Client
    RowMapper<Sizing> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Sizing(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("quantity"));
    };
 
    List<Sizing> findAll();
 
    Sizing findOne(String id);
 
    Sizing save(Sizing contragent);
 
    int delete(String id);
}
