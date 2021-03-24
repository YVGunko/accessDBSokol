package com.example.accsSkl;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

 
public class Config
{
	private static final String dtPattern = "dd.MM.yyyy HH:mm:ss";
	private static final String dayPattern = "dd.MM.yyyy";
	public static String HEADFONT ;
	public static String FONT ;
   Properties configFile;
   
   public Config()
   {
	configFile = new java.util.Properties();
	try {
		configFile.load (new FileInputStream ("accsSkl.cfg"));
	  //configFile.load(this.getClass().getClassLoader().
	  //getResourceAsStream("accsSkl.cfg"));
	}catch(Exception eta){
	    eta.printStackTrace();
	}
   }
 
   public String getProperty(String key)
   {
	String value = this.configFile.getProperty(key);
	return value;
   }
   
   public static String getDayTimeString(Date date) {
      return org.apache.commons.lang3.time.DateFormatUtils.format(date, dtPattern);
   }
   
   public static String getLongDateString(Long date) {
	      return org.apache.commons.lang3.time.DateFormatUtils.format(date, dayPattern);
	   }
   
   public static long getStartOfDayLong(Date date) {
       return org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.DATE).getTime();
   }
   
   public Date lDateToDate (long lDate){
       Calendar cal = Calendar.getInstance();
       cal.setTimeInMillis(lDate);
       Date d = cal.getTime();
       return d;
   }
   
}