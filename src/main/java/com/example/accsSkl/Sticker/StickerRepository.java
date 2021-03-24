package com.example.accsSkl.Sticker;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.example.accsSkl.Config;


public interface StickerRepository {
	//Config cfg = new Config();
    RowMapper<Sticker> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Sticker(
        		Config.getLongDateString(resultSet.getDate("dateOfTrace").getTime()),
        		resultSet.getInt("quantity"), 
        		resultSet.getString ("model"), 
        		resultSet.getString ("sizing_row"), 
        		resultSet.getString ("client"), 
        		resultSet.getLong("numberOfOrder"), 
        		resultSet.getString ("sizing"),       		 
        		resultSet.getLong ("idDoc"), 
        		resultSet.getLong ("idSticker"), 
        		resultSet.getLong ("idQRCode"));
    };
    
    RowMapper<Sticker> ROW_MAPPER_REST = (ResultSet resultSet, int rowNum) -> {
        return new Sticker(
        		"",
        		resultSet.getInt("quantity"), 
        		"", 
        		"", 
        		"", 
        		(long)0, 
        		"",       		 
        		resultSet.getLong ("idDoc"), 
        		resultSet.getLong ("idSticker"), 
        		resultSet.getLong ("idQRCode"));
    };
    /*private Long dateOfTrace; //Время
	private int quantity;
    private String model ; //№Модели
    private String sizing_row ; //Участок от
    private String client ; //Участок от
    private Long numberOfOrder; //№Партии
    private String sizing; //Разм/ряд
    private Long idDoc ; //OrderTrace
    private Long idSticker;*/

	List<Sticker> getSticker();

	List<Sticker> findByNumberOfOrderAndDateAfter(Long idDoc, Long idSticker, Long date);

	List<Sticker> findStickerGreaterThan(Long idStickerMax);
}
