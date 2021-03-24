package com.example.accsSkl.Season;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.accsSkl.Brand.Brand;



@Component
public final class SeasonRepositoryImpl implements SeasonRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(SeasonRepository.class);
    
    private final JdbcTemplate jdbcTemplate;
 
    @Autowired
    public SeasonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
	@Override
	public List<Season> findAll() {
		return jdbcTemplate.query("select кодСезон as id, Сезон as name from СЕЗОНЫ", ROW_MAPPER);
	}

	@Override
	public Season findOne(String id) {
		Season brand = null;
		try {
			Integer.parseInt(id);
	        try {
	        	brand = jdbcTemplate.queryForObject("select кодСезон as id, Сезон as name from СЕЗОНЫ where кодСезон = ?", new Object[]{id}, ROW_MAPPER);
	            } catch (DataAccessException dataAccessException) {
	                LOGGER.debug("Не найдена запись в таблице СЕЗОНЫ с кодСезон {}", id);
	            }
	    } catch (NumberFormatException ex)
	    {
	        try {
	        	brand = jdbcTemplate.queryForObject("select кодСезон as id, Сезон as name from СЕЗОНЫ where Сезон = ?", new Object[]{id}, ROW_MAPPER);
	            } catch (DataAccessException dataAccessException) {
	                LOGGER.debug("Не найдена запись в таблице СЕЗОНЫ с Сезон {}", id);
	            }
	    }     
		return brand;
	}

	@Override
	public Season save(Season contragent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
