package com.example.accsSkl.Sticker;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.accsSkl.Config;
import com.example.accsSkl.OrderTrace.OrderTraceRepository;


@Component
public class StickerRepositoryImpl implements StickerRepository {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTraceRepository.class);
    
    private final JdbcTemplate jdbcTemplate;
 
    @Autowired
    public StickerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public List<Sticker> findStickerGreaterThan(Long idStickerMax) {
		List<Sticker> result = null;

		try {
			result = jdbcTemplate.query("SELECT c.`Пар_Коробе` as quantity, " + 
					"c.кодДок AS idDoc, c.кодВыборКарт as idSticker, d.кодДвижЗакТабл as idQRCode " + 
					"FROM `ВыборкаКарточек` AS c, `Движение_заказаТабл` AS d, ДВИЖЕНИЕ_ЗАКАЗА AS z " + 
					"WHERE c.кодДок=z.`кодДвижЗаказа` " + 
					"AND z.кодДвижЗаказа = d.кодДвижЗак " + 
					"AND (z.ВидДок=\"Запуск\") "+
					"AND c.`кодМодель`= d.`кодМодели` " +
					"AND c.`Ростовка`= d.`кодРостовки` " + 
					"AND c.кодВыборКарт > ? " + 
					"ORDER BY idSticker", new Object[]{idStickerMax}, ROW_MAPPER_REST);
			System.out.println("findStickerGreaterThan query result size is "+result.size());
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице Sticker ");
		}
		return result;
	}
	
	@Override
	public List<Sticker> getSticker() {
		List<Sticker> result = null;

		try {
			result = jdbcTemplate.query("SELECT c.`Дата` as dateOfTrace, c.`Пар_Коробе` as quantity, m.`№Модели` as model, r.`РазмерныйРяд` as sizing_row, " + 
					"cl.Получатель as client, d.[№Партии] as numberOfOrder, r.`Ростовка` sizing, "
					+ "c.кодДок AS idDoc, c.кодВыборКарт as idSticker, d.кодДвижЗакТабл as idQRCode " + 
					"FROM `ВыборкаКарточек` AS c, `Движение_заказаТабл` AS d, ДВИЖЕНИЕ_ЗАКАЗА AS z, " + 
					"КЛИЕНТ_ДОСТАВКА AS cl, МОДЕЛИ m, РОСТОВКИ r " + 
					"WHERE c.кодДок=z.`кодДвижЗаказа` " + 
					"AND z.кодДвижЗаказа = d.кодДвижЗак " + 
					"AND (z.ВидДок=\"Запуск\") "+
					"AND z.Клиент = cl.кодКлиентДоставка " + 
					"AND c.`кодМодель`= d.`кодМодели` " +
					"AND c.`кодМодель`= m.`кодМодели` " + 
					"AND c.`Ростовка`= d.`кодРостовки` " + 
					"AND c.`Ростовка`=r.`кодРостовки` " + 
					"ORDER BY dateOfTrace, idSticker", ROW_MAPPER);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице Sticker ");
		}
		return result;
	}
	//	public List<Sticker> getSticker(long date, long idDoc, long idSticker) {

	@Override
	public List<Sticker> findByNumberOfOrderAndDateAfter(Long idDoc, Long idSticker, Long date) {
		List<Sticker> result = null;
		Config cfg = new Config();
		Date dtIn = cfg.lDateToDate(date);
		
		try {
			String request = "SELECT c.`Дата` as dateOfTrace, c.`Пар_Коробе` as quantity, m.`№Модели` as model, r.`РазмерныйРяд` as sizing_row, " + 
					"cl.Получатель as client, d.[№Партии] as numberOfOrder, r.`Ростовка` sizing, "
					+ "c.кодДок AS idDoc, c.кодВыборКарт as idSticker, d.кодДвижЗакТабл as idQRCode " + 
					"FROM `ВыборкаКарточек` AS c, `Движение_заказаТабл` AS d, ДВИЖЕНИЕ_ЗАКАЗА AS z, " + 
					"КЛИЕНТ_ДОСТАВКА AS cl, МОДЕЛИ m, РОСТОВКИ r " + 
					"WHERE c.кодДок=z.`кодДвижЗаказа` " + 
					"AND z.кодДвижЗаказа = d.кодДвижЗак " + 
					"AND (z.ВидДок=\"Запуск\") "+
					"AND z.Клиент = cl.кодКлиентДоставка " + 
					"AND c.`кодМодель`= d.`кодМодели` " +
					"AND c.`кодМодель`= m.`кодМодели` " +
					"AND c.`Ростовка`= d.`кодРостовки` " + 
					"AND c.`Ростовка`=r.`кодРостовки` " ;
			Object[] filters = new Object[] {};
			if (idDoc != null) {
				request += "AND c.кодДок >= ? ";
				filters = ArrayUtils.add(filters,idDoc);
			}
			if (idSticker != null) {
				request += "AND c.кодВыборКарт >= ? ";
				filters = ArrayUtils.add(filters,idSticker);
			}
			if (date != null) {
				request += "AND c.`Дата` >= ? ";
				filters = ArrayUtils.add(filters,dtIn);
			}
			request += " ORDER BY dateOfTrace, idSticker ";
			result = jdbcTemplate.query(request, filters, ROW_MAPPER);
		} catch (DataAccessException dataAccessException) {
			LOGGER.debug("Не найдена запись в таблице Sticker ");
		}
		return result;
	}
}
