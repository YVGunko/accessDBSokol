package com.example.accsSkl.Sizing;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public final class  SizingRepositoryImpl implements SizingRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(SizingRepository.class);
    
    private final JdbcTemplate jdbcTemplate;
 
    @Autowired
    public SizingRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
	@Override
	public List<Sizing> findAll() {
		return jdbcTemplate.query("select кодРостовки as id, РазмерныйРяд as name, Пар_в_коробе as quantity from РОСТОВКИ", ROW_MAPPER);
	}

	@Override
	public Sizing findOne(String id) {
		Sizing client = null;
		try {
			Integer.parseInt(id);
	        try {
	        	client = jdbcTemplate.queryForObject("select кодРостовки as id, РазмерныйРяд as name, Пар_в_коробе as quantity"
	        			+ " from РОСТОВКИ where кодРостовки = ?", new Object[]{id}, ROW_MAPPER);
	            } catch (DataAccessException dataAccessException) {
	                LOGGER.debug("Не найдена запись в таблице РОСТОВКИ с кодРостовки {}", id);
	            }
	    } catch (NumberFormatException ex)
	    {
	        try {
	        	client = jdbcTemplate.queryForObject("select кодРостовки as id, РазмерныйРяд as name, Пар_в_коробе as quantity"
	        			+ " from РОСТОВКИ where РазмерныйРяд = ?", new Object[]{id}, ROW_MAPPER);
	            } catch (DataAccessException dataAccessException) {
	                LOGGER.debug("Не найдена запись в таблице РОСТОВКИ с кодРостовки {}", id);
	            }
	    }     
		return client;
	}

	@Override
	public Sizing save(Sizing contragent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
