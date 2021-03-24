package com.example.accsSkl.OrderTraceDetail;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.accsSkl.Config;
import com.example.accsSkl.OrderTrace.OrderTrace;



@Component
public final class OrderTraceDetailRepositoryImpl implements OrderTraceDetailRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTraceDetailRepository.class);
    
    private final JdbcTemplate jdbcTemplate;
 
    @Autowired
    public OrderTraceDetailRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public List<OrderTraceDetail> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<OrderTraceDetail> findAllRowsAndDoc(long date) {
		List<OrderTraceDetail> result = null;
		Config cfg = new Config();
		Date dtIn = cfg.lDateToDate(date);
		try {
			result = jdbcTemplate.query("SELECT Движение_заказаТабл.кодДвижЗакТабл as id, ДВИЖЕНИЕ_ЗАКАЗА.кодДвижЗаказа as parent_id,  Движение_заказаТабл.[№Партии] as numberOfOrder,"
					+ " ДВИЖЕНИЕ_ЗАКАЗА.ДатаДок as dateOfTrace, ДВИЖЕНИЕ_ЗАКАЗА.ВидДок as nameOfTrace,"
					+ " ДВИЖЕНИЕ_ЗАКАЗА.ОТПРАВИТЕЛЬ AS sender_id, ДВИЖЕНИЕ_ЗАКАЗА.ПОЛУЧАТЕЛЬ AS receiver_id, ДВИЖЕНИЕ_ЗАКАЗА.КЛИЕНТ AS client_id,"
					+ " ДВИЖЕНИЕ_ЗАКАЗА.БрендКод AS brand_id, ДВИЖЕНИЕ_ЗАКАЗА.кодСезон AS season_id,"
					+ " Движение_заказаТабл.КодМодели as model_id, Движение_заказаТабл.кодРостовки as sizing_id, Движение_заказаТабл.колВо as quantity" 
					+ " FROM ДВИЖЕНИЕ_ЗАКАЗА AS ДВИЖЕНИЕ_ЗАКАЗА, Движение_заказаТабл as Движение_заказаТабл"
					+ " WHERE ДВИЖЕНИЕ_ЗАКАЗА.кодДвижЗаказа = Движение_заказаТабл.кодДвижЗак AND ДВИЖЕНИЕ_ЗАКАЗА.ДатаДок > ? AND ((ДВИЖЕНИЕ_ЗАКАЗА.ВидДок=\"Запуск\") OR (ДВИЖЕНИЕ_ЗАКАЗА.ВидДок=\"Выход Цеха\"))", new Object[]{dtIn}, ROW_MAPPER_DOC);
			System.out.println("findAllRowsAndDoc query result size is "+result.size());
		} catch (DataAccessException dataAccessException) {//OR (ДВИЖЕНИЕ_ЗАКАЗА.ВидДок=\"Выход Цеха\")
			LOGGER.debug("Не найдена запись в таблице findAllRowsAndDoc с date {}", dtIn);
		}
		return result;
	}
	
	@Override
	public List<OrderTraceDetail> getBoxSticker(long date) {
		List<OrderTraceDetail> result = null;
		Config cfg = new Config();
		Date dtIn = cfg.lDateToDate(date);
		try {
			result = jdbcTemplate.query("SELECT Движение_заказаТабл.кодДвижЗакТабл as id, ДВИЖЕНИЕ_ЗАКАЗА.кодДвижЗаказа as parent_id,  Движение_заказаТабл.[№Партии] as numberOfOrder,"
					+ " ДВИЖЕНИЕ_ЗАКАЗА.ДатаДок as dateOfTrace, ДВИЖЕНИЕ_ЗАКАЗА.ВидДок as nameOfTrace,"
					+ " ДВИЖЕНИЕ_ЗАКАЗА.ОТПРАВИТЕЛЬ AS sender_id, ДВИЖЕНИЕ_ЗАКАЗА.ПОЛУЧАТЕЛЬ AS receiver_id, ДВИЖЕНИЕ_ЗАКАЗА.КЛИЕНТ AS client_id,"
					+ " ДВИЖЕНИЕ_ЗАКАЗА.БрендКод AS brand_id, ДВИЖЕНИЕ_ЗАКАЗА.кодСезон AS season_id,"
					+ " Движение_заказаТабл.КодМодели as model_id, Движение_заказаТабл.кодРостовки as sizing_id, Движение_заказаТабл.колВо as quantity" 
					+ " FROM ДВИЖЕНИЕ_ЗАКАЗА AS ДВИЖЕНИЕ_ЗАКАЗА, Движение_заказаТабл as Движение_заказаТабл"
					+ " WHERE ДВИЖЕНИЕ_ЗАКАЗА.кодДвижЗаказа = Движение_заказаТабл.кодДвижЗак AND ДВИЖЕНИЕ_ЗАКАЗА.ДатаДок > ? AND ((ДВИЖЕНИЕ_ЗАКАЗА.ВидДок=\"Запуск\") ) ", new Object[]{dtIn}, ROW_MAPPER_DOC);
			System.out.println("getBoxSticker query result size is "+result.size());
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице findAllRowsAndDoc с date {}", dtIn);
		}
		return result;
	}
	
	@Override
	public List<OrderTraceDetail> findAllRowsOfDoc(long id) {
		List<OrderTraceDetail> result = null;
		try {
			result = jdbcTemplate.query("SELECT кодДвижЗакТабл as id, кодДвижЗак as parent_id, [№Партии] as numberOfOrder, " + 
					" КодМодели as model_id, кодРостовки as sizing_id, колВо as quantity " + 
					" FROM Движение_заказаТабл WHERE кодДвижЗак = ?", new Object[]{id}, ROW_MAPPER_ONE);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найденs записb в таблице Движение_заказаТабл с кодДвижЗак {}", id);
		}
		return result;
	}

	@Override
	public OrderTraceDetail findOne(String id) {
		OrderTraceDetail result = null;
		try {
			result = jdbcTemplate.queryForObject("SELECT кодДвижЗакТабл as id, кодДвижЗак as parent_id, [№Партии] as numberOfOrder, "
					+ " КодМодели as model_id, кодРостовки as sizing_id, колВо as quantity "+ 
					" FROM Движение_заказаТабл WHERE кодДвижЗакТабл = ?", new Object[]{id}, ROW_MAPPER_ONE);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице Движение_заказаТабл с кодДвижЗакТабл {}", id);
		}
		return result;
	}

	@Override
	@Transactional
	public int insert(OrderTraceDetail orderTraceDetail) {
		int result = 0;
		try {
			String sQuery = "INSERT INTO Движение_заказаТабл ([№Партии], [кодДвижЗак], [КодМодели], [кодРостовки], [колВо]) "
				+ "VALUES (?, ?, ?, ?, ?)";
			result = jdbcTemplate.update(sQuery, new Object[]{orderTraceDetail.getNumberOfOrder(), orderTraceDetail.getOrderTrace().getId(), 
					orderTraceDetail.getModel().getId(), 	orderTraceDetail.getSizing().getId(), orderTraceDetail.getQuantity()});
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Ошибка при INSERT INTO Движение_заказаТабл {}", orderTraceDetail.getNumberOfOrder(), orderTraceDetail.getOrderTrace().getId(), 
					orderTraceDetail.getModel().getId(), 	orderTraceDetail.getSizing().getId(), orderTraceDetail.getQuantity());
		}
		return result;
	}

	@Override
	public int update(OrderTraceDetail orderTrace) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
