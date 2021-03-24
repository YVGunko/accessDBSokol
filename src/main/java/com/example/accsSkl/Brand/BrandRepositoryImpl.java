package com.example.accsSkl.Brand;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public final class BrandRepositoryImpl implements BrandRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandRepository.class);
    
    private final JdbcTemplate jdbcTemplate;
 
    @Autowired
    public BrandRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	@Override
	public List<Brand> findAll() {
		return jdbcTemplate.query("select кодБренд as id, Бренд as name from БРЕНДЫ", ROW_MAPPER);
	}

	@Override
	public Brand findOne(String id) {
		Brand brand = null;
		try {
			Integer.parseInt(id);
	        try {
	        	brand = jdbcTemplate.queryForObject("select кодБренд as id, Бренд as name from БРЕНДЫ where кодБренд = ?", new Object[]{id}, ROW_MAPPER);
	            } catch (DataAccessException dataAccessException) {
	                LOGGER.debug("Не найдена запись в таблице БРЕНДЫ с кодБренд {}", id);
	            }
	    } catch (NumberFormatException ex)
	    {
	        try {
	        	brand = jdbcTemplate.queryForObject("select кодБренд as id, Бренд as name from БРЕНДЫ where Бренд = ?", new Object[]{id}, ROW_MAPPER);
	            } catch (DataAccessException dataAccessException) {
	                LOGGER.debug("Не найдена запись в таблице БРЕНДЫ с Бренд {}", id);
	            }
	    }     
		return brand;
	}

	@Override
	public Brand save(Brand contragent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
