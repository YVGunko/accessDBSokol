package com.example.accsSkl.OrderTraceDetail;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

public interface OrderTraceDetailRepository {
    RowMapper<OrderTraceDetail> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new OrderTraceDetail(resultSet.getLong("id"), 
        		resultSet.getLong ("parent_id") ,
        		resultSet.getLong("numberOfOrder"),  
        		resultSet.getLong("model_id"), 
        		resultSet.getString("model_name"), 
        		resultSet.getLong("sizing_id"), 
        		resultSet.getString("sizing_name"), 
        		resultSet.getInt("quantity") );
    };
    
    RowMapper<OrderTraceDetail> ROW_MAPPER_ONE = (ResultSet resultSet, int rowNum) -> {
        return new OrderTraceDetail(resultSet.getLong("id"), 
        		resultSet.getLong ("parent_id") ,
        		resultSet.getLong("numberOfOrder"),    
        		resultSet.getLong("model_id"), 
        		resultSet.getLong("sizing_id"),  
        		resultSet.getInt("quantity") );
    };
    
    RowMapper<OrderTraceDetail> ROW_MAPPER_DOC = (ResultSet resultSet, int rowNum) -> {
        return new OrderTraceDetail(resultSet.getLong("id"), 
        		resultSet.getLong ("parent_id"), resultSet.getLong("numberOfOrder"), resultSet.getDate("dateOfTrace").getTime(), resultSet.getString ("nameOfTrace"),        		 
        				resultSet.getLong ("sender_id"), 
        						resultSet.getLong ("receiver_id"),
        								resultSet.getLong ("client_id"), 
        										resultSet.getLong ("brand_id"),  
        												resultSet.getLong ("season_id"),      		
        													resultSet.getLong("model_id"), 
        														resultSet.getLong("sizing_id"),  
        															resultSet.getInt("quantity") );
    };
    List<OrderTraceDetail> findAll();
    
    List<OrderTraceDetail> findAllRowsOfDoc(long id) ;
 
    OrderTraceDetail findOne(String id);
 
    int insert(OrderTraceDetail orderTrace); //Возвращает кол-во записей
    
    int update(OrderTraceDetail orderTrace); //Возвращает кол-во записей
 
    int delete(Long id);

	List<OrderTraceDetail> findAllRowsAndDoc(long date);

	List<OrderTraceDetail> getBoxSticker(long date);
}
