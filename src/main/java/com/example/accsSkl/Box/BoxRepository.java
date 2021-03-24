package com.example.accsSkl.Box;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.jdbc.core.RowMapper;


/**
 *     private int quantity;
    private String model ; //№Модели
    private Long model_id ; //№Модели
    private String sizing; //Разм/ряд
    private Long sizing_id; //Разм/ряд
    private String numberOfOrder; //№Партии
    private String dateOfTrace; //Время приемки:
    private String sender ; //Участок от
    private Long sender_id ; //Участок от
    private String receiver ; //Участок от
    private Long receiver_id ; //Участок от
    private String client ; //Клиент
    private Long client_id ; //Клиент
    private String brand ; //Бренд
    private Long brand_id ; //Бренд
    private String season; //Сезон
    private Long season_id; //Сезон
    private String boxDesc; //Сезон
    private Long parent_id ; //OrderTrace
    private Long sentToMasterDate;
        private Long id ; //
 */

public interface BoxRepository {
	
	static String snameOfTrace = "Выход цеха";
	
    RowMapper<Box> ROW_MAPPER_BOX = (ResultSet resultSet, int rowNum) -> {
        return new Box(resultSet.getInt("quantity"), 
        		"",	resultSet.getLong ("model_id"), 
        		"",	resultSet.getLong ("sizing_id"), 
        		resultSet.getLong("numberOfOrder"), 
        		resultSet.getString("dateOfTrace"),      		 
        		"",	resultSet.getLong ("sender_id"), 
        		"",	resultSet.getLong ("receiver_id"),
        		"",	resultSet.getLong ("client_id"), 
        		"",	resultSet.getLong ("brand_id"),  
        		"",	resultSet.getLong ("season_id"),      		
        		resultSet.getLong("parent_id"), 
        		new Date().getTime(),
        		resultSet.getLong("sticker"),
        		resultSet.getLong("id"));
    };
    /*public Box(int quantity, String model, Long model_id, String sizing, Long sizing_id, Long numberOfOrder,
    		Long dateOfTrace, String receiver, Long receiver_id, String client, Long client_id,
               String brand, Long brand_id, String season, Long season_id, String sender, Long sender_id, Long parent_id, Long sentToMasterDate)*/
 
}
