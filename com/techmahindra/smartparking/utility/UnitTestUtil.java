package com.techmahindra.smartparking.utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnitTestUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(UnitTestUtil.class);
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	 
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
    
    public static <T> T convertJsonToObject(String jsonString, Class<T> clazz) {
		try {
			LOGGER.info("jsonString="+jsonString);
			ObjectMapper mapper = new ObjectMapper();
			T obj = (T) mapper.readValue(jsonString, clazz);
			return obj;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
		
    }
    
    public static Date getCurrentDateTime() {
		return new Date();
	}
    
    public static Date getDateTimeAfterMinutes(long minutes) {
		LocalDateTime now = LocalDateTime.now();
		now = now.plusMinutes(minutes);
		return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
	}
    
    public static Date getDateTimeAfterDays(long days) {
		LocalDateTime now = LocalDateTime.now();
		now = now.plusDays(days);
		return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
	}
    
	public static String getCurrentDateTimeString() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}
	
	public static String getDateTimeAfterMinutesString(long minutes) {
		LocalDateTime now = LocalDateTime.now();
		now = now.plusMinutes(minutes);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}
	
	public static String getDateTimeAfterDaysString(long days) {
		LocalDateTime now = LocalDateTime.now();
		now = now.plusDays(days);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}
}
