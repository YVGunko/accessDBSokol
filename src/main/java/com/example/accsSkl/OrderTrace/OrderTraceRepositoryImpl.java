package com.example.accsSkl.OrderTrace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.accsSkl.Config;

import net.ucanaccess.converters.TypesMap.AccessType; 
import net.ucanaccess.ext.FunctionType; 
import net.ucanaccess.jdbc.UcanaccessConnection; 
import net.ucanaccess.jdbc.UcanaccessDriver; 

@Component
public final class OrderTraceRepositoryImpl implements OrderTraceRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTraceRepository.class);
    
    private final JdbcTemplate jdbcTemplate;

	private Connection ucaConn;
 
    @Autowired
    public OrderTraceRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
	@Override
	public List<OrderTrace> findAll() {
		List<OrderTrace> lClient = new ArrayList<>();
		try {
			lClient = jdbcTemplate.query("SELECT ДВИЖЕНИЕ_ЗАКАЗА.кодДвижЗаказа, ДВИЖЕНИЕ_ЗАКАЗА.[№Партии], ДВИЖЕНИЕ_ЗАКАЗА.ДатаДок, ДВИЖЕНИЕ_ЗАКАЗА.ВидДок, "
    				+ "КОНТРАГЕНТЫ_ДОГОВОР_1.кодДоговорКонтрагент, КОНТРАГЕНТЫ_ДОГОВОР_1.ДоговорКонтрагент, КОНТРАГЕНТЫ_ДОГОВОР_1.Последовательность, "
    				+ "КОНТРАГЕНТЫ_ДОГОВОР.кодДоговорКонтрагент AS кодДоговорКонтрагент1, КОНТРАГЕНТЫ_ДОГОВОР.ДоговорКонтрагент AS ДоговорКонтрагент1, КОНТРАГЕНТЫ_ДОГОВОР.Последовательность AS Последовательность1, "
    				+ "КЛИЕНТ_ДОСТАВКА.кодКлиентДоставка, КЛИЕНТ_ДОСТАВКА.Получатель, "
    				+ "БРЕНДЫ.кодБренд, БРЕНДЫ.Бренд, "
    				+ "СЕЗОНЫ.кодСезон, СЕЗОНЫ.Сезон " + 
    				"FROM КЛИЕНТ_ДОСТАВКА AS КЛИЕНТ_ДОСТАВКА INNER JOIN "
    					+ "(БРЕНДЫ AS БРЕНДЫ INNER JOIN "
    						+ "(КОНТРАГЕНТЫ_ДОГОВОР AS КОНТРАГЕНТЫ_ДОГОВОР_1 INNER JOIN "
    							+ "(КОНТРАГЕНТЫ_ДОГОВОР AS КОНТРАГЕНТЫ_ДОГОВОР INNER JOIN "
    								+ "(ДВИЖЕНИЕ_ЗАКАЗА AS ДВИЖЕНИЕ_ЗАКАЗА INNER JOIN СЕЗОНЫ AS СЕЗОНЫ ON ДВИЖЕНИЕ_ЗАКАЗА.кодСезон = СЕЗОНЫ.кодСезон) "
    								+ "ON КОНТРАГЕНТЫ_ДОГОВОР.кодДоговорКонтрагент = ДВИЖЕНИЕ_ЗАКАЗА.ПОЛУЧАТЕЛЬ) "
    							+ "ON КОНТРАГЕНТЫ_ДОГОВОР_1.кодДоговорКонтрагент = ДВИЖЕНИЕ_ЗАКАЗА.ОТПРАВИТЕЛЬ) "
    						+ "ON БРЕНДЫ.кодБренд = ДВИЖЕНИЕ_ЗАКАЗА.БрендКод) "
    					+ "ON КЛИЕНТ_ДОСТАВКА.кодКлиентДоставка = ДВИЖЕНИЕ_ЗАКАЗА.Клиент WHERE ДВИЖЕНИЕ_ЗАКАЗА.ВидДок=\"Выход Цеха\""
    					+ "ORDER BY ДВИЖЕНИЕ_ЗАКАЗА.кодДвижЗаказа", ROW_MAPPER);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице КОНТРАГЕНТЫ_ДОГОВОР с кодДоговорКонтрагент");
		}
		return lClient;
	}

	@Override
	public OrderTrace findOne(String id) {
		OrderTrace result = null;
		try {
			result = jdbcTemplate.queryForObject("SELECT ДВИЖЕНИЕ_ЗАКАЗА.кодДвижЗаказа AS id, ДВИЖЕНИЕ_ЗАКАЗА.[№Партии] AS numberOfOrder, ДВИЖЕНИЕ_ЗАКАЗА.ДатаДок AS dateOfTrace, ДВИЖЕНИЕ_ЗАКАЗА.ВидДок AS nameOfTrace, " + 
					"КОНТРАГЕНТЫ_ДОГОВОР_1.кодДоговорКонтрагент AS sender_id, КОНТРАГЕНТЫ_ДОГОВОР_1.ДоговорКонтрагент AS sender_name, КОНТРАГЕНТЫ_ДОГОВОР_1.Последовательность AS sender_sequence, " + 
					"КОНТРАГЕНТЫ_ДОГОВОР.кодДоговорКонтрагент  AS receiver_id, КОНТРАГЕНТЫ_ДОГОВОР.ДоговорКонтрагент AS receiver_name, КОНТРАГЕНТЫ_ДОГОВОР.Последовательность AS receiver_sequence, " + 
					"КЛИЕНТ_ДОСТАВКА.кодКлиентДоставка AS client_id, КЛИЕНТ_ДОСТАВКА.Получатель AS client_name, " + 
					"БРЕНДЫ.кодБренд AS brand_id, БРЕНДЫ.Бренд AS brand_name, " + 
					"СЕЗОНЫ.кодСезон AS season_id, СЕЗОНЫ.Сезон AS season_name " + 
					"FROM КЛИЕНТ_ДОСТАВКА AS КЛИЕНТ_ДОСТАВКА INNER JOIN " + 
					"(БРЕНДЫ AS БРЕНДЫ INNER JOIN " + 
					"(КОНТРАГЕНТЫ_ДОГОВОР AS КОНТРАГЕНТЫ_ДОГОВОР_1 INNER JOIN " + 
					"(КОНТРАГЕНТЫ_ДОГОВОР AS КОНТРАГЕНТЫ_ДОГОВОР INNER JOIN " + 
					"(ДВИЖЕНИЕ_ЗАКАЗА AS ДВИЖЕНИЕ_ЗАКАЗА INNER JOIN СЕЗОНЫ AS СЕЗОНЫ ON ДВИЖЕНИЕ_ЗАКАЗА.кодСезон = СЕЗОНЫ.кодСезон) " + 
					"ON КОНТРАГЕНТЫ_ДОГОВОР.кодДоговорКонтрагент = ДВИЖЕНИЕ_ЗАКАЗА.ПОЛУЧАТЕЛЬ) " + 
					"ON КОНТРАГЕНТЫ_ДОГОВОР_1.кодДоговорКонтрагент = ДВИЖЕНИЕ_ЗАКАЗА.ОТПРАВИТЕЛЬ) " + 
					"ON БРЕНДЫ.кодБренд = ДВИЖЕНИЕ_ЗАКАЗА.БрендКод) " + 
					"ON КЛИЕНТ_ДОСТАВКА.кодКлиентДоставка = ДВИЖЕНИЕ_ЗАКАЗА.Клиент WHERE ДВИЖЕНИЕ_ЗАКАЗА.кодДвижЗаказа = ?", new Object[]{id}, ROW_MAPPER);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице ДВИЖЕНИЕ_ЗАКАЗА с кодДвижЗаказа {} ", id);
		}
		return result;
	}
	
	@Override
	public OrderTrace findOne(Long id) {
		OrderTrace result = null;
		try {
			result = jdbcTemplate.queryForObject("SELECT кодДвижЗаказа AS id, [№Партии] AS numberOfOrder, ДатаДок AS dateOfTrace, ВидДок AS nameOfTrace, " + 
					"ОТПРАВИТЕЛЬ AS sender_id, ПОЛУЧАТЕЛЬ  AS receiver_id, КЛИЕНТ AS client_id, БрендКод AS brand_id, кодСезон AS season_id " + 
					"FROM ДВИЖЕНИЕ_ЗАКАЗА WHERE кодДвижЗаказа = ?", new Object[]{id}, ROW_MAPPER);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице ДВИЖЕНИЕ_ЗАКАЗА с кодДвижЗаказа {} ", id);
		}
		return result;
	}
	
	@Override
	public OrderTrace findOne(Long numberOfOrder, String dateOfTrace, Long sender_id, Long receiver_id, Long client_id, Long brand_id, Long season_id) {
		OrderTrace result = null;
		try {
			result = jdbcTemplate.queryForObject("SELECT max(кодДвижЗаказа) AS id " + 
					" FROM ДВИЖЕНИЕ_ЗАКАЗА "
					+ " WHERE [№Партии] = ?"
					+ " and ДатаДок = ?"
					+ " and ВидДок =\"Выход Цеха\""
					+ " and ОТПРАВИТЕЛЬ = ?"
					+ " and ПОЛУЧАТЕЛЬ = ?"
					+ " and КЛИЕНТ = ?"
					+ " and БрендКод = ?"
					+ " and кодСезон = ?", new Object[]{numberOfOrder, dateOfTrace, 
							sender_id, 	receiver_id, client_id,	brand_id, season_id}, ROW_MAPPER_ID);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице ДВИЖЕНИЕ_ЗАКАЗА с кодДвижЗаказа {} ", numberOfOrder, new SimpleDateFormat("yyyy-MM-dd").format(dateOfTrace), 
					sender_id, 	receiver_id, client_id,	brand_id, season_id);
		}
		return result;
	}
	
	@Override
	public List<OrderTrace> findAllGreaterThan(Long date) {
		List<OrderTrace> lClient = new ArrayList<>();
		
		//LocalDateTime dtIn = java.time.LocalDateTime.parse(date, DateTimeFormatter.ofPattern( "dd/MM/uuuu HH:mm:ss" ));
		Config cfg = new Config();
		Date dtIn = cfg.lDateToDate(date);
		
		try {
			lClient = jdbcTemplate.query("SELECT кодДвижЗаказа AS id, [№Партии] AS numberOfOrder, ДатаДок AS dateOfTrace, ВидДок AS nameOfTrace,"
					+ " ОТПРАВИТЕЛЬ AS sender_id, ПОЛУЧАТЕЛЬ AS receiver_id, Клиент AS client_id, БрендКод AS brand_id, кодСезон AS season_id"
    				+" FROM ДВИЖЕНИЕ_ЗАКАЗА WHERE ДатаДок > ? AND ВидДок=\"Запуск\""
    				+" ORDER BY кодДвижЗаказа", new Object[]{dtIn},  ROW_MAPPER_DOC);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице КОНТРАГЕНТЫ_ДОГОВОР с кодДоговорКонтрагент {}", dtIn);
		}
		return lClient;
	}

	
	@Override
	@Transactional
	public Long insert(OrderTrace orderTrace) {
		Long result = (long)0;
		try {
			String sQuery = "INSERT INTO ДВИЖЕНИЕ_ЗАКАЗА ([№Партии], [ДатаДок], [ВидДок], [ОТПРАВИТЕЛЬ], [ПОЛУЧАТЕЛЬ], [Клиент], [БрендКод], [кодСезон]) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			result = Long.valueOf(jdbcTemplate.update(sQuery, new Object[]{orderTrace.getNumberOfOrder(), new SimpleDateFormat("yyyy-MM-dd").format(new Date(orderTrace.getDateOfTrace())), 
					orderTrace.getNameOfTrace(), 	orderTrace.getSender().getId(), orderTrace.getReceiver().getId(), 
					orderTrace.getClient().getId(), orderTrace.getBrand().getId(), orderTrace.getSeason().getId()}));
			if (result > 0) {

				result = findOne(orderTrace.getNumberOfOrder(), new SimpleDateFormat("yyyy-MM-dd").format(new Date(orderTrace.getDateOfTrace())),
						orderTrace.getSender().getId(), orderTrace.getReceiver().getId(), 
						orderTrace.getClient().getId(), orderTrace.getBrand().getId(), orderTrace.getSeason().getId()).getId();
			}
			
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Ошибка при INSERT INTO ДВИЖЕНИЕ_ЗАКАЗА {}", orderTrace.getNumberOfOrder(), orderTrace.getDateOfTrace(), 
					orderTrace.getNameOfTrace(), 	orderTrace.getSender().getId(), orderTrace.getReceiver().getId(), 
					orderTrace.getClient().getId(), orderTrace.getBrand().getId(), orderTrace.getSeason().getId());
		}
		return result;
	}
	
	@Override
	@Transactional
	public int update(OrderTrace orderTrace) {
		int result = 0;
		try {
			String sQuery = "UPDATE ДВИЖЕНИЕ_ЗАКАЗА SET [№Партии]=?, [ДатаДок]=?, [ВидДок]=?, [ОТПРАВИТЕЛЬ]=?, [ПОЛУЧАТЕЛЬ]=?, [Клиент]=?, [БрендКод]=?, [кодСезон]=? "
					+ " WHERE [кодДвижЗаказа]= ?";
			result = jdbcTemplate.update(sQuery, new Object[]{orderTrace.getNumberOfOrder(), orderTrace.getDateOfTrace(), 
					orderTrace.getNameOfTrace(), 	orderTrace.getSender().getId(), orderTrace.getReceiver().getId(), 
					orderTrace.getClient().getId(), orderTrace.getBrand().getId(), orderTrace.getSeason().getId()});
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Ошибка при UPDATE ДВИЖЕНИЕ_ЗАКАЗА {}", orderTrace.getNumberOfOrder(), orderTrace.getDateOfTrace(), 
					orderTrace.getNameOfTrace(), 	orderTrace.getSender().getId(), orderTrace.getReceiver().getId(), 
					orderTrace.getClient().getId(), orderTrace.getBrand().getId(), orderTrace.getSeason().getId());
		}
		return result;
	}

	@Override
	@Transactional
	public int delete(Long id) {
		int result = 0;
		try {
			String sQuery = "DELETE FROM ДВИЖЕНИЕ_ЗАКАЗА WHERE [кодДвижЗаказа]= ?";
			result = jdbcTemplate.update(sQuery, new Object[]{id});
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Ошибка при DELETE FROM ДВИЖЕНИЕ_ЗАКАЗА {}", id);
		}
		return result;
	}

}
