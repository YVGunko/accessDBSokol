package com.example.accsSkl.Contragent;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.accsSkl.Contragent.Contragent;
import com.example.accsSkl.Contragent.ContragentRepository;

@Component
public final class ContragentRepositoryImpl implements ContragentRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(ContragentRepository.class);
    
    private final JdbcTemplate jdbcTemplate;
 
    @Autowired
    public ContragentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
 
    @Override
    public List<Contragent> findAll() {
    		return jdbcTemplate.query("select кодДоговорКонтрагент AS id, ДоговорКонтрагент AS name, Последовательность AS sequence"
    				+ " from КОНТРАГЕНТЫ_ДОГОВОР where Последовательность is not null order by Последовательность", ROW_MAPPER);
    }
 
    @Override
    public Contragent findOne(String id) {
    	Contragent client = null;
    		try {
    			Integer.parseInt(id);
    	        try {
    	        	client = jdbcTemplate.queryForObject("select кодДоговорКонтрагент AS id, ДоговорКонтрагент AS name, Последовательность AS sequence"
    	        			+ " from КОНТРАГЕНТЫ_ДОГОВОР where (Последовательность is not null) and кодДоговорКонтрагент = ?", new Object[]{id}, ROW_MAPPER);
    	            } catch (DataAccessException dataAccessException) {
    	                LOGGER.debug("Не найдена запись в таблице КОНТРАГЕНТЫ_ДОГОВОР с кодДоговорКонтрагент {}", id);
    	            }
    	    } catch (NumberFormatException ex)
    	    {
    	        try {
    	        	client = jdbcTemplate.queryForObject("select кодДоговорКонтрагент AS id, ДоговорКонтрагент AS name, Последовательность AS sequence"
    	        			+ " from КОНТРАГЕНТЫ_ДОГОВОР where (Последовательность is not null) and ДоговорКонтрагент = ?", new Object[]{id}, ROW_MAPPER);
    	            } catch (DataAccessException dataAccessException) {
    	                LOGGER.debug("Не найдена запись в таблице КОНТРАГЕНТЫ_ДОГОВОР с ДоговорКонтрагент {}", id);
    	            }
    	    }     
        return client;
    }

	@Override
	public Contragent save(Contragent contragent) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int delete(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
