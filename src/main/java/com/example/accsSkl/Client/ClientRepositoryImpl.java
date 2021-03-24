package com.example.accsSkl.Client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.accsSkl.Client.Client;

@Component
public final class ClientRepositoryImpl implements ClientRepository {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepository.class);
 
    private final JdbcTemplate jdbcTemplate;
 
    @Autowired
    public ClientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
 
    @Override
    public List<Client> findAll() {
    		List<Client> lClient = new ArrayList<>();
    		try {
    			lClient = jdbcTemplate.query("select кодКлиентДоставка as id, Получатель as name from КЛИЕНТ_ДОСТАВКА", ROW_MAPPER);
    		} catch (DataAccessException dataAccessException) {
    			LOGGER.debug("Не найдена запись в таблице КОНТРАГЕНТЫ_ДОГОВОР с кодДоговорКонтрагент");
    		}
    		return lClient;
    }
 
    @Override
    public Client findOne(String id) {
    		Client client = null;
    		try {
    			Integer.parseInt(id);
    	        try {
    	        	client = jdbcTemplate.queryForObject("select кодКлиентДоставка as id, Получатель as name from КЛИЕНТ_ДОСТАВКА where кодКлиентДоставка = ?", new Object[]{id}, ROW_MAPPER);
    	            } catch (DataAccessException dataAccessException) {
    	                LOGGER.debug("Не найдена запись в таблице КЛИЕНТЫ с кодКлиентДоставка {}", id);
    	            }
    	    } catch (NumberFormatException ex)
    	    {
    	        try {
    	        	client = jdbcTemplate.queryForObject("select кодКлиентДоставка as id, Получатель as name from КЛИЕНТ_ДОСТАВКА where Получатель = ?", new Object[]{id}, ROW_MAPPER);
    	            } catch (DataAccessException dataAccessException) {
    	                LOGGER.debug("Не найдена запись в таблице КЛИЕНТЫ с Получатель {}", id);
    	            }
    	    }     
        return client;
    }
 
    @Override
    public Client save(Client client) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
 
    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
