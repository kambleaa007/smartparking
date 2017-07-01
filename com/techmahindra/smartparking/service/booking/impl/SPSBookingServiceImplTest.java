package com.techmahindra.smartparking.service.booking.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.config.RootConfigTest;
import com.techmahindra.smartparking.constant.BookingStatus;
import com.techmahindra.smartparking.dao.jpa.booking.ISpBookingCRUDRepository;
import com.techmahindra.smartparking.dao.jpa.booking.ISpPaymentGatewayCRUDRepository;
import com.techmahindra.smartparking.pojo.dbentity.customer.SpCustomerProfile;
import com.techmahindra.smartparking.pojo.dbentity.transaction.SpBooking;
import com.techmahindra.smartparking.pojo.dbentity.transaction.SpPayment;
import com.techmahindra.smartparking.pojo.dbentity.transaction.SpPaymentGateway;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZone;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZoneLevel;
import com.techmahindra.smartparking.pojo.dto.request.booking.BookingRequestDTO;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingResponseDTO;
import com.techmahindra.smartparking.service.email.EmailSenderService;
import com.techmahindra.smartparking.service.sms.SMSService;
import com.techmahindra.smartparking.utility.CommonUtility;
import com.techmahindra.smartparking.utility.UnitTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfigTest.class})
@WebAppConfiguration
public class SPSBookingServiceImplTest {
	private final static Logger LOGGER = LoggerFactory.getLogger(SPSBookingServiceImplTest.class);

	@Mock
    private ISpBookingCRUDRepository bookingRepo;
	
	@Mock
	private ISpPaymentGatewayCRUDRepository gateWayRepo;
	
	@Mock
	private SMSService smsService;
	
	@Mock
	private EmailSenderService mailService;
	
	@Mock
	private Environment env;

	
	@InjectMocks
	SPSBookingServiceImpl bookingService; 

	@Rule
	public TestRule watcher = new TestWatcher() {
	   protected void starting(Description description) {
		   LOGGER.info("Executing " + description.getMethodName());
	   }
	};
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

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
			
	        List<Object[]> list = new ArrayList<>();
	        list.add(new Object[]{Integer.valueOf(2), BigDecimal.valueOf(10)});
	        when(bookingRepo.findLevelPriceDetails(levelId, vehicleType, slotType)).thenReturn(list);
	        BigDecimal cost = bookingService.getBookingCost(levelId, vehicleType, slotType, fromTime, toTime);
			assertEquals(BigDecimal.valueOf(10), cost);
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
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
	        
	        List<Object[]> list = new ArrayList<>();
	        list.add(new Object[]{Integer.valueOf(2), BigDecimal.valueOf(10)});
	        when(bookingRepo.findLevelPriceDetails(levelId, vehicleType, slotType)).thenReturn(list);
	        BigDecimal cost = bookingService.getBookingCost(levelId, vehicleType, slotType, fromTime, toTime);
	        LOGGER.info("cost:"+cost);
	        
			assertEquals(BigDecimal.valueOf(1680), cost);
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}
	}
	
	@Test(expected = GenericDBException.class)
	public void testGetBookingCostForNoRecordFound() throws GenericDBException {
		int levelId = 3;
		int vehicleType = 2;
		int slotType = 1;
		String fromTime = UnitTestUtil.getCurrentDateTimeString();
		String toTime = UnitTestUtil.getDateTimeAfterMinutesString(10);
		
		List<Object[]> list = new ArrayList<>();
		when(bookingRepo.findLevelPriceDetails(levelId, vehicleType, slotType)).thenReturn(list);
		bookingService.getBookingCost(levelId, vehicleType, slotType, fromTime, toTime);
	}

	@Test
	public void testBlockParking() {
		try {
			int customerId = 2;
			int levelId = 2; 
			int vehicleType = 2;
	        int slotType = 1;
	    	String fromTime = UnitTestUtil.getCurrentDateTimeString();
			String toTime = UnitTestUtil.getDateTimeAfterMinutesString(10);
			int expectedBookingId = 10; 
	       
	        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
	        bookingRequestDTO.setCustomerId(customerId);
	        bookingRequestDTO.setLevelId(levelId);
	        bookingRequestDTO.setVehicleTypeId(vehicleType);
	        bookingRequestDTO.setSlotTypeId(slotType);
	        bookingRequestDTO.setFromTime(fromTime);
	        bookingRequestDTO.setToTime(toTime);
	        bookingRequestDTO.setTimeZone("IST");
	        
	        String currentTime = UnitTestUtil.getCurrentDateTimeString();
	        
	        when(bookingRepo.blockParking(bookingRequestDTO.getCustomerId(),
	                bookingRequestDTO.getLevelId(), bookingRequestDTO
	                        .getVehicleTypeId(), bookingRequestDTO.getSlotTypeId(),
	                BookingStatus.BLOCKED, CommonUtility.convertToGMTTime(
	                        bookingRequestDTO.getFromTime(),
	                        bookingRequestDTO.getTimeZone()), CommonUtility
	                        .convertToGMTTime(bookingRequestDTO.getToTime(),
	                                bookingRequestDTO.getTimeZone()), UnitTestUtil.getCurrentDateTimeString())).thenReturn(1);

	        when(bookingRepo.findSpBookingByCustomerIdAndCreatedOn(
                    bookingRequestDTO.getCustomerId(), currentTime)).thenReturn(expectedBookingId);
	        BookingResponseDTO bookingResponseDTO  = bookingService.blockParking(bookingRequestDTO);
			assertEquals(expectedBookingId, bookingResponseDTO.getBookingId());
		}catch(GenericDBException dbEx) {
			LOGGER.error("", dbEx);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = GenericDBException.class)
	public void testShouldNotBlockParking() throws GenericDBException {
		int customerId = 2;
		int levelId = 2;
		int vehicleType = 2;
		int slotType = 1;
		String fromTime = UnitTestUtil.getCurrentDateTimeString();
		String toTime = UnitTestUtil.getDateTimeAfterMinutesString(10);

		BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
		bookingRequestDTO.setCustomerId(customerId);
		bookingRequestDTO.setLevelId(levelId);
		bookingRequestDTO.setVehicleTypeId(vehicleType);
		bookingRequestDTO.setSlotTypeId(slotType);
		bookingRequestDTO.setFromTime(fromTime);
		bookingRequestDTO.setToTime(toTime);
		bookingRequestDTO.setTimeZone("IST");

		when(bookingRepo.blockParking(bookingRequestDTO.getCustomerId(), bookingRequestDTO.getLevelId(),
				bookingRequestDTO.getVehicleTypeId(), bookingRequestDTO.getSlotTypeId(), BookingStatus.BLOCKED,
				CommonUtility.convertToGMTTime(bookingRequestDTO.getFromTime(), bookingRequestDTO.getTimeZone()),
				CommonUtility.convertToGMTTime(bookingRequestDTO.getToTime(), bookingRequestDTO.getTimeZone()),
				UnitTestUtil.getCurrentDateTimeString())).thenThrow(GenericDBException.class);
		
		bookingService.blockParking(bookingRequestDTO);
	}

	@Test
	public void testRecievePayment() {
		try {
			int bookingId = 10;
			String transactionId = "20";
			BigDecimal cost = BigDecimal.valueOf(10);
			boolean paymentStatus = true;
			boolean isExtended = false;

			SpBooking spbooking = Mockito.mock(SpBooking.class);
			SpPayment spPayment = Mockito.mock(SpPayment.class);
			SpPaymentGateway gateway = Mockito.mock(SpPaymentGateway.class);
			SpCustomerProfile spCustomer = Mockito.mock(SpCustomerProfile.class);
			SpZoneLevel zoneLevel =  Mockito.mock(SpZoneLevel.class);
			SpZone zone = Mockito.mock(SpZone.class);

			when(spPayment.getPaymentStatus()).thenReturn((byte) 1);
			when(spPayment.getPaymentId()).thenReturn(25);

			when(gateWayRepo.findSpPaymentGatewayByGatewayId(1)).thenReturn(gateway);

			when(spbooking.getBookingId()).thenReturn(bookingId);
			when(spbooking.getPayments()).thenReturn(new HashSet<>(Arrays.asList(spPayment)));
			when(spbooking.getSpsCustomer()).thenReturn(spCustomer);
			when(spbooking.getZoneLevel()).thenReturn(zoneLevel);
			
			when(spbooking.getFromtime()).thenReturn(UnitTestUtil.getCurrentDateTime());
			when(spbooking.getTotime()).thenReturn(UnitTestUtil.getDateTimeAfterMinutes(10));
			
			
			when(zoneLevel.getZone()).thenReturn(zone);
			when(zone.getZoneName()).thenReturn("TechM Parking");
			when(zone.getTimezone()).thenReturn("IST");
			
			when(spCustomer.getEmail()).thenReturn("test@gmail.com");
			when(spCustomer.getCountryCode()).thenReturn("91");
			when(spCustomer.getMdnNumber()).thenReturn("1234567889");

			when(bookingRepo.findSpBookingByBookingId(bookingId)).thenReturn(spbooking);

			when(env.getProperty("smtp.username")).thenReturn("user");
			
		    bookingService.recievePayment(bookingId, transactionId, cost, paymentStatus, isExtended);
		    
		    verify(bookingRepo).findSpBookingByBookingId(bookingId);
		    verify(bookingRepo).save(spbooking);
		} catch (Exception ex) {
			LOGGER.error("", ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected=GenericDBException.class)
	public void testShouldNotRecievePayment() throws Exception {
		int bookingId = 10;
		String transactionId = "20";
		BigDecimal cost = BigDecimal.valueOf(10);
		boolean paymentStatus = true;
		boolean isExtended = false;

		SpBooking spbooking = Mockito.mock(SpBooking.class);
		SpPayment spPayment = Mockito.mock(SpPayment.class);
		SpPaymentGateway gateway = Mockito.mock(SpPaymentGateway.class);
		SpCustomerProfile spCustomer = Mockito.mock(SpCustomerProfile.class);
		SpZoneLevel zoneLevel = Mockito.mock(SpZoneLevel.class);
		SpZone zone = Mockito.mock(SpZone.class);

		when(spPayment.getPaymentStatus()).thenReturn((byte) 1);
		when(spPayment.getPaymentId()).thenReturn(25);

		when(gateWayRepo.findSpPaymentGatewayByGatewayId(1)).thenReturn(gateway);

		when(spbooking.getBookingId()).thenReturn(bookingId);
		when(spbooking.getPayments()).thenReturn(new HashSet<>(Arrays.asList(spPayment)));
		when(spbooking.getSpsCustomer()).thenReturn(spCustomer);
		when(spbooking.getZoneLevel()).thenReturn(zoneLevel);

		when(spbooking.getFromtime()).thenReturn(UnitTestUtil.getCurrentDateTime());
		when(spbooking.getTotime()).thenReturn(UnitTestUtil.getDateTimeAfterMinutes(10));

		when(zoneLevel.getZone()).thenReturn(zone);
		when(zone.getZoneName()).thenReturn("TechM Parking");
		when(zone.getTimezone()).thenReturn("IST");

		when(spCustomer.getEmail()).thenReturn("test@gmail.com");
		when(spCustomer.getCountryCode()).thenReturn("91");
		when(spCustomer.getMdnNumber()).thenReturn("1234567889");

		when(bookingRepo.findSpBookingByBookingId(bookingId)).thenThrow(GenericDBException.class);

		when(env.getProperty("smtp.username")).thenReturn("user");

		bookingService.recievePayment(bookingId, transactionId, cost, paymentStatus, isExtended);

		verify(bookingRepo).findSpBookingByBookingId(bookingId);
		verify(bookingRepo).save(spbooking);

	}
}
