package com.techmahindra.smartparking.controller.device;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.constant.IConstant;
import com.techmahindra.smartparking.pojo.dto.request.device.SpSensorDataRequestDTO;
import com.techmahindra.smartparking.service.device.SPSSensorService;

@RestController
@RequestMapping(value = "/impact")
public class DeviceDataPushController {
	private final Logger logger = Logger.getLogger(IConstant.DEVICE_LOGGER + this.getClass());
	
	private ExecutorService executorService;
	
	@Autowired
	private SPSSensorService sensorService;
	
	@RequestMapping(value = "/callback", method=RequestMethod.POST) 
	public void callbackNotificationData(@RequestBody(required = false) String notification) {
		if (notification != null && !notification.isEmpty()) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(notification);
				logger.info("Smart Parking -> Received notify2 data :: " + jsonInString);

				if (this.executorService != null && !this.executorService.isShutdown()) {
					DeviceDataPushTask deviceDataPushTask = new DeviceDataPushTask(sensorService, notification);
					logger.info(String.format("Notification Data submitted to Task=%s", deviceDataPushTask.getTaskId()));
					this.executorService.execute(deviceDataPushTask);
				}else {
					logger.info("executorService is not started.");
				}
			} catch (Exception ex) {
				logger.error("Error while saving Notification data ", ex);
			}
		}else {
			logger.info("Notification Data could not be saved. payload=" + notification);
		}	
	}
	
	@PostConstruct
	public void init() throws Exception {
		logger.info("Staring executor service");
		executorService = Executors.newFixedThreadPool(100);
		logger.info("Executor Service started Successfully!");
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
		logger.info("Shutting Down executor service");
		if(executorService != null) {
			executorService.shutdownNow();
		}
		logger.info("Executor Service shut down successfully!");
	}
	
	private static class DeviceDataPushTask implements Runnable {
		private final Logger logger = Logger.getLogger(IConstant.DEVICE_LOGGER + this.getClass());
		private SPSSensorService sensorService;
		private String notification;
		private String taskId = UUID.randomUUID().toString();
		
		DeviceDataPushTask(SPSSensorService sensorService, String notification) {
			this.sensorService = sensorService;
			this.notification = notification;
		}

		@Override
		public void run() {
			if (notification != null && !notification.isEmpty()) {
				try {
					ObjectMapper mapper = new ObjectMapper();
					// TODO CASSANDRA CALL
					SpSensorDataRequestDTO sensorDataRequestDTO = mapper.readValue(notification, SpSensorDataRequestDTO.class);
					if (sensorDataRequestDTO != null) {
						if(sensorService != null) {
							logger.info("Going to save Notification data for taskId="+taskId);
							sensorService.saveSensorData(sensorDataRequestDTO);
							logger.info("Notification data saved Successfully for taskId="+taskId);
						}else {
							logger.info("Could not save Notification data as sensorService parameter is null");
						}
					} else {
						logger.info("Could not save Notification data as SpSensorDataRequestDTO is NULL");
					}
				} catch (GenericDBException gEx) {
					logger.error("Error while saving Notification data for taskId="+taskId, gEx);
				} catch (Exception ex) {
					logger.error("Error while saving Notification data for taskId="+taskId, ex);
				}
			} else {
				logger.info("Could not save notification data as notification payload is null. payload=" + notification);
			}
		}

		public String getTaskId() {
			return taskId;
		}
		
	}
}
