package com.example.accsSkl.OrderTrace;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface OrderTraceRepository {
    // Маппер, превращающий строку из таблицы БД в объект класса Client
    RowMapper<OrderTrace> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new OrderTrace(resultSet.getLong("id"), 
        		resultSet.getLong("numberOfOrder"), 
        		resultSet.getLong("dateOfTrace"), 
        		resultSet.getString("nameOfTrace"), 
        		resultSet.getLong ("sender_id") , 
        		resultSet.getString("sender_name"), 
        		resultSet.getInt ("sender_sequence") , 
        		resultSet.getLong("receiver_id") , 
        		resultSet.getString("receiver_name"), 
        		resultSet.getInt ("receiver_sequence") ,
        		resultSet.getLong("client_id"), 
        		resultSet.getString("client_name"), 
        		resultSet.getLong("brand_id"), 
        		resultSet.getString("brand_name"), 
        		resultSet.getLong("season_id"),
        		resultSet.getString("season_name") );
    };
    
    RowMapper<OrderTrace> ROW_MAPPER_DOC = (ResultSet resultSet, int rowNum) -> {
        return new OrderTrace(resultSet.getLong("id"), 
        		resultSet.getLong("numberOfOrder"), 
        		resultSet.getLong("dateOfTrace"), 
        		resultSet.getString("nameOfTrace"), 
        		resultSet.getLong ("sender_id") , 
        		resultSet.getLong("receiver_id") , 
        		resultSet.getLong("client_id"), 
        		resultSet.getLong("brand_id"), 
        		resultSet.getLong("season_id") );
    };
    
    RowMapper<OrderTrace> ROW_MAPPER_ID = (ResultSet resultSet, int rowNum) -> {
        return new OrderTrace(resultSet.getLong("id") );
    };
    
 
    List<OrderTrace> findAll();
    
    List<OrderTrace> findAllGreaterThan(Long date) ;
 
    OrderTrace findOne(String id);
 
    Long insert(OrderTrace orderTrace); //Возвращает кол-во записей
    
    int update(OrderTrace orderTrace); //Возвращает кол-во записей
 
    int delete(Long id);

	OrderTrace findOne(Long id);

	OrderTrace findOne(Long numberOfOrder, String dateOfTrace, Long sender_id, Long receiver_id, Long client_id,
			Long brand_id, Long season_id);

}
