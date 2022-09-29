package com.example.accsSkl.Box;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.accsSkl.Config;
import com.example.accsSkl.OrderTrace.OrderTrace;


@Component
public class BoxRepositoryImpl implements BoxRepository {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(BoxRepository.class);
    private final JdbcTemplate jdbcTemplate;
    
	@Autowired
    private Environment env; 
	
    @Autowired
    public BoxRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Override
	@Transactional
	public int createDB () {
		try {
			String databaseURL = env.getProperty("asBoxUrl")+";newDatabaseVersion=V2010";
	      
			try (Connection connection = DriverManager.getConnection(databaseURL)) {
         
    			System.out.println("CREATE Database Box. Success");    
    			return 1;
        
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
				System.out.println("CREATE Database Box. SQLException. "+sqlEx); 
				return 0;
			}
		} catch (Exception ex){
			ex.printStackTrace();
			System.out.println("CREATE Database Box. Exception. "+ex); 
			return -1;
		}
		
	}
	@Override
	@Transactional
	public int createBoxTable () {
		try {
			String databaseURL = env.getProperty("asBoxUrl");
	      
			try (Connection connection = DriverManager.getConnection(databaseURL)) {
         
    			String sql = "CREATE TABLE BOX (\n" + 
    					"    id          LONG ,\n" + 
    					"    quantity         INTEGER ,\n" + 
    					"    model            LONG ,\n" + 
    					"    sizing           LONG ,\n" + 
    					"    numberOfOrder    LONG ,\n" + 
    					"    dateOfTrace      TIMESTAMP ,\n" + 
    					"    receiver         LONG ,\n" + 
    					"    client           LONG ,\n" + 
    					"    brand            LONG ,\n" + 
    					"    season           LONG ,\n" + 
    					"    sender           LONG ,\n" + 
    					"    orderTrace       LONG ,\n" + 
    					"    sentToMasterDate TIMESTAMP ,\n" + 
    					"    sticker          LONG ,\n" + 
    					");";
         
    			Statement statement = connection.createStatement();
    			int result = statement.executeUpdate(sql);
         
    			System.out.println("CREATE TABLE Box. Success");    
    			return 1;
        
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
				return 0;
			}
		} catch (Exception ex){
			ex.printStackTrace();
			return -1;
		}
		
	}
	
	@Override
	@Transactional
	public int insert(Box box) {
		try {
			String databaseURL = env.getProperty("asBoxUrl");
	      
			try (Connection connection = DriverManager.getConnection(databaseURL)) {
         
				String sQuery = "INSERT INTO BOX ([ID], [QUANTITY], [MODEL], [SIZING], [NUMBEROFORDER], "
						+ "[DATEOFTRACE], [RECEIVER], [CLIENT], [BRAND], [SEASON], "
						+ "[SENDER], [ORDERTRACE], [SENTTOMASTERDATE], [STICKER]) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
         
				PreparedStatement st = connection.prepareStatement (sQuery);
				/*
				 * {box.getId(), box.getQuantity(), box.getModel_id(), box.getSizing_id(), box.getNumberOfOrder(), 
					box.getDateOfTrace(), box.getReceiver_id(), box.getClient_id(), box.getBrand_id(), box.getSeason_id(),
					box.getSender_id(), box.getParent_id(), new Date().getTime(), box.getSticker()}
					*/
				st.setLong(1, box.getId());
				st.setInt(2, box.getQuantity());
				st.setLong(3, box.getModel_id());
				st.setLong(4, box.getSizing_id());
				st.setLong(5, box.getNumberOfOrder());
				//st.setLong(6, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(box.getDateOfTrace()).getTime());
				st.setTimestamp(6, getTimeStamp(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(box.getDateOfTrace()).getTime()));
				st.setLong(7, box.getReceiver_id());
				st.setLong(8, box.getClient_id());
				st.setLong(9, box.getBrand_id());
				st.setLong(10, box.getSeason_id());
				st.setLong(11, box.getSender_id());
				st.setLong(12, box.getParent_id());
				if (box.getSentToMasterDate()==0)
					st.setTimestamp(13, getCurrentTimeStamp());
				else	st.setTimestamp(13, getCurrentTimeStamp());//box.getSentToMasterDate());
				st.setLong(14, box.getSticker());
				
				st.executeUpdate();
         
    			System.out.println("INSERT INTO BOX. Success. Box id is : "+box.getId().toString());    
    			return 1;
        
			} catch (SQLException sqlEx) {
				LOGGER.debug("Ошибка при INSERT INTO BOX. Box id is : {} "+sqlEx, box.getId());
				sqlEx.printStackTrace();
				return 0;
			}
		} catch (Exception ex){
			ex.printStackTrace();
			return -1;
		}

	}
	
	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	
	private static java.sql.Timestamp getTimeStamp(long dt) {

		return new java.sql.Timestamp(dt);

	}

	@Override
	public List<Box> findAll() {
		List<Box> respond = new ArrayList<>();
		try {
			String databaseURL = env.getProperty("asBoxUrl");
	      
			try (Connection connection = DriverManager.getConnection(databaseURL)) {
         
    			String sql = "SELECT [QUANTITY], [MODEL], [SIZING], [NUMBEROFORDER], " + 
    					" [DATEOFTRACE], [RECEIVER], [CLIENT], [BRAND], [SEASON], " + 
    					" [SENDER], [ORDERTRACE], [SENTTOMASTERDATE], [STICKER], [ID] "
    					+ " FROM BOX ";
         
    			Statement statement = connection.createStatement();
    			ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                        	respond.add(new Box(result.getInt("quantity"),        		
                        			result.getLong ("MODEL"), 
                        			result.getLong ("SIZING"), 
                        			result.getLong("numberOfOrder"), 
                            		Config.getLongDateString(result.getTimestamp("dateOfTrace").getTime()),      		 
                            		result.getLong ("RECEIVER"), 
                            		result.getLong ("CLIENT"),
                            		result.getLong ("BRAND"), 
                            		result.getLong ("SEASON"),  
                            		result.getLong ("SENDER"),      		
                            		result.getLong("ORDERTRACE"), 
                            		result.getTimestamp("sentToMasterDate").getTime(),
                            		result.getLong("sticker"),
                            		result.getLong("id")));
                }   
    			return respond;
        
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
				return respond;
			}
		} catch (Exception ex){
			ex.printStackTrace();
			return respond;
		}
	}

	@Override
	public Box findOne(long id) {
		Box respond = new Box(0, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Config.getLongDateString(new Date().getTime()),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),
				new Date().getTime(), Long.valueOf(0), Long.valueOf(0));
		try {
			String databaseURL = env.getProperty("asBoxUrl");
	      
			try (Connection connection = DriverManager.getConnection(databaseURL)) {
         
    			String sql = "SELECT [QUANTITY], [MODEL], [SIZING], [NUMBEROFORDER], " 
    					+ " [DATEOFTRACE], [RECEIVER], [CLIENT], [BRAND], [SEASON], "
    					+ " [SENDER], [ORDERTRACE], [SENTTOMASTERDATE], [STICKER], [ID] "
    					+ " FROM BOX "
    					+ " WHERE [ID] = "+id;
         
    			Statement statement = connection.createStatement();
    			ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                	respond=(new Box(result.getInt("quantity"),        		
                			result.getLong ("MODEL"), 
                			result.getLong ("SIZING"), 
                			result.getLong("numberOfOrder"), 
                    		Config.getLongDateString(result.getTimestamp("dateOfTrace").getTime()),      		 
                    		result.getLong ("RECEIVER"), 
                    		result.getLong ("CLIENT"),
                    		result.getLong ("BRAND"), 
                    		result.getLong ("SEASON"),  
                    		result.getLong ("SENDER"),      		
                    		result.getLong("ORDERTRACE"), 
                    		result.getTimestamp("sentToMasterDate").getTime(),
                    		result.getLong("sticker"),
                    		result.getLong("id")));
        }   
		return respond;
        
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
				return respond;
			}
		} catch (Exception ex){
			ex.printStackTrace();
			return respond;
		}
	}
}
	
