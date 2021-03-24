package com.example.accsSkl.Model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public final class ModelRepositoryImpl implements ModelRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelRepository.class);
    
    private final JdbcTemplate jdbcTemplate;
 
    @Autowired
    public ModelRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public List<Model> findAll() {
		return jdbcTemplate.query("select кодМодели as id, [№Модели] as name from МОДЕЛИ", ROW_MAPPER);
	}

	@Override
	public Model findOne(String id) {
		Model model = null;
		try {
			Integer.parseInt(id);
	        try {
	        	model = jdbcTemplate.queryForObject("select кодМодели as id, [№Модели] as name from МОДЕЛИ where кодМодели = ?", new Object[]{id}, ROW_MAPPER);
	            } catch (DataAccessException dataAccessException) {
	                LOGGER.debug("Не найдена запись в таблице МОДЕЛИ с кодМодели {}", id);
	            }
	    } catch (NumberFormatException ex)
	    {
	        try {
	        	model = jdbcTemplate.queryForObject("select кодМодели as id, [№Модели] as name from МОДЕЛИ where [№Модели] = ?", new Object[]{id}, ROW_MAPPER);
	            } catch (DataAccessException dataAccessException) {
	                LOGGER.debug("Не найдена запись в таблице МОДЕЛИ с №Модели {}", id);
	            }
	    }     
    return model;
	}

	@Override
	public Model save(Model contragent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
