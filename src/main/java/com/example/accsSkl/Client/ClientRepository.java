package com.example.accsSkl.Client;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface ClientRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Client
    RowMapper<Client> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Client(resultSet.getLong("id"), resultSet.getString("name"));
    };
 
    List<Client> findAll();
 
    Client findOne(String id);
 
    Client save(Client client);
 
    int delete(String id);
}

