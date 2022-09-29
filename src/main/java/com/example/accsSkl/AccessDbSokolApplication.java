package com.example.accsSkl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableWebMvc



public class AccessDbSokolApplication implements CommandLineRunner {


	@Autowired
    private Environment env; 
	
	public static void main(String[] args) {
		SpringApplication.run(AccessDbSokolApplication.class, args);
		


		try {
	        
			Config cfg = new Config();
			//String databaseURL = url;
			String databaseURL = cfg.getProperty("databaseURL");
		      
			try (Connection connection = DriverManager.getConnection(databaseURL)) {
             
        			String sql = "SELECT max([кодДвижЗаказа]) from ДВИЖЕНИЕ_ЗАКАЗА";
             
        			Statement statement = connection.createStatement();
        			ResultSet result = statement.executeQuery(sql);
             
        			System.out.println("Looks like connection is ok.");    
            
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
				}
		} catch (Exception ex){
				ex.printStackTrace();
    			}
    }

	@Override
	public void run(String... args) throws Exception {

        System.out.println(env.getProperty("asDBUrl"));
        System.out.println(env.getProperty("asFont"));
        //System.out.println(env.getProperty("asHeadFont"));
        //Config.FONT = env.getProperty("asFont");
        //Config.HEADFONT = env.getProperty("asHeadFont");
		
	}
}
