package com.techmahindra.smartparking.controller.booking;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.config.RootConfigTest;
import com.techmahindra.smartparking.constant.IResponse;
import com.techmahindra.smartparking.pojo.dto.request.booking.BookingPaymentRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.booking.BookingRequestDTO;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingResponseDTO;
import com.techmahindra.smartparking.service.booking.impl.SPSBookingServiceImpl;
import com.techmahindra.smartparking.utility.CommonUtility;
import com.techmahindra.smartparking.utility.UnitTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfigTest.class })
@WebAppConfiguration
public class SPSBookingControllerTest {
	private final static Logger LOGGER = LoggerFactory.getLogger( SPSBookingControllerTest.class);
	
	private static final String URL = "/booking/";
    private static final String GET_COST = "cost";
    private static final String BOOK_PARKING = "block";
    private static final String BOOKING_PAYMENT = "payment";

	private MockMvc mockMvc = null;
	
	@InjectMocks
	private SPSBookingController spsBookingController;

	@Rule
	public TestRule watcher = new TestWatcher() {
	   protected void starting(Description description) {
		   LOGGER.info("Executing " + description.getMethodName());
	   }
	};
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(spsBookingController).build();
	}
	
	@Mock
	SPSBookingServiceImpl spsBookingService;

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetBookingCostForADayBooking() {
		try {
			int levelId = 2; 
			int vehicleType = 2;
	        int slotType = 1;
	        String fromTime = UnitTestUtil.getCurrentDateTimeString();
	    	String toTime = UnitTestUtil.getDateTimeAfterMinutesString(10);
	        
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			params.add("levelId", String.valueOf(levelId));
			params.add("vehicleTypeId", String.valueOf(vehicleType));
			params.add("slotTypeId", String.valueOf(slotType));
			params.add("toTime", toTime);
			params.add("fromTime", fromTime);
			
			when(spsBookingService.getBookingCost(levelId, vehicleType, slotType, fromTime, toTime)).thenReturn(BigDecimal.valueOf(20));

			mockMvc.perform(get(URL + GET_COST).params(params))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.SUCCESS_CODE)))
	        .andExpect(jsonPath("price", equalTo(20)));
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}catch(Exception ex) {
			LOGGER.error("", ex);
		}
	}

	@Test
	public void testGetBookingCostForAWeekBooking() {
		try {
			int levelId = 2; 
			int vehicleType = 2;
	        int slotType = 1;
	        String fromTime = UnitTestUtil.getCurrentDateTimeString();
			String toTime = UnitTestUtil.getDateTimeAfterDaysString(7);
	        
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			params.add("levelId", String.valueOf(levelId));
			params.add("vehicleTypeId", String.valueOf(vehicleType));
			params.add("slotTypeId", String.valueOf(slotType));
			params.add("toTime", toTime);
			params.add("fromTime", fromTime);
			
			when(spsBookingService.getBookingCost(levelId, vehicleType, slotType, fromTime, toTime)).thenReturn(BigDecimal.valueOf(1680));

			mockMvc.perform(get(URL + GET_COST).params(params))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
	        .andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.SUCCESS_CODE)))
	        .andExpect(jsonPath("price", equalTo(1680)));
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}catch(Exception ex) {
			LOGGER.error("", ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetBookingCostForNoRecordFound() throws Exception {
		int levelId = 2;
		int vehicleType = 2;
		int slotType = 1;
		String fromTime = UnitTestUtil.getCurrentDateTimeString();
    	String toTime = UnitTestUtil.getDateTimeAfterMinutesString(10);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("levelId", String.valueOf(levelId));
		params.add("vehicleTypeId", String.valueOf(vehicleType));
		params.add("slotTypeId", String.valueOf(slotType));
		params.add("toTime", toTime);
		params.add("fromTime", fromTime);

		when(spsBookingService.getBookingCost(levelId, vehicleType, slotType, fromTime, toTime)).thenThrow(GenericDBException.class);

		mockMvc.perform(get(URL + GET_COST).params(params)).andExpect(status().isOk())
				.andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.ERROR_CODE_APPLICATION)));
	}

	@Test
	public void testBookParking() {
		try {
			int customerId = 2;
			int levelId = 2; 
			int vehicleType = 2;
	        int slotType = 1;
	        String fromTime = UnitTestUtil.getCurrentDateTimeString();
	    	String toTime = UnitTestUtil.getDateTimeAfterMinutesString(10);
	    	String timeZone = "IST";
	    	
			BookingRequestDTO requestBody = new BookingRequestDTO();
			requestBody.setCustomerId(customerId);
			requestBody.setLevelId(levelId);
			requestBody.setVehicleTypeId(vehicleType);
			requestBody.setSlotTypeId(slotType);
			requestBody.setFromTime(fromTime);
			requestBody.setToTime(toTime);
			requestBody.setTimeZone(timeZone);
			
			int expectedBookingId = 10;
			String expectedBookingDisplayId = CommonUtility.formatBookingId(expectedBookingId);
			
			BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
			bookingResponseDTO.setBookingId(expectedBookingId);
			bookingResponseDTO.setCustomerId(customerId);
			bookingResponseDTO.setBookingDisplayId(expectedBookingDisplayId);
			
			when(spsBookingService.blockParking(requestBody)).thenReturn(bookingResponseDTO);
			
			mockMvc.perform(post(URL + BOOK_PARKING)
							.contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
							.content(UnitTestUtil.convertObjectToJsonBytes(requestBody)))
	        	   .andExpect(status().isOk())
	        	   .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
	        	   .andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.SUCCESS_CODE)))
	        	   .andExpect(jsonPath("appHeader.statusMessage", equalTo("Parking Blocked Successfully.")))
	        	   .andExpect(jsonPath("customerId", equalTo(customerId)))
	        	   .andExpect(jsonPath("bookingId", equalTo(expectedBookingId)))
	        	   .andExpect(jsonPath("bookingDisplayId", equalTo(expectedBookingDisplayId)));
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}catch(Exception ex) {
			LOGGER.error("", ex);
		}
	}
	
	@Test
	public void testBookParking_WhenFromTimeAndToTimeInvalidFormat_ShouldReturnValidationErrorsForFromTimeAndToTime() {
		try {
			int customerId = 2;
			int levelId = 2; 
			int vehicleType = 2;
	        int slotType = 1;
	        String fromTime = "09-03-2017 15:50:00";
	    	String toTime = "06-03-2017 15:50:00";
	    	String timeZone = "IST";
	    	
			BookingRequestDTO requestBody = new BookingRequestDTO();
			requestBody.setCustomerId(customerId);
			requestBody.setLevelId(levelId);
			requestBody.setVehicleTypeId(vehicleType);
			requestBody.setSlotTypeId(slotType);
			requestBody.setFromTime(fromTime);
			requestBody.setToTime(toTime);
			requestBody.setTimeZone(timeZone);
			
			int expectedBookingId = 10;
			String expectedBookingDisplayId = CommonUtility.formatBookingId(expectedBookingId);
			
			BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
			bookingResponseDTO.setBookingId(expectedBookingId);
			bookingResponseDTO.setCustomerId(customerId);
			bookingResponseDTO.setBookingDisplayId(expectedBookingDisplayId);
			
			when(spsBookingService.blockParking(requestBody)).thenReturn(bookingResponseDTO);
			
			mockMvc.perform(post(URL + BOOK_PARKING)
							.contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
							.content(UnitTestUtil.convertObjectToJsonBytes(requestBody)))
	        	   .andExpect(status().isOk())
	        	   .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
	        	   .andDo(print())
	        	   .andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.ERROR_CODE_VALIDATION)))
	        	   .andExpect(jsonPath("appHeader.statusMessage", equalTo(IResponse.ERROR_MESSAGE_VALIDATION)))
	        	   .andExpect(jsonPath("errors.validationErrors[*].param", containsInAnyOrder("fromTime", "toTime")))
				   .andExpect(jsonPath("errors.validationErrors[*].msg", containsInAnyOrder("fromTime format must be YYYY-MM-DD HH:MM:SS .", "toTime format must be YYYY-MM-DD HH:MM:SS ."))).andReturn();
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}catch(Exception ex) {
			LOGGER.error("", ex);
		}
	}
	
	@Test
	public void testBookParking_WhenNoParkingSlotAvaliableForThisTimeDuration_ShouldReturnErrorCode() {
		try {
			int customerId = 2;
			int levelId = 2; 
			int vehicleType = 2;
	        int slotType = 1;
	        String fromTime = UnitTestUtil.getCurrentDateTimeString();
	    	String toTime = UnitTestUtil.getDateTimeAfterMinutesString(10);
	    	String timeZone = "IST";
	    	
			BookingRequestDTO requestBody = new BookingRequestDTO();
			requestBody.setCustomerId(customerId);
			requestBody.setLevelId(levelId);
			requestBody.setVehicleTypeId(vehicleType);
			requestBody.setSlotTypeId(slotType);
			requestBody.setFromTime(fromTime);
			requestBody.setToTime(toTime);
			requestBody.setTimeZone(timeZone);
			
			BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
			
			when(spsBookingService.blockParking(requestBody)).thenReturn(bookingResponseDTO);
			
			mockMvc.perform(post(URL + BOOK_PARKING)
							.contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
							.content(UnitTestUtil.convertObjectToJsonBytes(requestBody)))
	        	   .andExpect(status().isOk())
	        	   .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
	        	   .andDo(print())
	        	   .andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.ERROR_CODE_BLOCK_PARKING)))
	        	   .andExpect(jsonPath("appHeader.statusMessage", equalTo(IResponse.ERROR_MESSAGE_BLOCK_PARKING)));
			
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}catch(Exception ex) {
			LOGGER.error("", ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testBookParking_WhenExceptionIsThrown_ShouldReturnErrorCode() {
		try {
			int customerId = 2;
			int levelId = 2; 
			int vehicleType = 2;
	        int slotType = 1;
	        String fromTime = UnitTestUtil.getCurrentDateTimeString();
	    	String toTime = UnitTestUtil.getDateTimeAfterMinutesString(10);
	    	String timeZone = "IST";
	    	
			BookingRequestDTO requestBody = new BookingRequestDTO();
			requestBody.setCustomerId(customerId);
			requestBody.setLevelId(levelId);
			requestBody.setVehicleTypeId(vehicleType);
			requestBody.setSlotTypeId(slotType);
			requestBody.setFromTime(fromTime);
			requestBody.setToTime(toTime);
			requestBody.setTimeZone(timeZone);
			
			when(spsBookingService.blockParking(requestBody)).thenThrow(GenericDBException.class);
			
			mockMvc.perform(post(URL + BOOK_PARKING)
							.contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
							.content(UnitTestUtil.convertObjectToJsonBytes(requestBody)))
	        	   .andExpect(status().isOk())
	        	   .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
	        	   .andDo(print())
	        	   .andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.ERROR_CODE_APPLICATION)));
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}catch(Exception ex) {
			LOGGER.error("", ex);
		}
	}


	@Test
	public void testBookingPayment() {
		try {
				int bookingId = 10;    
				String transactionId = "20";
				BigDecimal cost = BigDecimal.valueOf(20);
				int customerId = 2;
				boolean extendedStatus = false;
				boolean paymentStatus = true;
				
				BookingPaymentRequestDTO requestBody = new BookingPaymentRequestDTO();
				requestBody.setBookingId(bookingId);
				requestBody.setTransactionId(transactionId);
				requestBody.setCost(cost);
				requestBody.setCustomerId(customerId);
				requestBody.setExtendedStatus(extendedStatus);
				requestBody.setPaymentStatus(paymentStatus);
				
				doNothing().when(spsBookingService).recievePayment(requestBody.getBookingId(),
						requestBody.getTransactionId(), requestBody.getCost(),
						requestBody.isPaymentStatus(),
						requestBody.isExtendedStatus());
				
				mockMvc.perform(post(URL + BOOKING_PAYMENT)
						.contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
						.content(UnitTestUtil.convertObjectToJsonBytes(requestBody)))
        	   .andExpect(status().isOk())
        	   .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
        	   .andDo(print())
        	   .andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.SUCCESS_CODE)))
        	   .andExpect(jsonPath("appHeader.statusMessage", equalTo(IResponse.SUCCESS_MESSAGE_PARKING_PAYMENT)));
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}catch(Exception ex) {
			LOGGER.error("", ex);
		}
	}
	
	@Test
	public void testBookingPayment_WhenExceptionIsThrown_ShouldReturnErrorCode() {
		try {
				int bookingId = 10;    
				String transactionId = "20";
				BigDecimal cost = BigDecimal.valueOf(20);
				int customerId = 2;
				boolean extendedStatus = false;
				boolean paymentStatus = true;
				
				BookingPaymentRequestDTO requestBody = new BookingPaymentRequestDTO();
				requestBody.setBookingId(bookingId);
				requestBody.setTransactionId(transactionId);
				requestBody.setCost(cost);
				requestBody.setCustomerId(customerId);
				requestBody.setExtendedStatus(extendedStatus);
				requestBody.setPaymentStatus(paymentStatus);
				
				doThrow(GenericDBException.class).when(spsBookingService).recievePayment(requestBody.getBookingId(),
						requestBody.getTransactionId(), requestBody.getCost(),
						requestBody.isPaymentStatus(),
						requestBody.isExtendedStatus());
				
				mockMvc.perform(post(URL + BOOKING_PAYMENT)
						.contentType(UnitTestUtil.APPLICATION_JSON_UTF8)
						.content(UnitTestUtil.convertObjectToJsonBytes(requestBody)))
        	   .andExpect(status().isOk())
        	   .andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
        	   .andDo(print())
        	   .andExpect(jsonPath("appHeader.statusCode", equalTo(IResponse.ERROR_CODE_APPLICATION)));
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}catch(Exception ex) {
			LOGGER.error("", ex);
		}
	}

}
